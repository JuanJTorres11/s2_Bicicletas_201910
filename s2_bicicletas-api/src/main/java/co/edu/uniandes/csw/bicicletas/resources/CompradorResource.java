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

    @GET
    @Path("{id: \\d+}")
    public CompradorDTO obtenerComprador(@PathParam("id") Long pCompradorId) {
        return null;
    }

    @PUT
    @Path("{id: \\d+}")
    public CompradorDetailDTO actualizarComprador(@PathParam("id") long id, CompradorDetailDTO pComprador) {
        return pComprador;
    }

    /**
     * Metodo que elimina un comprador.
     */
    @DELETE
    @Path("{id: \\d+}")
    public CompradorDTO eliminarComprador(@PathParam("id") long id) {
        return null;
    }

}
