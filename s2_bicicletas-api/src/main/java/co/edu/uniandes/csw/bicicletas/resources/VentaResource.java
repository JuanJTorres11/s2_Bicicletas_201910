/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.VentaDTO;
import javax.ws.rs.*;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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
    
    /** 
     * 
     * @param pVenta
     * @return 
     */
    @POST 
    public VentaDTO crearVenta(VentaDTO pVenta)
    {
        return pVenta;
    }
    
    @GET 
    @Path("{id: \\d+}")
    public VentaDTO obtenerVenta(@PathParam("id") long id)
    {
        return null;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public VentaDTO actualizaVenta(@PathParam("id") long id)
    {
        return null;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public VentaDTO eliminarVenta(@PathParam("id") long id)
    {
        return null;
    }
    
    
}
