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
 * Juan José Torres-Andres Donoso
 */
@Stateless
public class VendedorMedioPagoLogic
{

    private static final Logger LOGGER = Logger.getLogger(VendedorMedioPagoLogic.class.getName());

    /**
     * Conexión a la persistencia del vendedor.
     */
    @Inject
    private VendedorPersistence vendedorPersistence;

    /**
     * Conexión a la persistencia del medio de pago.
     */
    @Inject
    private MedioPagoPersistence medioPagoPersistence;

    /**
     * Agrega un nuevo medio de pago al vendedor.
     *
     * @param medio Medio de pago que se desea agregar.
     * @param vendedorId Id del vendedor al que se la va a agregar el medio de
     * pago.
     * @return medio de pago agregado.
     */
    public MedioPagoEntity addMedioPago(Long vendedorId, MedioPagoEntity medio)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un medio de pago a un vendedor con id = {0}", vendedorId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedorId);
        vendedorEntity.getMediosPago().add(medio);
        medio.setVendedor(vendedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un medio de pago al vendedor con id = {0}", vendedorId);
        return medio;
    }

    /**
     * Retorna una lista con los medios de pago del vendedor.
     * @param vendedorId Id del vendedor.
     * @return lista con los medios de pago.
     */
    public List<MedioPagoEntity> getMediosPago(Long vendedorId)
    {
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
    public MedioPagoEntity getMedioPago(Long vendedorId, Long medioPagoId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el medio de pago con id: {0} del vendedor con id: {1}", new Object[] {vendedorId, medioPagoId});
        MedioPagoEntity medioPago = medioPagoPersistence.findByVendedor(vendedorId, medioPagoId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el medio de pago con id: {0} del vendedor con id: {1} ", new Object[] {vendedorId, medioPagoId});
        if (medioPago == null)
        {
            throw new BusinessLogicException("El medio de pago no está asociado al vendedor");
        }
        return medioPago;
    }

    /**
     * Reemplaza los medios de pago de un vendedor.
     * @param vendedorId Id del vendedor.
     * @param mediosPago Medios de pago que se agregarán.
     * @return lista de medios de pago actualizada.
     */
    public List<MedioPagoEntity> replaceMedioPagos(Long vendedorId, List<MedioPagoEntity> mediosPago)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el vendedor con id = {0}", vendedorId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedorId);
        List<MedioPagoEntity> medioPagoList = medioPagoPersistence.findAll();
        for (MedioPagoEntity medioPago : medioPagoList)
        {
            if (mediosPago.contains(medioPago))
            {
                medioPago.setVendedor(vendedorEntity);
            }
            else if (medioPago.getVendedor() != null && medioPago.getVendedor().equals(vendedorEntity))
            {
                medioPago.setVendedor(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el vendedor con id = {0}", vendedorId);
        return mediosPago;
    }
    
    /**
     * Se elimina un medio de pago asociado a un vendedor
     * @param vendedorId Identificador del Vendedor.
     * @param medioPagoId Identificador del medio de pago.
     * @throws BusinessLogicException Si el medio de pago no existe o no está asociado al vendedor indicado.
     */
    public void eliminarMedioPago (Long vendedorId, Long medioPagoId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el medio de pago con id: {0} del vendedor con id: {1} ", new Object[] {vendedorId, medioPagoId});
        MedioPagoEntity borrar = getMedioPago(vendedorId, medioPagoId);
        if (borrar == null)
            throw new BusinessLogicException("El medio de pago con id = " + medioPagoId + " no esta asociado a el vendedor con id: " + vendedorId);
        
        medioPagoPersistence.delete(borrar.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el medio de pago con id = {0} del vendedor con id: {1}", new Object[] {vendedorId, medioPagoId});
    }
}