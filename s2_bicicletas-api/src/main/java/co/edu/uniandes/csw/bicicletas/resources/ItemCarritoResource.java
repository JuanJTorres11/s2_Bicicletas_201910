/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.dtos.ItemCarritoDTO;
import co.edu.uniandes.csw.bicicletas.ejb.CompradorItemCarritoLogic;
import co.edu.uniandes.csw.bicicletas.entities.ItemCarritoEntity;
import co.edu.uniandes.csw.bicicletas.ejb.ItemCarritoLogic;
import java.text.ParseException;
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
 * @author Juan Lozano
 */
@Path("itemCarrito")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ItemCarritoResource {

    private static final Logger LOGGER = Logger.getLogger(BicicletaResource.class.getName());
    
    /**
     * constante que declara que no existe.
     */
    private static final String NO = "no existe";

    @Inject
    private CompradorItemCarritoLogic logic;

    /**
     * Crea una nueva bicicleta con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param pItem {@link BicicletaDTO} - La bicicleta que se desea guardar.
     * @return JSON {@link BicicletaDTO} - La bicicleta guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la bicicleta
     */
    @POST
    public ItemCarritoDTO createItemCarrito(@PathParam("compradorId") Long compradorId, ItemCarritoDTO pItem) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "ItemCompraResource createItemCompra: input: {0}", pItem);
        ItemCarritoEntity itemEntity = pItem.toEntity();
        ItemCarritoEntity nuevaEntity = logic.addItemCarrito(compradorId, itemEntity);
        ItemCarritoDTO nuevoItemDTO = new ItemCarritoDTO(nuevaEntity);
        LOGGER.log(Level.INFO, "ItemCarritoResource createItemCompra: output: {0}", nuevoItemDTO);
        return nuevoItemDTO;

    }

    /**
     * Busca el item con el id asociado recibido en la URL y lo devuelve.
     *
     * @param itemCarritoId Identificador del item que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link ItemCarritoDTO} - el item buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el item.
     */
    @GET
    @Path("{itemCarritoId: \\d+}")
    public ItemCarritoDTO getItemCarrito(@PathParam("compradorId") Long compradorId, @PathParam("itemCarritoId") Long itemCarritoId) throws WebApplicationException, BusinessLogicException {

        LOGGER.log(Level.INFO, "ItemCarritoResource getItemCarrito: input: {0}", itemCarritoId);
        ItemCarritoEntity entity = logic.getItemCarrito(compradorId, itemCarritoId);

        if (entity == null) {
            throw new WebApplicationException("El recurso /itemCarrito/" + itemCarritoId + NO, 404);
        }
        ItemCarritoDTO itemDTO = new ItemCarritoDTO(entity);
        LOGGER.log(Level.INFO, "ItemCarritoResource getItemCarrito: output: {0}", itemDTO);

        return itemDTO;
    }

    /**
     * Busca y devuelve todos los items que existen en la aplicacion.
     *
     * @return JSONArray {@link ItemsCarritoDTO} - Los items encontrados en la
     * aplicación. Si no hay, retorna una lista vacía.
     */
    @GET
    public List<ItemCarritoDTO> getItemCarritos(@PathParam("compradorId") Long compradorId) throws BusinessLogicException {
        LOGGER.info("ItemCarritoResource getItemCarritos: input: void");
        List<ItemCarritoDTO> listaItems = listEntity2DetailDTO(logic.getItemsCarrito(compradorId));
        LOGGER.log(Level.INFO, "ItemCarritosResource getItemCarritos: output: {0}", listaItems);
        return listaItems;
    }

    /**
     * /**
     * Actualiza la bicicleta con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param itemId
     * @param itemCarrito
     * @return la bicicleta actualizada
     * @throws BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * cuando el recurso no existe
     */
    @PUT
    @Path("{itemCarritoId: \\d+}")
    public ItemCarritoDTO updateItemCarrito(@PathParam("compradorId") Long compradorId, @PathParam("itemCarritoId") Long itemId, ItemCarritoDTO itemCarrito) throws BusinessLogicException, WebApplicationException {
        LOGGER.log(Level.INFO, "ItemCarritoResource updateItemCarrito: input: id: {0} , item: {1}", new Object[]{itemId, itemCarrito});
        itemCarrito.setId(itemId);
        if (logic.getItemCarrito(compradorId, itemId) == null) {
            throw new WebApplicationException("El recurso /itemCarrito/" + itemId + NO, 404);
        }
        ItemCarritoDTO detailDTO = new ItemCarritoDTO(logic.ubdateItemCarrito(compradorId, itemCarrito.toEntity()));
        LOGGER.log(Level.INFO, "ItemCarritoResource updateItemCarrito: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Elimina la bicicleta con el id recibido
     *
     * @param itemId
     */
    @DELETE
    @Path("{itemCarritoId: \\d+}")
    public void deleteItemCarrito(@PathParam("compradorId") Long compradorId, @PathParam("itemCarritoId") Long itemId) throws BusinessLogicException, WebApplicationException {

        LOGGER.log(Level.INFO, "ItemCarritoResource deleteItemCarrito: input: {0}", itemId);
        ItemCarritoEntity entity = logic.getItemCarrito(compradorId, itemId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /itemCarrito/" + itemId + NO, 404);
        }
        logic.deleteItemCarrito(compradorId, itemId);
        LOGGER.info("ItemCarritoResource deleteItemCarrito: output: void");
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
    private List<ItemCarritoDTO> listEntity2DetailDTO(List<ItemCarritoEntity> entityList) {
        List<ItemCarritoDTO> list = new ArrayList<>();
        for (ItemCarritoEntity entity : entityList) {
            list.add(new ItemCarritoDTO(entity));
        }
        return list;
    }

}
