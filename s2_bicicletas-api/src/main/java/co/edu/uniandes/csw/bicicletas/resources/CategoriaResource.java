/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.CategoriaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.CategoriaDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.CategoriaLogic;
import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

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
    
    @Inject private CategoriaLogic categoriaLogic;
    
    /**
     * Crea una categoría con la información dada por parámetro.
     * @param categoria {@link CategoriaDTO} - categoria que se desea crear.
     * @return JSON {@link CategoriaDTO} - Categoria guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Si la categoría ya existe.
     */
    @POST
    public CategoriaDTO crearCategoria(CategoriaDTO categoria) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CategoriaResource crearCategoria: input: {0}", categoria);
        
        CategoriaEntity categoriaEntity = categoria.toEntity();
        CategoriaEntity nuevaCategoria = categoriaLogic.createCategoria(categoriaEntity);
        
        CategoriaDTO resultado = new CategoriaDTO(nuevaCategoria);
        
        LOGGER.log(Level.INFO, "CategoriaResource crearCategoria: output: {0}", categoria);
        return categoria;
    }
    
    /**
     * Retorna todas las categorias que existen.
     * @return JSONArray {@link CategoriaDetailDTO} - categorias.
     */
    @GET
    public List<CategoriaDetailDTO> darCategorias() {
        LOGGER.log(Level.INFO, "CategoriaResource darCategorias: input: void");
        
        List<CategoriaDetailDTO> categorias = new ArrayList<>();
        for(CategoriaEntity categoria: categoriaLogic.getCategorias()) {
            categorias.add(new CategoriaDetailDTO(categoria));
        }
        
        LOGGER.log(Level.INFO, "CategoriaResource darCategorias: output: {0}", categorias);
        return categorias;
    }
    
    /**
     * Retorna la categoría con el nombre asociado.
     * @param nombre Nombre de la categoria.
     * @return JSON {@link CategoriaDetailDTO} - la categoría buscada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Si la categoría no existe.
     */
    @GET
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public CategoriaDetailDTO darCategoria(@PathParam("nombre") String nombre) throws WebApplicationException {
        LOGGER.log(Level.INFO, "CategoriaResource darCategoria: input: {0}", nombre);
        
        CategoriaEntity categoriaEntity = categoriaLogic.getCategoriaPorNombre(nombre);
        if(categoriaEntity == null) {
            throw new WebApplicationException("El recurso /categorias/" + nombre + " no existe", 404);
        }
        
        CategoriaDetailDTO categoria = new CategoriaDetailDTO(categoriaEntity);
        LOGGER.log(Level.INFO, "CategoriaResource darCategoria: output: {0}", categoria);
        return categoria;
    }
    
    /**
     * Elimina la categoría con el nombre dado por parámetro.
     * @param nombre Nombre de la categoría.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Si la categoría no se pudo eliminar.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Si la categoría no existe.
     */
    @DELETE
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public void eliminarCategoria(@PathParam("nombre") String nombre) throws BusinessLogicException, WebApplicationException{
        LOGGER.log(Level.INFO, "CategoriaResource eliminarCategoria: output: {0}", nombre);
        
        CategoriaEntity categoria = categoriaLogic.getCategoriaPorNombre(nombre);
        if(categoria == null) {
            throw new WebApplicationException("El recurso /categorias/" + nombre + " no existe.", 404);
        }
        System.out.println("ID DE LA CATEGORIA: " + categoria.getId());
        categoriaLogic.deleteCategoria(categoria.getId());
        
        LOGGER.log(Level.INFO, "CategoriaResource eliminarCategoria: output: void");
    }
    
    /**
     * Actualiza la categoría con el nombre dado por parámetro con la información recibida.
     * @param nombre Nombre de la categoría.
     * @param categoria {@link CategoriaDetailDTO} Categoria que se desea guardar.
     * @return JSON {@link CategoriaDetailDTO} - Categoria guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - si no es pudo crear la categoría.
     * @throws WebApplicationException {@lnk WebApplicationExceptionMapper} - si la categoría no existe.
     */
    @PUT
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public CategoriaDTO modificarCategoria(@PathParam("nombre") String nombre, CategoriaDetailDTO categoria) throws BusinessLogicException, WebApplicationException {
        LOGGER.log(Level.INFO, "CategoriaResource modificarCategoria: output: nombre; {0}, categoria: {1}", 
                new Object[]{nombre, categoria});
        
        if(categoriaLogic.getCategoriaPorNombre(nombre) == null) {
            throw new WebApplicationException("El recurso /categorias/" + nombre + " no existe.", 404);
        }
        CategoriaEntity categoriaEntity = categoria.toEntity();
        categoriaEntity.setId(categoriaLogic.getCategoriaPorNombre(nombre).getId());
        CategoriaDetailDTO categoriaDTO = new CategoriaDetailDTO(categoriaLogic.updateCategoria(categoriaEntity));
        
        LOGGER.log(Level.INFO, "CategoriaResource darCategorias: output: {0}", categoriaDTO);
        return categoriaDTO;
    }
}
