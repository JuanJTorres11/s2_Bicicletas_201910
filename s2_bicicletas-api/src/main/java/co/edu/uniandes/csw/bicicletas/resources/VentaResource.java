/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.VentaDTO;
import co.edu.uniandes.csw.bicicletas.ejb.VentaLogic;
import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ws.rs.*;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Lozano
 */
@Path("venta")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class VentaResource {

    /**
     * Logger de programa.
     */
    private static final Logger LOGGER = Logger.getLogger(VentaResource.class.getName());
    
    /**
     * Constante que declara que algo no existe.
     */
    public final static String NO = "no existe";

    @Inject
    VentaLogic logica;

    /**
     * Crea una venta.
     *
     * @param pVenta
     * @return Retorna un objeto tipo VentaDTO
     */
    @POST
    public VentaDTO crearVenta(VentaDTO pVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VentaResource Venta: input: {0}", pVenta);
        VentaEntity VentaEntity = pVenta.toEntity();
        VentaEntity nuevoVentaEntity = logica.createVenta(VentaEntity);
        VentaDTO VentaDTO = new VentaDTO(nuevoVentaEntity);
        LOGGER.log(Level.INFO, "VentaResource Venta: output: {0}", VentaDTO);
        return VentaDTO;
    }

    /**
     * Obtiene una venta que se especifica por un id.
     *
     * @param id id de la venta a buscar.
     * @return retorna la venta respectivo al id.
     */
    @GET
    @Path("{pVentaId: \\d+}")
    public VentaDTO getVenta(@PathParam("pVentaId") long pVentaId) {
        LOGGER.log(Level.INFO, "ventaResource getVenta: input: {0}", pVentaId);
        VentaEntity VentaEntity = logica.getVenta(pVentaId);
        if (VentaEntity == null) {
            throw new WebApplicationException("El recurso /ventas/" + pVentaId + NO, 404);
        }
        VentaDTO ventaDTO = new VentaDTO(VentaEntity);
        LOGGER.log(Level.INFO, "VentaResource Venta: output: {0}", ventaDTO);
        return ventaDTO;
    }

    /**
     * Actualiza la informacion de la venta especificada.
     *
     * @param id id de la venta a actualizar.
     * @return La venta actualizada.
     */
    @PUT
    @Path("{id: \\d+}")
    public VentaDTO actualizaVenta(@PathParam("id") Long id, VentaDTO pVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VentaResource updateVenta: input: id:{0} , Venta: {1}", new Object[]{id, pVenta});
        pVenta.setId(id);
        if (logica.getVenta(id) == null) {
            throw new WebApplicationException("El recurso /venta/" + id + NO, 404);
        }
        VentaDTO detailDTO = new VentaDTO(logica.updateVenta(pVenta.toEntity()));
        LOGGER.log(Level.INFO, "ventaResource updateVenta: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Elimina una venta especifica.
     *
     * @param id id de la venta a eliminar.
     * @return
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarVenta(@PathParam("id") long id) {
        LOGGER.log(Level.INFO, "VentaResource deleteVenta: input: {0}", id);
        if (logica.getVenta(id) == null) {
            throw new WebApplicationException("El recurso /venta/" + id + NO, 404);
        }
        logica.deleteVenta(id);
        LOGGER.info("VentaResource deleteVenta: output: void");
    }

    /**
     * Retorna una lista con todas las ventas registradas.
     *
     * @return la lista con las ventas.
     */
    @GET
    public List<VentaDTO> darVendedores() {
        List<VentaDTO> listaVentas = listEntity2DetailDTO(logica.getVentas());
        return listaVentas;
    }

    /**
     * Convierte una lista de entity en una lista de Dto
     *
     * @param entityList
     * @return
     */
    private List<VentaDTO> listEntity2DetailDTO(List<VentaEntity> entityList) {
        List<VentaDTO> list = new ArrayList<>();
        for (VentaEntity entity : entityList) {
            list.add(new VentaDTO(entity));
        }
        return list;
    }

}
