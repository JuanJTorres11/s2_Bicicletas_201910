/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.MedioPagoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Andres Donoso
 */
@Path("medioPagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MedioPagoResource {
    private static final Logger LOGGER = Logger.getLogger(MedioPagoResource.class.getName());
    
    @POST
    public MedioPagoDTO crearMedioPago(MedioPagoDTO medioPago) {
        return medioPago;
    }
    
    @PUT
    @Path("{numero: \\d+}")
    public MedioPagoDTO actualizarMedioPago(@PathParam("numero") Long numero, MedioPagoDTO medioPago) {
        return null;
    }
    
    @DELETE
    @Path("{numero: \\d+}")
    public void eliminarMedioPago(@PathParam("numero") Long numero) {
        
    }
    
    @GET
    @Path("{numero: \\d+}")
    public MedioPagoDTO darInfoMedioPago(@PathParam("numero") Long numero) {
        return null;
    }
}
