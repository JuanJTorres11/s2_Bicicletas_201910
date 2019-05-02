package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.MedioPagoDTO;
import co.edu.uniandes.csw.bicicletas.ejb.MedioPagoLogic;
import co.edu.uniandes.csw.bicicletas.ejb.VendedorMedioPagoLogic;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.bicicletas.mappers.WebApplicationExceptionMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;

/**
 * Andres Donoso-Juan José Torres
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendedorMedioPagoResource
{

    private static final Logger LOGGER = Logger.getLogger(VendedorMedioPagoResource.class.getName());

    @Inject
    private VendedorMedioPagoLogic vendedorMediosPagoLogic;

    @Inject
    private MedioPagoLogic medioPagoLogic;

    /**
     * Guarda un medio de pago en el vendedor con el id dado.
     * @param vendedorId Id del vendedor.
     * @param medio
     * @return JSON {@link MedioPagoDTO} - Medio de pago guardado en el
     * vendedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago.
     */
    @POST
    public MedioPagoDTO addMedioPago(@PathParam("vendedorId") Long vendedorId, MedioPagoDTO medio) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "VendedorMedioPagosResource addMedioPago: input: vendedorsID: {0} , medioPagosId: {1}", new Object[]
        {
            vendedorId,
            medio.getId()
        });
        MedioPagoDTO medioPagoDTO = new MedioPagoDTO(vendedorMediosPagoLogic.addMedioPago(vendedorId, medio.toEntity()));
        LOGGER.log(Level.INFO, "EditorialMedioPagosResource addMedioPago: output: {0}", medioPagoDTO);
        return medioPagoDTO;
    }

    /**
     * Busca y devuelve todos los medios de pagos de un vendedor.
     *
     * @param vendedorId Identificador del vendedor que se esta buscando.
     * @return JSONArray {@link MedioPagoDTO} - Los medios de pago encontrados
     * en el vendedor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MedioPagoDTO> getMedioPagos(@PathParam("vendedorId") Long vendedorId)
    {
        LOGGER.log(Level.INFO, "VendedorMedioPagosResource getMedioPagos: input: {0}", vendedorId);
        MedioPagoDTO dto = new MedioPagoDTO();
        List<MedioPagoDTO> listaDetailDTOs = dto.mediosPagoListEntity2DTO(vendedorMediosPagoLogic.getMediosPago(vendedorId));
        LOGGER.log(Level.INFO, "VendedorMedioPagosResource getMedioPagos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el medio de pago con el id asociado dentro del vendedor con id
     * asociado.
     *
     * @param vendedorId Identificador del vendedor que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @param mediosPagoId Identificador del medio de pago que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link MedioPagoDTO} - El medio de pago buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago en
     * el vendedor.
     */
    @GET
    @Path("{mediosPagoId: \\d+}")
    public MedioPagoDTO getMedioPago(@PathParam("vendedorId") Long vendedorId, @PathParam("mediosPagoId") Long mediosPagoId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "VendedorMedioPagosResource getMedioPago: input: vendedorsID: {0} , mediosPagoId: {1}", new Object[]
        {
            vendedorId, mediosPagoId
        });
        if (medioPagoLogic.getMedioPago(mediosPagoId) == null)
        {
            throw new WebApplicationException("El recurso /vendedores/" + vendedorId + "/mediosPago/" + mediosPagoId + " no existe", 404);
        }
        MedioPagoDTO medioPagoDTO = new MedioPagoDTO(vendedorMediosPagoLogic.getMedioPago(vendedorId, mediosPagoId));
        LOGGER.log(Level.INFO, "VendedorMedioPagosResource getMedioPago: output: {0}", medioPagoDTO);
        return medioPagoDTO;
    }

    /**
     * Remplaza las instancias de MedioPago asociadas a una instancia de
     * Vendedor
     *
     * @param vendedorId Identificador de la vendedor que se esta remplazando.
     * Este debe ser una cadena de dígitos.
     * @param mediosPago JSONArray {@link MedioPagoDTO} El arreglo de medio de
     * pagos nuevo para el vendedor.
     * @return JSON {@link MedioPagoDTO} - El arreglo de medio de pagos guardado
     * en el vendedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago.
     */
    @PUT
    public List<MedioPagoDTO> replaceMedioPagos(@PathParam("vendedorId") Long vendedorId, List<MedioPagoDTO> mediosPago)
    {
        LOGGER.log(Level.INFO, "VendedorMedioPagosResource replaceMedioPagos: input: vendedorId: {0} , mediosPago: {1}", new Object[]
        {
            vendedorId, mediosPago
        });
        for (MedioPagoDTO medioPago : mediosPago)
        {
            if (medioPagoLogic.getMedioPagoPorNumero(medioPago.getNumeroTarjeta()) == null)
            {
                throw new WebApplicationException("El recurso /mediosPago/" + medioPago.getNumeroTarjeta() + " no existe.", 404);
            }
        }
        MedioPagoDTO dto = new MedioPagoDTO();
        List<MedioPagoDTO> listaDetailDTOs = dto.mediosPagoListEntity2DTO(vendedorMediosPagoLogic.replaceMedioPagos(vendedorId, dto.mediosPagoListDTO2Entity(mediosPago)));
        LOGGER.log(Level.INFO, "VendedorMedioPagosResource replaceMedioPagos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Elimina un métood de pago asociado a un vendedor.
     * @param vendedorId Identificar del vendedor.
     * @param mediosPagoId Identificador del medio de pago.
     * @throws BusinessLogicException Si el vendedor no existe.
     */
    @DELETE
    @Path("{mediosPagoId: \\d+}")
    public void borrarMedioPago (@PathParam("vendedorId") Long vendedorId, @PathParam("mediosPagoId") Long mediosPagoId) throws BusinessLogicException
    {
        if (getMedioPago(vendedorId, mediosPagoId) == null)
            throw new WebApplicationException("El recurso /mediosPago/ con id: "  + mediosPagoId + " no existe. ", 404);
        vendedorMediosPagoLogic.eliminarMedioPago(vendedorId, mediosPagoId);
    }
}