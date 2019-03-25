/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.MedioPagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
public class CompradorMedioPagoLogic {
    private static final Logger LOGGER = Logger.getLogger(CompradorMedioPagoLogic.class.getName());
    
    @Inject
    private CompradorPersistence compradorPersistence;
    
    @Inject
    private MedioPagoPersistence medioPagoPersistence;
    
    /**
     * Agrega un nuevo medio de pago al comprador.
     * @param medioPagoId Id del medio de pago que se desea agregar.
     * @param compradorId Id del comprador al que se la va a agregar el medio de pago.
     * @return medio de pago agregado.
     */
    public MedioPagoEntity addMedioPago(Long medioPagoId, Long compradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un medio de pago a un comprador con id = {0}", compradorId);
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        MedioPagoEntity medioPagoEntity = medioPagoPersistence.find(medioPagoId);
        medioPagoEntity.setComprador(compradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un medio de pago al comprador con id = {0}", compradorId);
        return medioPagoEntity;
    }
    
    /**
     * Retorna una lista con los medios de pago del comprador.
     * @param compradorId Id del comprador.
     * @return lista con los medios de pago.
     */
    public List<MedioPagoEntity> getMediosPago(Long compradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los medios de pago del comprador con id = {0}", compradorId);
        return compradorPersistence.find(compradorId).getMediosPago();
    }
    
    /**
     * Retorna el medio de pago asociado al comprador.
     * @param compradorId Id del comprador.
     * @param medioPagoId Id del medio de pago.
     * @return medio de pago asociado al comprador.
     * @throws BusinessLogicException si el medio de pago no existe.
     */
    public MedioPagoEntity getMedioPago(Long compradorId, Long medioPagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el medio de pago con id = {0} del comprador con id = " + compradorId, medioPagoId);
        List<MedioPagoEntity> medioPagos = compradorPersistence.find(compradorId).getMediosPago();
        MedioPagoEntity medioPagoEntity = medioPagoPersistence.find(medioPagoId);
        int index = medioPagos.indexOf(medioPagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el medio de pago con id = {0} del comprador con id = " + compradorId, medioPagoId);
        if (index >= 0) {
            return medioPagos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la comprador");
    }
    
    /**
     * Reemplaza los medios de pago de un comprador.
     * @param compradorId Id del comprador.
     * @param mediosPago Medios de pago que se agregarán.
     * @return lista de medios de pago actualizada.
     */
    public List<MedioPagoEntity> replaceMedioPagos(Long compradorId, List<MedioPagoEntity> mediosPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el comprador con id = {0}", compradorId);
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        List<MedioPagoEntity> medioPagoList = medioPagoPersistence.findAll();
        for (MedioPagoEntity medioPago : medioPagoList) {
            if (mediosPago.contains(medioPago)) {
                medioPago.setComprador(compradorEntity);
            } else if (medioPago.getComprador() != null && medioPago.getComprador().equals(compradorEntity)) {
                medioPago.setComprador(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el comprador con id = {0}", compradorId);
        return mediosPago;
    }
}
