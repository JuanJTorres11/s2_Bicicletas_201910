/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.VentaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class VentaLogic 
{
       @Inject 
    private VentaPersistence vp;
    
    private static final Logger LOGGER = Logger.getLogger(VentaLogic.class.getName());
     
    public VentaEntity createVenta(VentaEntity pVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear una venta.");
        
        if(vp.findByName(pVenta.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una venta con el nombre \"" + pVenta.getNombre() + "\"");
        } else if(pVenta.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre de la venta no puede estar vacío.");
        }
        
        vp.crearEntity(pVenta);
        
        LOGGER.log(Level.INFO, "Termina proceso de crear una venta.");
        return pVenta;
    }
    
    public VentaEntity getVenta(Long pCompradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso búsqueda de una venta.");
        
        VentaEntity ret = vp.encontrarEntity(pCompradorId);
        
        LOGGER.log(Level.INFO, "Termina proceso de búsqueda de una venta.");
        return ret;
    }
    
    public VentaEntity getVentaPorNombre(String pLoggin) {
        LOGGER.log(Level.INFO, "Inicia proceso de búsqueda de una venta por Loggin.");
        
        VentaEntity ret = vp.findByName(pLoggin);
        
        LOGGER.log(Level.INFO, "Termina proceso de búsqueda de una venta por Loggin");
        return ret;
    }
    
     public VentaEntity updateVenta(VentaEntity pVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar categoría.");
        
        if(vp.findByName(pVenta.getNombre()) != null) {
            throw new BusinessLogicException("La venta con el nombre dado ya existe");
        }
        
        if(pVenta.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre de la venta no puede estar vacío.");
        }
        
        vp.crearEntity(pVenta);
        
        LOGGER.log(Level.INFO, "Termina proceso de actualizar una venta."); 
        
        return pVenta;
    }
}
