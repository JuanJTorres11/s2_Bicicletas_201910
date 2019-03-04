/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.ResenaDTO;
import co.edu.uniandes.csw.bicicletas.ejb.ResenaLogic;
import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
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
 * @author Andrea
 */
@Path("resenas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ResenaResource {

    private static final Logger LOGGER = Logger.getLogger(ResenaResource.class.getName());

    @Inject
    private ResenaLogic logic;

    /**
     * Crea una nueva reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param resena {@link ResenaDTO} - La reseña que se desea guardar.
     * @return JSON {@link BicicletaDTO} - La reseña guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseñaa
     */
    @POST
    public ResenaDTO createResena(ResenaDTO resena) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "ResenaResource createResena: input: {0}", resena);
        ResenaEntity entity = logic.createResena(resena.toEntity());
        ResenaDTO nuevaResenaDTO = new ResenaDTO(entity);
        LOGGER.log(Level.INFO, "ResenaResource createResena: output: {0}", nuevaResenaDTO);

        return nuevaResenaDTO;
    }

    /**
     * 
     * @param resenaId
     * @return 
     */
    @GET
    @Path("{resenaId: \\d+}")
    public ResenaDTO getResena(@PathParam("resenaId") Long resenaId) {
        LOGGER.log(Level.INFO, "ResenaResource getResena: input: {0}", resenaId);
        ResenaEntity entity = logic.getResena(resenaId);

        if (entity == null) {
            throw new WebApplicationException("El recurso /resenas/" + resenaId + " no existe.", 404);
        }
        ResenaDTO resenaDTO = new ResenaDTO(entity);
        LOGGER.log(Level.INFO, "ResenaResource getResena: output: {0}", resenaDTO);

        return resenaDTO;
    }

    /**
     * 
     * @return 
     */
    @GET
    public List<ResenaDTO> getResenas() {
         LOGGER.info("ResenaResource getResenas: input: void");
        List<ResenaDTO> listaBooks = listEntity2DetailDTO(logic.getResenas());
        LOGGER.log(Level.INFO, "ResenaResource getResenas: output: {0}", listaBooks);
        return listaBooks;
    }
    
    /**
     * 
     * @param resenaId
     * @param resena
     * @return 
     */
    @PUT
    @Path("{resenaId: \\d+}")
    public ResenaDTO updateResena(@PathParam("resenaId") Long resenaId, ResenaDTO resena) {
        LOGGER.log(Level.INFO, "ResenaResource updateResena: input: id: {0} , book: {1}", new Object[]{resenaId, resena});
        resena.setId(resenaId);
        if (logic.getResena(resenaId) == null) {
            throw new WebApplicationException("El recurso /books/" + resenaId + " no existe.", 404);
        }
        ResenaDTO detailDTO = new ResenaDTO(logic.ubdateResena(resena.toEntity()));
        LOGGER.log(Level.INFO, "ResenaResource updateResena: output: {0}", detailDTO);
        return detailDTO;  
    }
    
    /**
     * 
     * @param resenaId 
     */
    @DELETE
    @Path("{resenaId: \\d+}")
    public void deleteResena(@PathParam("resenaId") Long resenaId) {
        
        LOGGER.log(Level.INFO, "ResenaResource deleteResena: input: {0}", resenaId);
        ResenaEntity entity = logic.getResena(resenaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + resenaId + " no existe.", 404);
        }
        logic.deleteResena(resenaId);
        LOGGER.info("ResenaResource deleteResena: output: void");
    }
    
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ResenaEntity a una lista de
     * objetos ResenaDTO (json)
     *
     * @param entityList corresponde a la lista de resenas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de resenas en forma DTO (json)
     */
    private List<ResenaDTO> listEntity2DetailDTO(List<ResenaEntity> entityList) {
        List<ResenaDTO> list = new ArrayList<>();
        for (ResenaEntity entity : entityList) {
            list.add(new ResenaDTO(entity));
        }
        return list;
    }

}
