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
        
        //Verifica que la bicicleta exista
        BicicletaEntity bike = persistenceBike.find(bicicletaId);
        if(bike == null)
            throw new BusinessLogicException("No existe una bicicleta con el id \"" + bicicletaId + "\"");

        // Verifica la regla de negocio que dice que no puede haber dos resenas con el mismo id
        if (persistence.find(bike.getId(), resenaEntity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una resena con el id \"" + resenaEntity.getId() + "\"");
        }
  
        // Verifica la regla de negocio que dice que la calificacion debe ser menor a 5 y mayor a 0
        if(resenaEntity.getCalificacion() > 5 || resenaEntity.getCalificacion() < 0){
            throw new BusinessLogicException("La calificacion debe ser menor a 5 y mayor a 0 \"" + resenaEntity.getCalificacion() + "\"");
        }
        resenaEntity.setBicicleta(bike);
        // Invoca la persistencia para crear la resena
        LOGGER.log(Level.INFO, "Termina proceso de creación de la resena");
        return persistence.create(resenaEntity);
    }

   
    /**
     * Elimina una resena
     * @param bicicletaId el id de la bicicleta a la que se quiere añadir la reseña
     * @param resenaId id de la resena que se quiere borrar
     * @throws BusinessLogicException Si la reseña o la bicicleta asociada no exiten
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
     * @throws BusinessLogicException Si la bicicleta asociada no exite
     */
    public List<ResenaEntity> getResenas(Long bicicletaId) throws BusinessLogicException{

        LOGGER.log(Level.INFO, "Inicia proceso de buscar todas las resenas");
        
       //Verifica que la bicicleta exista
         BicicletaEntity entityBike = persistenceBike.find(bicicletaId);
       if(entityBike == null)
            throw new BusinessLogicException("No existe una bicicleta con el id \"" + bicicletaId + "\"");

        LOGGER.log(Level.INFO, "Termina proceso de buscar todas las resenas");
        return entityBike.getResenas();
    }

    /**
     * Retorna una ResenaEntity
     *
     * @param resenaId: ide de la resena que se quiere consultar
     * @return La entiddad de la resena que se quiere consultar
     * @throws BusinessLogicException Si la bicicleta asociada o la reseña no exiten
     */
    public ResenaEntity getResena(Long bicicletaId, Long resenaId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de buscar una resena por id ", resenaId);
        
        BicicletaEntity bike = persistenceBike.find(bicicletaId);
        if(bike == null)
            throw new BusinessLogicException("No existe una bicicleta con el id \"" + bicicletaId + "\"");
   
        ResenaEntity resena = persistence.find(bicicletaId, resenaId);
        LOGGER.log(Level.INFO, "Termina proceso de buscar una resena por id", resenaId);
        return resena;
    }

    /**
     * Actualiza una resena
     *
     * @param resenaEntity La Entidad con los cambios
     * @return La Entidad de la resena modificada
     * @throws BusinessLogicException Si la reseña o la bicicleta asociada no exiten
     */
    public ResenaEntity ubdateResena(Long bicicletaId, ResenaEntity resenaEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una resena con id", resenaEntity.getId());
           
         //Verifica que la bicicleta exista
        BicicletaEntity bicicleta = persistenceBike.find(bicicletaId);
        if(bicicleta == null)
            throw new BusinessLogicException("No existe una bicicleta con el id \"" + bicicletaId + "\"");
         resenaEntity.setBicicleta(bicicleta);
        // Verifica la regla de negocio que dice que no puede haber dos resenas con el mismo id
        if (persistence.find(bicicletaId, resenaEntity.getId()) == null) {
            throw new BusinessLogicException("No existe una resena con el id \"" + resenaEntity.getId() + "\"");
        }
           // Verifica la regla de negocio que dice que la calificacion debe ser menor a 5 y mayor a 0
        if(resenaEntity.getCalificacion() > 5 || resenaEntity.getCalificacion() < 0){
            throw new BusinessLogicException("La calificacion debe ser menor a 5 y mayor a 0 \"" + resenaEntity.getCalificacion() + "\"");
        }
        persistence.update(resenaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar una resena con id", resenaEntity.getId());
        return resenaEntity;
    }
}
