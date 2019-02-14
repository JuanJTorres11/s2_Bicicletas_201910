/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.ResenaDTO;
import java.util.List;
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
 * @author Andrea
 */

@Path("bicicletas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class BicicletaResource {
    
    private static final Logger LOGGER = Logger.getLogger(BicicletaResource.class.getName());

    @POST
    public BicicletaDTO createBicicleta(BicicletaDTO bicicleta) {
           return bicicleta;
    }

    @GET
    @Path("{bicicletaReferencia: \\d+}")
    public BicicletaDTO getBicicleta(@PathParam("bicicletaReferencia") int bicicletaId) {
        return null;
    }
    
    @GET
    public List<BicicletaDTO> getBicicletas() {
        return null;
    }
     
    @PUT
    @Path("{bicicletaReferencia: \\d+}")
    public BicicletaDTO updateBicicleta(@PathParam("bicicletaReferencia") int editorialsId, BicicletaDTO bicicleta) {
        return null;
    }
    
    @DELETE
    @Path("{bicicletaReferencia: \\d+}")
    public void deleteBicicleta(@PathParam("bicicletaReferencia") int bicicletaId) {
    }
    
}
