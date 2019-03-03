/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
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

    /**
     * Crea una resena en la persistencia.
     *
     * @param bicicletaEntity La entidad que representa la bicicleta a
     * persistir.
     * @return La entiddad de la resena luego de persistirla.
     * @throws BusinessLogicException Si la resena a persistir ya existe.
     */
    public ResenaEntity createResena(ResenaEntity resenaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la resena");

        // Verifica la regla de negocio que dice que no puede haber dos resenas con el mismo id
        if (persistence.find(resenaEntity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una resena con el id \"" + resenaEntity.getId() + "\"");
        }
        // Invoca la persistencia para crear la resena
        persistence.create(resenaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la resena");
        return resenaEntity;
    }

    /**
     * Elimina una resena
     *
     * @param id : id de la resena que se quiere borrar
     */
    public void deleteResena(Long id) {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar la resena con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la resena con id = {0}", id);
    }

    /**
     * Retorna las resenaEntitys
     *
     * @return Una lista con todas las resenaEntity
     */
    public List<ResenaEntity> getResenas() {

        LOGGER.log(Level.INFO, "Inicia proceso de buscar todas las resenas");
        List<ResenaEntity> resenas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de buscar todas las resenas");
        return resenas;
    }

    /**
     * Retorna una ResenaEntity
     *
     * @param id: ide de la resena que se quiere consultar
     * @return La entiddad de la resena que se quiere consultar
     */
    public ResenaEntity getResena(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de buscar una resena por id ", id);
        ResenaEntity resena = persistence.find(id);
        LOGGER.log(Level.INFO, "Termina proceso de buscar una resena por id", id);
        return resena;
    }

    /**
     * Actualiza una resena
     *
     * @param resenaEntity La Entidad con los cambios
     * @return La Entidad de la resena modificada
     */
    public ResenaEntity ubdateResena(ResenaEntity resenaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una resena con id", resenaEntity.getId());
        ResenaEntity resenaE = persistence.update(resenaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar una resena con id", resenaEntity.getId());
        return resenaE;
    }
}
