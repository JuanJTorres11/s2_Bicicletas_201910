/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.bicicletas.dtos.CategoriaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.MarcaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.ResenaDTO;
import co.edu.uniandes.csw.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.csw.bicicletas.ejb.CategoriaLogic;
import co.edu.uniandes.csw.bicicletas.ejb.MarcaLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
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
 * @author Andrea Bayona
 */
@Path("bicicletas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class BicicletaResource {

    private static final Logger LOGGER = Logger.getLogger(BicicletaResource.class.getName());

    @Inject
    private BicicletaLogic logic;

    @Inject
    private CategoriaLogic logicCat;

    @Inject
    private MarcaLogic logicMarca;

    /**
     * Crea una nueva bicicleta con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param bicicleta {@link BicicletaDTO} - La bicicleta que se desea
     * guardar.
     * @return JSON {@link BicicletaDTO} - La bicicleta guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la bicicleta
     */
    @POST
    public BicicletaDTO createBicicleta(BicicletaDTO bicicleta) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "BicicletaResource createBicicleta: input: {0}", bicicleta);
        BicicletaEntity nuevaEntity = logic.createBicicleta(bicicleta.toEntity());
        BicicletaDTO nuevaBicicletaDTO = new BicicletaDTO(nuevaEntity);
        LOGGER.log(Level.INFO, "BicicletaResource createBicicleta: output: {0}", nuevaBicicletaDTO);
        return nuevaBicicletaDTO;

    }

    /**
     * Busca la bicicleta con el id asociado recibido en la URL y lo devuelve.
     *
     * @param bicicletaId Identificador de la bicicleta que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link BicicletaDetailDTO} - la bicicleta buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la bicicleta.
     */
    @GET
    @Path("{bicicletaId: \\d+}")
    public BicicletaDetailDTO getBicicleta(@PathParam("bicicletaId") Long bicicletaId) {

        LOGGER.log(Level.INFO, "BicicletaResource getBicicleta: input: {0}", bicicletaId);
        BicicletaEntity entity = logic.getBicicleta(bicicletaId);

        if (entity == null) {
            throw new WebApplicationException("El recurso /bicicletas/" + bicicletaId + " no existe.", 404);
        }
        BicicletaDetailDTO bikeDetailDTO = new BicicletaDetailDTO(entity);
        LOGGER.log(Level.INFO, "BicicletaResource getBicicleta: output: {0}", bikeDetailDTO);

        return bikeDetailDTO;
    }

    /**
     * Busca y devuelve todos las bicicletas que existen en la aplicacion.
     *
     * @return JSONArray {@link BicicletaDetailDTO} - Las bicicletas encontrados
     * en la aplicación. Si no hay, retorna una lista vacía.
     */
    @GET
    public List<BicicletaDetailDTO> getBicicletas() {
        LOGGER.info("BicicletaResource getBicicletas: input: void");
        List<BicicletaDetailDTO> listaBooks = listEntity2DetailDTO(logic.getBicicletas());
        LOGGER.log(Level.INFO, "BicicletaResource getBicicletas: output: {0}", listaBooks);
        return listaBooks;
    }

    /**
     * /**
     * Actualiza la bicicleta con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param bicicletaId
     * @param bicicleta
     * @return la bicicleta actualizada
     * @throws BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - cuando el recurso no existe
   
     */
    @PUT
    @Path("{bicicletaId: \\d+}")
    public BicicletaDTO updateBicicleta(@PathParam("bicicletaId") Long bicicletaId, BicicletaDTO bicicleta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BicicletaResource updateBicicleta: input: id: {0} , book: {1}", new Object[]{bicicletaId, bicicleta});
        bicicleta.setId(bicicletaId);
        if (logic.getBicicleta(bicicletaId) == null) {
            throw new WebApplicationException("El recurso /bicicletas/" + bicicletaId + " no existe.", 404);
        }
        BicicletaDetailDTO detailDTO = new BicicletaDetailDTO(logic.ubdateBicicleta(bicicleta.toEntity()));
        LOGGER.log(Level.INFO, "BicicletaResource updateBicicleta: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Elimina la bicicleta con el id recibido
     *
     * @param bicicletaId
     */
    @DELETE
    @Path("{bicicletaId: \\d+}")
    public void deleteBicicleta(@PathParam("bicicletaId") Long bicicletaId) {

        LOGGER.log(Level.INFO, "BicicletaResource deleteBicicleta: input: {0}", bicicletaId);
        BicicletaEntity entity = logic.getBicicleta(bicicletaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bicicletas/" + bicicletaId + " no existe.", 404);
        }
        logic.deleteBicicleta(bicicletaId);
        LOGGER.info("BicicletaResource deleteBicicleta: output: void");
    }

    /**
     * Devuleve las reseñas de la bicicletas con el id recibido
     *
     * @param bicicletaId
     * @return
     */
    @Path("{bicicletaId: \\d+}/resenas")
    public Class<ResenaResource> getResenaResource(@PathParam("bicicletaId") Long bicicletaId) {
        if (logic.getBicicleta(bicicletaId) == null) {
            throw new WebApplicationException("El recurso /bicicletas/" + bicicletaId + "/resenas no existe.", 404);
        }
        return ResenaResource.class;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BicicletaEntity a una lista de
     * objetos BicicletaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de bicicletas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de bicicletas en forma DTO (json)
     */
    private List<BicicletaDetailDTO> listEntity2DetailDTO(List<BicicletaEntity> entityList) {
        List<BicicletaDetailDTO> list = new ArrayList<>();
        for (BicicletaEntity entity : entityList) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Remplaza la instancia de Categoria asociada a una Bicicleta.
     *
     * @param bicicletaId Identificador de la Bicicleta que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param categoria La categoria que se será de la bicicleta.
     * @return JSON {@link BookDetailDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la categoria o el
     * bicicleta.
     */
    @PUT
    @Path("bicicletas/{bikeId: \\d+}/categoria")

    public BicicletaDetailDTO replaceCategoria(@PathParam("bicicletaId") Long bicicletaId, CategoriaDTO categoria) {
        LOGGER.log(Level.INFO, "Reemplazar categoria: input: bicicletaId{0} , categoria:{1}", new Object[]{bicicletaId, categoria.toString()});
        if (logic.getBicicleta(bicicletaId) == null) {
            throw new WebApplicationException("El recurso /bicicletas/" + bicicletaId + " no existe.", 404);
        }
        if (logicCat.getCategoriaPorNombre(categoria.getNombre()) == null) {
            throw new WebApplicationException("El recurso /categorias/" + categoria.getNombre() + " no existe.", 404);
        }
        BicicletaDetailDTO bikekDetailDTO = new BicicletaDetailDTO(logic.replaceCategoria(bicicletaId, categoria.getNombre()));
        LOGGER.log(Level.INFO, "Reemplazar categoria: output: {0}", bikekDetailDTO.toString());
        return bikekDetailDTO;
    }

    /**
     * Remplaza la instancia de Marca asociada a una Bicicleta.
     *
     * @param bicicletaId Identificador de la Bicicleta que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param categoria La Marca que se será de la bicicleta.
     * @return JSON {@link BookDetailDTO} - El arreglo de libros guardado en la
     * Marca.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Marca o el
     * bicicleta.
     */
    @PUT
    @Path("bicicletas/{bikeId: \\d+}/marca")

    public BicicletaDetailDTO replaceMarca(@PathParam("bicicletaId") Long bicicletaId, MarcaDTO marca) {
        LOGGER.log(Level.INFO, "Reemplazar marca: input: bicicletaId{0} , categoria:{1}", new Object[]{bicicletaId, marca.toString()});
        if (logic.getBicicleta(bicicletaId) == null) {
            throw new WebApplicationException("El recurso /bicicletas/" + bicicletaId + " no existe.", 404);
        }
        if (logicMarca.getMarca(marca.getId()) == null) {
            throw new WebApplicationException("El recurso /marcas/" + marca.getId() + " no existe.", 404);
        }
        BicicletaDetailDTO bikekDetailDTO = new BicicletaDetailDTO(logic.replaceMarca(bicicletaId, marca.getId()));
        LOGGER.log(Level.INFO, "Reemplazar marca: output: {0}", bikekDetailDTO.toString());
        return bikekDetailDTO;
    }
}
