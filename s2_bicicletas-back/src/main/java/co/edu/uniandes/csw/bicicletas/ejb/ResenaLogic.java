/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.ResenaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andrea
 */
@Stateless
public class ResenaLogic {

    private static final Logger LOGGER = Logger.getLogger(ResenaLogic.class.getName());

    @Inject
    private ResenaPersistence persistence;
    
    @Inject
    private BicicletaPersistence persistenceBike;

    /**
     * Crea una resena en la persistencia.
     *
     * @param resenaEntity La entidad que representa la bicicleta a persistir.
     * @param bicicletaId el id de la bicicleta a la que se quiere añadir la reseña
     * @return La entiddad de la resena luego de persistirla.
     * @throws BusinessLogicException Si la resena a persistir ya existe.
     */
    public ResenaEntity createResena(Long bicicletaId, ResenaEntity resenaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la resena");

        // Verifica la regla de negocio que dice que no puede haber dos resenas con el mismo id
        BicicletaEntity bike = persistenceBike.find(bicicletaId);
        if(bike == null)
            throw new BusinessLogicException("No existe una bicicleta con el id \"" + bicicletaId + "\"");
            
        if (persistence.find(bike.getId(), resenaEntity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una resena con el id \"" + resenaEntity.getId() + "\"");
        }
        resenaEntity.setBicicleta(bike);
        // Invoca la persistencia para crear la resena
        LOGGER.log(Level.INFO, "Termina proceso de creación de la resena");
        return persistence.create(resenaEntity);
    }

    /**
     * Elimina una resena
     *
     * @param resenaId : id de la resena que se quiere borrar
     */
    public void deleteResena(Long bicicletaId, Long resenaId) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar la resena con id = {0}", resenaId);
        ResenaEntity buscada = getResena(bicicletaId, resenaId);
        if(buscada == null)
            throw new BusinessLogicException("La reseña con id = " + resenaId + " no existe o no está asociada a la bicicleta =" + bicicletaId + "\"");
           
        persistence.delete(buscada.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la resena con id = {0}", resenaId);
    }

    /**
     * Retorna las resenaEntitys asociadas a una bicicleta
     *
     * @return Una lista con todas las resenaEntity
     */
    public List<ResenaEntity> getResenas(Long bicicletaId) {

        LOGGER.log(Level.INFO, "Inicia proceso de buscar todas las resenas");
        BicicletaEntity entityBike = persistenceBike.find(bicicletaId);
        LOGGER.log(Level.INFO, "Termina proceso de buscar todas las resenas");
        return entityBike.getResenas();
    }

    /**
     * Retorna una ResenaEntity
     *
     * @param resenaId: ide de la resena que se quiere consultar
     * @return La entiddad de la resena que se quiere consultar
     */
    public ResenaEntity getResena(Long bicicletaId, Long resenaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de buscar una resena por id ", resenaId);
        ResenaEntity resena = persistence.find(bicicletaId, resenaId);
        LOGGER.log(Level.INFO, "Termina proceso de buscar una resena por id", resenaId);
        return resena;
    }

    /**
     * Actualiza una resena
     *
     * @param resenaEntity La Entidad con los cambios
     * @return La Entidad de la resena modificada
     */
    public ResenaEntity ubdateResena(Long bicicletaId, ResenaEntity resenaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una resena con id", resenaEntity.getId());
        BicicletaEntity bicicleta = persistenceBike.find(bicicletaId);
        resenaEntity.setBicicleta(bicicleta);
        persistence.update(resenaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar una resena con id", resenaEntity.getId());
        return resenaEntity;
    }
}
