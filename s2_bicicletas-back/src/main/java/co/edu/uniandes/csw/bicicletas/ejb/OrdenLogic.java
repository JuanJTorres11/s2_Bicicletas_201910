/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.OrdenPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo
 */
@Stateless
public class OrdenLogic {
    
    private static final Logger LOGGER = Logger.getLogger(OrdenLogic.class.getName());
    
    @Inject
    private OrdenPersistence ordenPersistence;
    
     /**
     * Crea una orden en la persistencia.
     *
     * @param ordenEntity La entidad que representa la orden a
     * persistir.
     * @return La orden de la editorial luego de persistirla.
     * @throws BusinessLogicException Si la orden a persistir ya existe.
     */
   public OrdenEntity createOrden(OrdenEntity ord) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la orden");
        //falta completaaaaaar
        if(ord.getCostoTotal()<0 || ord.getCantidad()<0 || ord.getFecha()==null){
            throw new BusinessLogicException("El costo de una orden y la cantidad no pueden ser menor a 0");
        }
        ordenPersistence.create(ord);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la orden");
        return ord;
    }
   
   /**
     *
     * Obtener todas las ordenes existentes en la base de datos.
     *
     * @return una lista de ordenes.
     */
    public List<OrdenEntity> getordenes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las ordenes");
        List<OrdenEntity> ordenes = ordenPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las ordenes");
        return ordenes;
}
    
    /**
     *
     * Obtener una orden por medio de su id.
     *
     * @param ordenId: id de la orden para ser buscada.
     * @return la orden solicitada por medio de su id.
     */
    public OrdenEntity getOrden(Long ordenId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la orden con id = {0}", ordenId);
        OrdenEntity ordenEntity = ordenPersistence.find(ordenId);
        if (ordenEntity == null) {
            LOGGER.log(Level.SEVERE, "La orden con el id = {0} no existe", ordenId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la orden con id = {0}", ordenId);
        return ordenEntity;
}
}
