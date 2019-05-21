/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.CompradorDTO;
import co.edu.uniandes.csw.bicicletas.dtos.CompradorDetailDTO;
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
        LOGGER.log(Level.INFO, "CompradorResource Comprador: input: {0}", pComprador);
        CompradorEntity compradorEntity = pComprador.toEntity();
        CompradorEntity nuevoCompradorEntity = logica.createComprador(compradorEntity);
        CompradorDTO compradorDTO = new CompradorDTO(nuevoCompradorEntity);
        LOGGER.log(Level.INFO, "CompradorResource createComprador: output: {0}", compradorDTO);
        return compradorDTO;
    }

    /**
     * Obtiene una lista con todos los compradores.
     *
     * @return
     */
    @GET
    public List<CompradorDetailDTO> darCompradores() {
        List<CompradorDetailDTO> listaCompradores = listEntity2DetailDTO(logica.getCompradores());
        return listaCompradores;
    }

    /**
     * retorna al comprador dado un id.
     *
     * @param pCompradorId id del comprador a buscar.
     * @return retorna el comprador del id respectivo.
     */
    @GET
    @Path("{pCompradorId: \\d+}")
    public CompradorDetailDTO getComprador(@PathParam("pCompradorId") long pCompradorId) {
        LOGGER.log(Level.INFO, "CompradorResource getComprador: input: {0}", pCompradorId);
        CompradorEntity compradorEntity = logica.getComprador(pCompradorId);
        if (compradorEntity == null) {
            throw new WebApplicationException("El recurso /comprador/" + pCompradorId + " no se encuentra.", 404);
        }
        CompradorDetailDTO compradorDetailDTO = new CompradorDetailDTO(compradorEntity);
        LOGGER.log(Level.INFO, "CompradorResource getComprador: output: {0}", compradorDetailDTO);
        return compradorDetailDTO;
    }

    /**
     * actualiza la informacion de un comprador ya registrado.
     *
     * @param id dle comprador que se desea actualizar.
     * @return el nuevo objeto actualizado que se encuentra en la base de datos.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public CompradorDetailDTO actualizarComprador(@PathParam("id") long id, CompradorDTO pComprador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompradorResource updateComprador: input: id:{0} , Comprador: {1}", new Object[]{id, pComprador});
        pComprador.setId(id);
        if (logica.getComprador(id) == null) {
            throw new WebApplicationException("El recurso /Comprador/" + id + " no pudo ser encontrado.", 404);
        }
        CompradorDetailDTO compradorDetailDTO = new CompradorDetailDTO(logica.updateComprador(pComprador.toEntity()));
        LOGGER.log(Level.INFO, "CompradorResource updateComprador: output: {0}", compradorDetailDTO);
        return compradorDetailDTO;
    }

    /**
     * Metodo que elimina un comprador.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarComprador(@PathParam("id") long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompradorResource deleteComprador: input: {0}", id);
        if (logica.getComprador(id) == null) {
            throw new WebApplicationException("El recurso /comprador/" + id + " no existe en la BD.", 404);
        }
        logica.deleteComprador(id);
        LOGGER.info("EditorialResource deleteEditorial: output: void");
    }

    /**
     * Convierte una lista de entity en una lista de detailDto
     *
     * @param entityList
     * @return
     */
    private List<CompradorDetailDTO> listEntity2DetailDTO(List<CompradorEntity> entityList) {
        List<CompradorDetailDTO> list = new ArrayList<>();
        for (CompradorEntity entity : entityList) {
            list.add(new CompradorDetailDTO(entity));
        }
        return list;
    }
}
