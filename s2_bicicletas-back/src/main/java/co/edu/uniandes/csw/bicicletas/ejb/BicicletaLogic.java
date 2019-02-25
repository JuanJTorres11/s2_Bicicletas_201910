/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
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
public class BicicletaLogic {

    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());

    @Inject
    private BicicletaPersistence persistence;

    /**
     * Crea una bicicleta en la persistencia.
     *
     * @param bicicletaEntity La entidad que representa la bicicleta a
     * persistir.
     * @return La entiddad de la bicicleta luego de persistirla.
     * @throws BusinessLogicException Si la bicicleta a persistir ya existe.
     */
    public BicicletaEntity createBicicleta(BicicletaEntity bicicletaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la bicicleta");

        // Verifica la regla de negocio que dice que no puede haber dos biciletas con la misma referencia
        if (persistence.findByReferencia(bicicletaEntity.getReferencia()) != null) {
            throw new BusinessLogicException("Ya existe una Bicicletaa con la referencia \"" + bicicletaEntity.getReferencia() + "\"");
        }

        // Invoca la persistencia para crear la bicileta
        persistence.create(bicicletaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la bicicleta");

        return bicicletaEntity;
    }

    /**
     * Elimina una bicicleta
     *
     * @param id : id de la bicicleta que se quiere borrar
     */
    public void deleteBicicleta(Long id) {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar la bicicleta con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la bicicleta con id = {0}", id);
    }

    /**
     * Retorna una bicicletaEntity
     *
     * @return Una lista con todas las BicletaEntity
     */
    public List<BicicletaEntity> getBicicletas() {

        LOGGER.log(Level.INFO, "Inicia proceso de buscar todas las bicletas");
        List<BicicletaEntity> bicicletas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de buscar todas las bicletas");
        return bicicletas;
    }

    /**
     * Retorna una bicicletaEntity
     *
     * @param id: ide de la bicicleta que se quiere consultar
     * @return La entiddad de la bicicleta que se quiere consultar
     */
    public BicicletaEntity getBicicleta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de buscar una bicleta por id ", id);
        BicicletaEntity bicicleta = persistence.find(id);
        LOGGER.log(Level.INFO, "Termina proceso de buscar una bicleta por id", id);
        return bicicleta;
    }

    /**
     * Retorna una bicicletaEntity
     *
     * @param referenciaBicicleta : referencia de la bicicleta que se quiere
     * consultar
     * @return La entiddad de la bicicleta que se quiere consultar
     */
    public BicicletaEntity getBicicletaPorReferencia(String referenciaBicicleta) {
        LOGGER.log(Level.INFO, "Inicia proceso de buscar una bicleta por referencia ", referenciaBicicleta);
        BicicletaEntity bicicleta = persistence.findByReferencia(referenciaBicicleta);
        LOGGER.log(Level.INFO, "Termina proceso de buscar una bicleta por referencia", referenciaBicicleta);
        return bicicleta;
    }

    /**
     * Actualiza una bicicleta
     *
     * @param bicicletaEntity La Entidad con los cambios
     * @return La Entidad de la bicicleta modificada
     */
    public BicicletaEntity ubdateBicicleta(BicicletaEntity bicicletaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una bicicleta con id", bicicletaEntity.getId());
        BicicletaEntity bikeE = persistence.update(bicicletaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar una bicicleta con id", bicicletaEntity.getId());
        return bikeE;
    }

}
