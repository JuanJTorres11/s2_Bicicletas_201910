/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.CompradorDTO;
import co.edu.uniandes.csw.bicicletas.dtos.CompradorDetailDTO;
import co.edu.uniandes.csw.bicicletas.dtos.VendedorDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.CompradorLogic;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Lozano
 */
@Path("comprador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

// post retornar un basico ADTOO
// El get trae los detalles ADetailsDTO
// un get retorna una cosa mientras que otro tipo de get es traer una listat de varias cosas. 
//En la cracion se utiliza los basicos, debido a la decision del api creado por ellos. 
public class CompradorResource {

    /**
     * Logger de programa.
     */
    private static final Logger LOGGER = Logger.getLogger(VentaResource.class.getName());

    @Inject
    CompradorLogic logica;

    /**
     * *
     * Crea un nuevo comprador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param pComprador
     * @return
     */
    @POST
    public CompradorDTO crearComprador(CompradorDTO pComprador) throws BusinessLogicException {
        CompradorDTO nuevo = null;
        nuevo = new CompradorDTO(logica.createComprador(pComprador.toEntity()));
        return nuevo;
    }

    /**
     * Obtiene una lista con todos los compradores.
     *
     * @return
     */
    @GET
    public List<CompradorDetailDTO> darCompradores() {
        ArrayList<CompradorDetailDTO> compradores = new ArrayList<CompradorDetailDTO>();
        List<CompradorEntity> vEntity = logica.findAllCompradores();
        for (CompradorEntity v : vEntity) {
            compradores.add(new CompradorDetailDTO(v));
        }
        return compradores;
    }
    
    
    /**
     * retorna al comprador dado un id.
     * @param pCompradorId id del comprador a buscar.
     * @return retorna el comprador del id respectivo.
     */
    @GET
    @Path("{id: \\d+}")
    public CompradorDTO obtenerComprador(@PathParam("id") Long pCompradorId) {
        CompradorEntity cE = logica.findComprador(pCompradorId);
        if (cE != null)
        {
            return new CompradorDetailDTO(cE);
        }
        else
        {
            throw new WebApplicationException("El vendedor con id: " + pCompradorId + " no existe", 404);
        }
    }

    /**
     * actualiza la informacion de un comprador ya registrado.
     * @param id dle comprador que se desea actualizar.
     * @return el nuevo objeto actualizado que se encuentra en la base de datos.
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{id: \\d+}")
    public CompradorDetailDTO actualizarComprador(@PathParam("id") long id) throws BusinessLogicException {
        CompradorEntity cE = logica.findComprador(id);
        if (cE != null)
        {
            return new CompradorDetailDTO(logica.updateComprador(cE));
        }
        else
        {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
    }

    /**
     * Metodo que elimina un comprador.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarComprador(@PathParam("id") long id) {
        if (logica.findComprador(id) != null) {
            logica.deleteComprador(id);
        } else {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
    }
}
