/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.OrdenDTO;
import co.edu.uniandes.csw.bicicletas.ejb.CompradorOrdenLogic;
import co.edu.uniandes.csw.bicicletas.ejb.CompradorLogic;
import co.edu.uniandes.csw.bicicletas.ejb.OrdenLogic;
import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.bicicletas.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Lozano
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompradorOrdenResource {

    private static final Logger LOGGER = Logger.getLogger(CompradorOrdenResource.class.getName());

    @Inject
    private CompradorOrdenLogic compradorOrdenLogic;

    @Inject
    private CompradorLogic compradorLogic;

    @Inject
    private OrdenLogic ordenLogic;

    /**
     * Agrega una orden en el comprador con el id dado.
     *
     * @param compradorId Id del comprador.
     * @param ordenId Id de la orden.
     * @return JSON {@link OrdenDTO} - Orden guardada en el comprador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la orden.
     */
    @POST
    public OrdenDTO addOrden(@PathParam("compradorId") Long compradorId, OrdenDTO orden) {
        LOGGER.log(Level.INFO, "CompradorOrdenResource addOrden: input: compradorID: {0}", compradorId);
        OrdenDTO ordenDTO = new OrdenDTO(compradorOrdenLogic.addOrden(compradorId, orden.toEntity()));
        LOGGER.log(Level.INFO, "CompradorOrdenResource addOrden: output: {0}", ordenDTO.getIdentificador());
        return ordenDTO;
    }

    /**
     * Retorna las ordenes de un comprador.
     *
     * @param compradorId Identificador del comprador que se esta buscando.
     * @return JSONArray {@link OrdenDTO} - Las ordenes asociadas. Si no hay
     * ninguno retorna una lista vacía.
     */
    @GET
    public List<OrdenDTO> getOrdenes(@PathParam("compradorId") Long compradorId) {
        LOGGER.log(Level.INFO, "CompradorOrdenResource getOrdenes: input: {0}", compradorId);
        List<OrdenDTO> listaDetailDTOs = listEntity2DTO(compradorOrdenLogic.getOrdenes(compradorId));
        LOGGER.log(Level.INFO, "CompradorOrdenResource getOrdenes: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Retorna la orden con el comprador del id asociado.
     *
     * @param compradorId Identificador del comprador asociado.Este debe ser una
     * cadena de dígitos.
     * @param ordenId
     * @return JSON {@link OrdenDTO} - La orden que se esta buscando
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la orden.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la orden en el
     * comprador.
     */
    @GET
    @Path("{ordenId: \\d+}")
    public OrdenDTO getOrden(@PathParam("compradorId") Long compradorId, @PathParam("ordenId") Long ordenId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompradorOrdenResource Orden: input: compradorId: {0} , ordenId: {1}", new Object[]{compradorId, ordenId});
        if (ordenLogic.getOrden(ordenId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + "/orden/" + ordenId + " no existe.", 404);
        }
        OrdenDTO ordenDTO = new OrdenDTO(compradorOrdenLogic.getOrden(compradorId, ordenId));
        LOGGER.log(Level.INFO, "CompradorOrdenResource getOrden: output: {0}", ordenDTO);
        return ordenDTO;
    }

    /**
     * Elimina una orden asociada a un comprador.
     *
     * @param compradorId Identificador del comprador
     * @param ordenId Identificador de la orden.
     * @throws BusinessLogicException Si la orden no existe o no está asociada
     * al comprador.
     */
    @DELETE
    @Path("{ordenId: \\d+}")
    public void borrarVenta(@PathParam("compradorId") Long compradorId, @PathParam("ordenId") Long ordenId) throws BusinessLogicException {
        if (getOrden(compradorId, ordenId) == null) {
            throw new WebApplicationException("El recurso comprador/" + compradorId + "/orden/ " + ordenId + " no existe.", 404);
        }
        compradorOrdenLogic.eliminarOrden(compradorId, ordenId);
    }

    /**
     * Convierte una lista de OrdenEntity a una lista de OrdenDTO.
     *
     * @param entityList Lista de OrdenEntity a convertir.
     * @return Lista de OrdenDTO convertida.
     */
    private List<OrdenDTO> listEntity2DTO(List<OrdenEntity> entityList) {
        List<OrdenDTO> list = new ArrayList();
        for (OrdenEntity entity : entityList) {
            list.add(new OrdenDTO(entity));
        }
        return list;
    }
}
