/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.VentaPersistence;
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
public class VentaLogic {

    /**
     * Persistencia de la Venta.
     */
    @Inject
    private VentaPersistence vp;
    /**
     * logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CompradorLogic.class.getName());

    /**
     * Se crea un nuevo Comprador
     *
     * @param pVenta usuario a crear en el sistema
     * @return el Comprador creado
     * @throws BusinessLogicException si se rompe alguna regla de negocio. <br>
     * 1.Ninguno de los atributos es vacio o nulo. <br>
     * 2.No hay otro comprador con el mismo login. <br>
     */
    public VentaEntity createVenta(VentaEntity pVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear una venta.");

        if (pVenta.getId() != null) {
            throw new BusinessLogicException("Ya existe una venta con dicho id \"" + pVenta.getId() + "\"");
        }

        if (!pVenta.getFactura().isEmpty()) {
        } else {
            throw new BusinessLogicException("La ruta de la factura no puede estar vacío.");
        } 
        if (vp.findById(pVenta.getId()) != null) {
            throw new BusinessLogicException("Ya existe un comprador con el nombre \"" + pVenta.getId() + "\"");
        }

        if (pVenta.getPrecio() < 0.0) {
            throw new BusinessLogicException("El precio no puede ser un valor menor a 0.");
        }

        if (pVenta.getFotos().length > 0) {
            throw new BusinessLogicException("La ruta de la imagen no puede estar vacío auqi esotas.");
        }

        vp.create(pVenta);

        LOGGER.log(Level.INFO, "Termina proceso de crear una venta.");
        return pVenta;
    }

    /**
     * Elimina una venta asignada por parametro.
     *
     * @param id de la venta a eliminar.
     */
    public void deleteVenta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la editorial con id = {0}", id);
        vp.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la editorial con id = {0}", id);
    }

    /**
     * Retorna una lista con todas las ventas.
     *
     * @return
     */
    public List<VentaEntity> getVentas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las editoriales");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<VentaEntity> ventas = vp.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las editoriales");
        return ventas;
    }

    /**
     * Actualiza una venta.
     *
     * @param pVenta los valores a actualizar.
     * @return la venta actualizada, 1.Ninguno de los atributos es vacio o nulo.
     * <br>
     * 2.No hay otro vendedor o comprador con el mismo login. <br>
     * @throws BusinessLogicException
     */
    public VentaEntity updateVenta(VentaEntity pVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una venta.");

        if (vp.findById(pVenta.getId()) == null) {
            throw new BusinessLogicException("No existe una venta con el id \"" + pVenta.getId() + "\"");
        }
        if (pVenta.getFactura() == null) {
            throw new BusinessLogicException("La ruta de la imagen no puede estar vacío.");
        }

        if (pVenta.getPrecio() < 0.0) {
            throw new BusinessLogicException("El precio no puede ser un valor menor a 0.");
        }

        if (0 >= pVenta.getFotos().length) {
            throw new BusinessLogicException("La ruta de la imagen no puede estar vacío.");
        }

        VentaEntity ret = vp.update(pVenta);

        LOGGER.log(Level.INFO, "Termina proceso de actualizar una venta.");
        return ret;
    }

    /**
     * obtener la venta con el id relacionado.
     *
     * @param id de la venta a buscar.
     * @return la venta con el id relacionado.
     */
    public VentaEntity getVenta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la venta con id = {0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        VentaEntity ventaEntity = vp.find(id);
        if (ventaEntity == null) {
            LOGGER.log(Level.SEVERE, "La venta con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la venta con id = {0}", id);
        return ventaEntity;
    }

    public VentaEntity getById(Long pId) {
        LOGGER.log(Level.INFO, "Inicia proceso de búsqueda de un comprador por Loggin.");

        VentaEntity ret = vp.findById(pId);
        if (ret == null) {
            LOGGER.log(Level.SEVERE, "No existe el vendedor con login {0}", pId);
        }

        LOGGER.log(Level.INFO, "Termina proceso de búsqueda de un comprador por Loggin");
        return ret;
    }
}
