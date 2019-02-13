/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.CategoriaDTO;
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
 * @author Andres Donoso
 */

@Path("categorias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CategoriaResource {
    
    private static final Logger LOGGER = Logger.getLogger(CategoriaResource.class.getName());
    
    @POST
    public CategoriaDTO crearCategoria(CategoriaDTO categoria) {
        return categoria;
    }
    
    @GET
    public List<CategoriaDTO> darCategorias() {
        return null;
    }
    
    @GET
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public CategoriaDTO darCategoria(@PathParam("nombre") String nombre) {
        return null;
    }
    
    @DELETE
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public void eliminarCategoria(@PathParam("nombre") String nombre) {
        
    }
    
    @PUT
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public CategoriaDTO modificarCategoria(@PathParam("nombre") String nombre, CategoriaDTO categoria) {
        return null;
    }
}
