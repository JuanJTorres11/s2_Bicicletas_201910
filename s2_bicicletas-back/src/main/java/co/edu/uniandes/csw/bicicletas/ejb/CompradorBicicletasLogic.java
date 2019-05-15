/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class CompradorBicicletasLogic {
    
    private static final Logger LOGGER = Logger.getLogger(CategoriaBicicletasLogic.class.getName());
    
    @Inject
    private BicicletaPersistence bicicletaPersistence;

    @Inject
    private CompradorPersistence compradorPersistence;
    
    /**
     * Agrega un nueva bicicleta al comprador.
     * @param bicicletaId Id de la bicicleta que se desea agregar.
     * @param compradorId Id del comprador al que se la va a agregar.
     * @return bicicleta agregada.
     */
    public BicicletaEntity addBicicleta(Long bicicletaId, Long compradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una bicicleta a un comprador con id = {0}", compradorId);
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        bicicletaEntity.getComprador().add(compradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una bicicleta a un comprador con id = {0}", compradorId);
        return bicicletaEntity;
    }
    
     /**
     * Retorna una lista con las bicicleta de un comprador.
     * @param compradorId Id del comprador.
     * @return lista con las bicicletas.
     */
    public List<BicicletaEntity> getBicicletas(Long compradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las bicicletas de un comprador con id = {0}", compradorId);
        return compradorPersistence.find(compradorId).getListaDeseos();
    }
    
        /**
     * Retorna la bicicleta asociada al comprador.
     * @param compradorId Id del comprador.
     * @param bicicletaId id de la bicicleta.
     * @return bicicleta asociada al comprador.
     * @throws BusinessLogicException si la bicicleta no existe.
     */
    public BicicletaEntity getBicicleta(Long compradorId, Long bicicletaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consulta la bicicleta con id= {0} del comprador con id = " + compradorId, bicicletaId);
        List<BicicletaEntity> bicicletas = compradorPersistence.find(compradorId).getListaDeseos();
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        int index = bicicletas.indexOf(bicicletaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la bicicleta con id = {0} del comprador con id = " + compradorId, bicicletaId);
        if (index >= 0) {
            return bicicletas.get(index);
        }
        throw new BusinessLogicException("La bicicleta no está asociada a la comprador");
    }
    
}
