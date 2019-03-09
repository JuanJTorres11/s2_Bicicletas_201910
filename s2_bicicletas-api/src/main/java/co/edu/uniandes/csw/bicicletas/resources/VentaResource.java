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
import javax.ws.rs.*;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Lozano
 */
@Path("ventas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class VentaResource {

    /**
     * Logger de programa.
     */
    private static final Logger LOGGER = Logger.getLogger(VentaResource.class.getName());

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
        VentaDTO nuevo = null;
        nuevo = new VentaDTO(logica.createVenta(pVenta.toEntity()));
        return nuevo;
    }

    /**
     * Obtiene una venta que se especifica por un id.
     *
     * @param id id de la venta a buscar.
     * @return retorna la venta respectivo al id.
     */
    @GET
    @Path("{id: \\d+}")
    public VentaDTO obtenerVenta(@PathParam("id") long id) {
        VentaEntity cE = logica.findVenta(id);
        if (cE != null) {
            return new VentaDTO(cE);
        } else {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
    }

    /**
     * Actualiza la informacion de la venta especificada.
     *
     * @param id id de la venta a actualizar.
     * @return La venta actualizada.
     */
    @PUT
    @Path("{id: \\d+}")
    public VentaDTO actualizaVenta(@PathParam("id") long id) throws BusinessLogicException {
        VentaEntity vE = logica.findVenta(id);
        if (vE != null) {
            return new VentaDTO(logica.updateVenta(vE));
        } else {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
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
        if (logica.findVenta(id) != null) {
            logica.deleteVenta(id);
        } else {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
    }

    /**
     * Retorna una lista con todas las ventas registradas.
     *
     * @return la lista con las ventas.
     */
    @GET
    public List<VentaDTO> darVendedores() {
        ArrayList<VentaDTO> ventas = new ArrayList<VentaDTO>();
        List<VentaEntity> vEntity = logica.findAllVentas();
        for (VentaEntity v : vEntity) {
            ventas.add(new VentaDTO(v));
        }
        return ventas;
    }

}
