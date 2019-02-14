/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.ResenaDTO;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
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

@Path("resenas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ResenaResource {
    
    private static final Logger LOGGER = Logger.getLogger(ResenaResource.class.getName());

    @POST
    public ResenaDTO createResena(ResenaDTO resena) {
           return resena;
    }

    @GET
    @Path("{resenaId: \\d+}")
    public ResenaDTO getResena(@PathParam("editorialsId") int editorialsId) {
        return null;
    }
    
    @GET
    public List<ResenaDTO> getResenas() {
        return null;
    }
     
    @PUT
    @Path("{resenaId: \\d+}")
    public ResenaDTO updateResena(@PathParam("editorialsId") int editorialsId, ResenaDTO resena) {
        return null;
    }
    
    @DELETE
    @Path("{resenaId: \\d+}")
    public void deleteResena(@PathParam("editorialsId") int editorialsId) {
    }
    
  
}
