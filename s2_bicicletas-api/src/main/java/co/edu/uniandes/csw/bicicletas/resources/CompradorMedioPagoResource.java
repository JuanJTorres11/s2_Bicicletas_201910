/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.MedioPagoDTO;
import co.edu.uniandes.csw.bicicletas.ejb.CompradorMedioPagoLogic;
import co.edu.uniandes.csw.bicicletas.ejb.MedioPagoLogic;
import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.bicicletas.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andres Donoso
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompradorMedioPagoResource {
    private final static Logger LOGGER = Logger.getLogger(CompradorMedioPagoResource.class.getName());
    
    @Inject
    private CompradorMedioPagoLogic compradorMediosPagoLogic;
    
    @Inject
    private MedioPagoLogic medioPagoLogic;
    
    /**
     * Guarda un medio de pago en el comprador con el id dado.
     * @param compradorId Id del comprador.
     * @param medioPagoId Id del medio de pago.
     * @return JSON {@link MedioPagoDTO} - Medio de pago guardado en el comprador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago.
     */
    @POST
    @Path("{mediosPagoId: \\d+}")
    public MedioPagoDTO addMedioPago(@PathParam("compradorId") Long compradorId, @PathParam("medioPagosId") Long medioPagoId) {
        LOGGER.log(Level.INFO, "CompradorMedioPagosResource addMedioPago: input: compradorsID: {0} , medioPagosId: {1}", new Object[]{compradorId, medioPagoId});
        if (medioPagoLogic.getMedioPago(medioPagoId) == null) {
            throw new WebApplicationException("El recurso /medioPagos/" + medioPagoId + " no existe.", 404);
        }
        MedioPagoDTO medioPagoDTO = new MedioPagoDTO(compradorMediosPagoLogic.addMedioPago(medioPagoId, compradorId));
        LOGGER.log(Level.INFO, "EditorialMedioPagosResource addMedioPago: output: {0}", medioPagoDTO);
        return medioPagoDTO;
    }
    
    /**
     * Busca y devuelve todos los medios de pagos de un comprador.
     * @param compradorsId Identificador del comprador que se esta buscando.
     * @return JSONArray {@link MedioPagoDTO} - Los medios de pago encontrados en el
     * comprador. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MedioPagoDTO> getMedioPagos(@PathParam("compradorsId") Long compradorsId) {
        LOGGER.log(Level.INFO, "CompradorMedioPagosResource getMedioPagos: input: {0}", compradorsId);
        List<MedioPagoDTO> listaDetailDTOs = mediosPagoListEntity2DTO(compradorMediosPagoLogic.getMediosPago(compradorsId));
        LOGGER.log(Level.INFO, "CompradorMedioPagosResource getMedioPagos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el medio de pago con el id asociado dentro del comprador con id asociado.
     *
     * @param compradorsId Identificador del comprador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param mediosPagoId Identificador del medio de pago que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link MedioPagoDTO} - El medio de pago buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago en el
     * comprador.
     */
    @GET
    @Path("{mediosPagoId: \\d+}")
    public MedioPagoDTO getMedioPago(@PathParam("compradorsId") Long compradorsId, @PathParam("mediosPagoId") Long mediosPagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompradorMedioPagosResource getMedioPago: input: compradorsID: {0} , mediosPagoId: {1}", new Object[]{compradorsId, mediosPagoId});
        if (medioPagoLogic.getMedioPago(mediosPagoId) == null) {
            throw new WebApplicationException("El recurso /compradors/" + compradorsId + "/mediosPago/" + mediosPagoId + " no existe.", 404);
        }
        MedioPagoDTO bookDetailDTO = new MedioPagoDTO(compradorMediosPagoLogic.getMedioPago(compradorsId, mediosPagoId));
        LOGGER.log(Level.INFO, "CompradorMedioPagosResource getMedioPago: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }

    /**
     * Remplaza las instancias de MedioPago asociadas a una instancia de Comprador
     *
     * @param compradorsId Identificador de la comprador que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param mediosPago JSONArray {@link MedioPagoDTO} El arreglo de medio de pagos nuevo para el
     * comprador.
     * @return JSON {@link MedioPagoDTO} - El arreglo de medio de pagos guardado en el
     * comprador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago.
     */
    @PUT
    public List<MedioPagoDTO> replaceMedioPagos(@PathParam("compradorsId") Long compradorsId, List<MedioPagoDTO> mediosPago) {
        LOGGER.log(Level.INFO, "CompradorMedioPagosResource replaceMedioPagos: input: compradorsId: {0} , mediosPago: {1}", new Object[]{compradorsId, mediosPago});
        for (MedioPagoDTO medioPago : mediosPago) {
            if (medioPagoLogic.getMedioPagoPorNumero(medioPago.getNumeroTarjeta()) == null) {
                throw new WebApplicationException("El recurso /mediosPago/" + medioPago.getNumeroTarjeta() + " no existe.", 404);
            }
        }
        List<MedioPagoDTO> listaDetailDTOs = mediosPagoListEntity2DTO(compradorMediosPagoLogic.replaceMedioPagos(compradorsId, mediosPagoListDTO2Entity(mediosPago)));
        LOGGER.log(Level.INFO, "CompradorMedioPagosResource replaceMedioPagos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de MedioPagoEntity a una lista de MedioPagoDTO.
     *
     * @param entityList Lista de MedioPagoEntity a convertir.
     * @return Lista de MedioPagoDTO convertida.
     */
    private List<MedioPagoDTO> mediosPagoListEntity2DTO(List<MedioPagoEntity> entityList) {
        List<MedioPagoDTO> list = new ArrayList();
        for (MedioPagoEntity entity : entityList) {
            list.add(new MedioPagoDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de MedioPagoDTO a una lista de MedioPagoEntity.
     *
     * @param dtos Lista de MedioPagoDTO a convertir.
     * @return Lista de MedioPagoEntity convertida.
     */
    private List<MedioPagoEntity> mediosPagoListDTO2Entity(List<MedioPagoDTO> dtos) {
        List<MedioPagoEntity> list = new ArrayList<>();
        for (MedioPagoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
