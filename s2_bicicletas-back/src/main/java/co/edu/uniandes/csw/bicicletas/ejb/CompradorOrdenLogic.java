/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.OrdenPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
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
public class CompradorOrdenLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(CompradorOrdenLogic.class.getName());
    
    
    /**
    *Conexíón con la persistencia del comprador. 
    */
    @Inject
    private CompradorPersistence compradorPersistence;

    /**
    *Conexíón con la persistencia de la orden. 
    */
    @Inject
    private OrdenPersistence ordenPersistence;
    
     /**
     * Agrega una nueva orden al comprador.
     * @param orden Orden que se desea agregar.
     * @param compradorId Id del comprador al que se la va a agregar la orden.
     * @return orden agregada.
     */
    public OrdenEntity addOrden(Long compradorId, OrdenEntity orden)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una orden a un comprador con id = {0}", compradorId);
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        compradorEntity.getOrdenes().add(orden);
        orden.setComprador(compradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una orden al comprador con id = {0}", compradorId);
        return orden;
    }
    
     /**
     * Retorna una lista con las ordenes del comprador.
     * @param compradorId del comprador.
     * @return lista con las ordenes.
     */
    public List<OrdenEntity> getOrdenes(Long compradorId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las ordenes del comprador con id = {0}", compradorId);
        return compradorPersistence.find(compradorId).getOrdenes();
    }
    
     /**
     * Retorna las ordenes asociada al comprador.
     * @param compradorId Id del comprador.
     * @param ordenId Id de la orden.
     * @return orden asociada al comprador.
     * @throws BusinessLogicException si la orden no existe.
     */
    public OrdenEntity getOrden(Long compradorId, Long ordenId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la venta con id = {0} del comprador con id: {1}", new Object[] {compradorId, ordenId});
        OrdenEntity orden = ordenPersistence.findByComprador(compradorId, ordenId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar de la orden con id = {0} del comprador con id: {1}", new Object[] {compradorId, ordenId});
        if (orden == null)
        {
            throw new BusinessLogicException("El orden no está asociado al comprador");
        }
        return orden;
    }
    
     /**
     * Se elimina una orden asociada a un comprador.
     * @param compradorId Identificador del comprador.
     * @param ordenId Identificador de la orden.
     * @throws BusinessLogicException Si no existe la orden o está asociada a otro comprador.
     */
    public void eliminarOrden (Long compradorId, Long ordenId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el orden con id = {0} del comprador con id: {1}", new Object[] {compradorId, ordenId});
        OrdenEntity borrar = getOrden(compradorId, ordenId);
        
        if (borrar == null)
            throw new BusinessLogicException("La orden con id = " + ordenId + " no esta asociado a el comprador con id = " + compradorId);
        
        ordenPersistence.delete(borrar.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la orden con id = {0} del comprador con id: {1}", new Object[] {compradorId, ordenId});
    } 
}
