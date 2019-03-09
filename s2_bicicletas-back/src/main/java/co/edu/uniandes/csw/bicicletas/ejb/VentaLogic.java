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

        if (pVenta.getId() > 0) {
            throw new BusinessLogicException("El id de la venta no puede ser menor a 0.");
        } else if (vp.findById(pVenta.getId()) != null) {
            throw new BusinessLogicException("Ya existe una venta con dicho id \"" + pVenta.getId() + "\"");
        }

        if (pVenta.getFactura().isEmpty()) {
            throw new BusinessLogicException("La ruta de la imagen no puede estar vacío.");
        }

        if (pVenta.getPrecio() > 0) {
            throw new BusinessLogicException("El precio no puede ser un valor menor a 0.");
        }

        if (pVenta.getAprobado() != true) {
            throw new BusinessLogicException("No fue aprobada la compra.");
        }

        if (0 > pVenta.getFotos().length) {
            throw new BusinessLogicException("La ruta de la imagen no puede estar vacío.");
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
        LOGGER.log(Level.INFO, "se borrará la venta con id {}", id);
        LOGGER.log(Level.INFO, "se borró la venta con id {}", id);
        vp.delete(id);
    }

    /**
     * Retorna una lista con todas las ventas.
     *
     * @return
     */
    public List<VentaEntity> findAllVentas() {
        LOGGER.log(Level.INFO, "se buscarán todos las ventas");
        List<VentaEntity> lista = vp.findAll();
        if (lista == null || lista.isEmpty()) {
            LOGGER.log(Level.SEVERE, "No existen ventas");
        }
        LOGGER.log(Level.INFO, "Se termina la busqueda de los ventas");
        return lista;
    }

    /**
     * Actualiza una venta.
     *
     * @param pVenta los valores a actualizar.
     * @return la venta actualizada, 
     * 1.Ninguno de los atributos es vacio o nulo. <br>
     * 2.No hay otro vendedor o comprador con el mismo login. <br>
     * @throws BusinessLogicException
     */
    public VentaEntity updateVenta(VentaEntity pVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una venta.");

        if (pVenta.getId() > 0) {
            throw new BusinessLogicException("El login del venta no puede ser menor a 0.");
        } else if (vp.findById(pVenta.getId()) != null) {
            throw new BusinessLogicException("Ya existe una venta con el id \"" + pVenta.getId() + "\"");
        }

        if (pVenta.getFactura().isEmpty()) {
            throw new BusinessLogicException("La ruta de la imagen no puede estar vacío.");
        }

        if (pVenta.getPrecio() > 0) {
            throw new BusinessLogicException("El precio no puede ser menor a cero.");
        }

        if (pVenta.getAprobado() != true) {
            throw new BusinessLogicException("No fue aprobada la compra.");
        }

        if (0 >= pVenta.getFotos().length) {
            throw new BusinessLogicException("La ruta de la imagen no puede estar vacío.");
        }

        VentaEntity ret = vp.create(pVenta);

        LOGGER.log(Level.INFO, "Termina proceso de crear una venta.");
        return ret;
    }

    /**
     * obtener la venta con el id relacionado.
     *
     * @param id de la venta a buscar.
     * @return la venta con el id relacionado.
     */
    public VentaEntity findVenta(Long id) {
        LOGGER.log(Level.INFO, "Se buscará el venta con id {}", id);
        VentaEntity buscado = vp.find(id);
        if (buscado == null) {
            LOGGER.log(Level.SEVERE, "No existe una venta con id {}", id);
        }
        LOGGER.log(Level.INFO, "Se termina la busqueda del venta con id {}", id);
        return buscado;
    }
}
