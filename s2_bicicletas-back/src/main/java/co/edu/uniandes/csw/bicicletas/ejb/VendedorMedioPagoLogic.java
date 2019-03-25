/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.MedioPagoPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.VendedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres Donoso
 */
@Stateless
public class VendedorMedioPagoLogic {
    private static final Logger LOGGER = Logger.getLogger(VendedorMedioPagoLogic.class.getName());
    
    @Inject
    private VendedorPersistence vendedorPersistence;
    
    @Inject
    private MedioPagoPersistence medioPagoPersistence;
    
    /**
     * Agrega un nuevo medio de pago al vendedor.
     * @param medioPagoId Id del medio de pago que se desea agregar.
     * @param vendedorId Id del vendedor al que se la va a agregar el medio de pago.
     * @return medio de pago agregado.
     */
    public MedioPagoEntity addMedioPago(Long medioPagoId, Long vendedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un medio de pago a un vendedor con id = {0}", vendedorId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedorId);
        MedioPagoEntity medioPagoEntity = medioPagoPersistence.find(medioPagoId);
        medioPagoEntity.setVendedor(vendedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un medio de pago al vendedor con id = {0}", vendedorId);
        return medioPagoEntity;
    }
    
    /**
     * Retorna una lista con los medios de pago del vendedor.
     * @param vendedorId Id del vendedor.
     * @return lista con los medios de pago.
     */
    public List<MedioPagoEntity> getMediosPago(Long vendedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los medios de pago del vendedor con id = {0}", vendedorId);
        return vendedorPersistence.find(vendedorId).getMediosPago();
    }
    
    /**
     * Retorna el medio de pago asociado al vendedor.
     * @param vendedorId Id del vendedor.
     * @param medioPagoId Id del medio de pago.
     * @return medio de pago asociado al vendedor.
     * @throws BusinessLogicException si el medio de pago no existe.
     */
    public MedioPagoEntity getMedioPago(Long vendedorId, Long medioPagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el medio de pago con id = {0} del vendedor con id = " + vendedorId, medioPagoId);
        List<MedioPagoEntity> medioPagos = vendedorPersistence.find(vendedorId).getMediosPago();
        MedioPagoEntity medioPagoEntity = medioPagoPersistence.find(medioPagoId);
        int index = medioPagos.indexOf(medioPagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el medio de pago con id = {0} del vendedor con id = " + vendedorId, medioPagoId);
        if (index >= 0) {
            return medioPagos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la vendedor");
    }
    
    /**
     * Reemplaza los medios de pago de un vendedor.
     * @param vendedorId Id del vendedor.
     * @param mediosPago Medios de pago que se agregarán.
     * @return lista de medios de pago actualizada.
     */
    public List<MedioPagoEntity> replaceMedioPagos(Long vendedorId, List<MedioPagoEntity> mediosPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el vendedor con id = {0}", vendedorId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedorId);
        List<MedioPagoEntity> medioPagoList = medioPagoPersistence.findAll();
        for (MedioPagoEntity medioPago : medioPagoList) {
            if (mediosPago.contains(medioPago)) {
                medioPago.setVendedor(vendedorEntity);
            } else if (medioPago.getVendedor() != null && medioPago.getVendedor().equals(vendedorEntity)) {
                medioPago.setVendedor(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el vendedor con id = {0}", vendedorId);
        return mediosPago;
    }
}
