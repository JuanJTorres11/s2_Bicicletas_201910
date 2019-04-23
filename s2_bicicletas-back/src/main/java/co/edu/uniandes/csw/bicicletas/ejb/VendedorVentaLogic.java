package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.VentaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.VendedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Juan José Torres <jj.torresr@uniandes.edu.co>
 */
public class VendedorVentaLogic
{
 private static final Logger LOGGER = Logger.getLogger(VendedorVentaLogic.class.getName());

    @Inject
    private VendedorPersistence vendedorPersistence;

    @Inject
    private VentaPersistence ventaPersistence;

    /**
     * Agrega una nueva venta al vendedor.
     *
     * @param venta Venta que se desea agregar.
     * @param vendedorId Id del vendedor al que se la va a agregar el medio de
     * pago.
     * @return venta agregada.
     */
    public VentaEntity addVenta(Long vendedorId, VentaEntity venta)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una venta a un vendedor con id = {0}", vendedorId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedorId);
        vendedorEntity.getVentas().add(venta);
        venta.setVendedor(vendedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una venta al vendedor con id = {0}", vendedorId);
        return venta;
    }

    /**
     * Retorna una lista con las ventas del vendedor.
     * @param vendedorId Id del vendedor.
     * @return lista con las ventas.
     */
    public List<VentaEntity> getVentas(Long vendedorId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las ventas del vendedor con id = {0}", vendedorId);
        return vendedorPersistence.find(vendedorId).getVentas();
    }

    /**
     * Retorna ls venta asociada al vendedor.
     * @param vendedorId Id del vendedor.
     * @param ventaId Id del venta.
     * @return venta asociada al vendedor.
     * @throws BusinessLogicException si la venta no existe.
     */
    public VentaEntity getVenta(Long vendedorId, Long ventaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el venta con id = {0} del vendedor con id: {1}", new Object[] {vendedorId, ventaId});
        VentaEntity venta = ventaPersistence.findByVendedor(vendedorId, ventaId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el venta con id = {0} del vendedor con id: {1}", new Object[] {vendedorId, ventaId});
        if (venta == null)
        {
            throw new BusinessLogicException("El venta no está asociado al vendedor");
        }
        return venta;
    }
    
    /**
     * Se elimina una venta asociada a un vendedor
     * @param vendedorId Identificador del vendedor.
     * @param ventaId Identificador de la venta.
     * @throws BusinessLogicException Si no existe la venta o está asociada a otro vendedor.
     */
    public void eliminarVenta (Long vendedorId, Long ventaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el venta con id = {0} del vendedor con id: {1}", new Object[] {vendedorId, ventaId});
        VentaEntity borrar = getVenta(vendedorId, ventaId);
        
        if (borrar == null)
            throw new BusinessLogicException("El venta con id = " + ventaId + " no esta asociado a el vendedor con id = " + vendedorId);
        
        ventaPersistence.delete(borrar.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el venta con id = {0} del vendedor con id: {1}", new Object[] {vendedorId, ventaId});
    }   
}