/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.MarcaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.MarcaDetailDTO;
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
 * @author Mateo
 */

@Path("marcas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MarcaResource {
    private static final Logger LOGGER = Logger.getLogger(MarcaResource.class.getName());
    
    @POST
    public MarcaDTO createMarca(MarcaDTO marca){
        return marca;
    }
    
    @GET
    public List<MarcaDetailDTO> getMarcas(){
        return null;
    }
    
    @GET
    @Path("{id: \\d+}")
    public MarcaDetailDTO getMarca(@PathParam("id") long id){
        return null;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public MarcaDTO putMarca(@PathParam("id") long id){
        return null;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public String deleteMarca(@PathParam("id") long id){
        return null;
    }
}
