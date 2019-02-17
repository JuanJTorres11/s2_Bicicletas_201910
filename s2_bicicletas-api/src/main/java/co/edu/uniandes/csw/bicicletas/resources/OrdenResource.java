/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.OrdenDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Mateo
 */

@Path("ordenes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class OrdenResource {
    private static final Logger LOGGER = Logger.getLogger(OrdenResource.class.getName());
    
    @POST
    public OrdenDTO createOrden(OrdenDTO orden){
        return orden;
    }
    
    @GET
    @Path("{id: \\d+}")
    public OrdenDTO getOrden(@PathParam("id") long id){
        return null;
    }
    
    @GET
    public List<OrdenDTO> getOrdenes(){
        return null;
    }
    
    
}
