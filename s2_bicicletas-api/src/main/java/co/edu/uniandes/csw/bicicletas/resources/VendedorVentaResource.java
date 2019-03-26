/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.VentaDTO;
import co.edu.uniandes.csw.bicicletas.ejb.VentaLogic;
import co.edu.uniandes.csw.bicicletas.ejb.VendedorVentaLogic;
import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
 *
 * @author Juan José Torres <jj.torresr@uniandes.edu.co>
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendedorVentaResource
{
     private final static Logger LOGGER = Logger.getLogger(VendedorVentaResource.class.getName());

    @Inject
    private VendedorVentaLogic logica;

    @Inject
    private VentaLogic logicaVenta;

    /**
     * Guarda un medio de pago en el vendedor con el id dado.
     * @param vendedorId Id del vendedor.
     * @return JSON {@link VentaDTO} - Medio de pago guardado en el
     * vendedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el medio de pago.
     */
    @POST
    public VentaDTO addVenta(@PathParam("vendedorId") Long vendedorId, VentaDTO venta)
    {
        LOGGER.log(Level.INFO, "VendedorVentasResource addVenta: input: vendedorsID: {0}", vendedorId);
        VentaDTO ventaDTO = new VentaDTO(logica.addVenta(vendedorId, venta.toEntity()));
        LOGGER.log(Level.INFO, "EditorialVentasResource addVenta: output: {0}", ventaDTO);
        return ventaDTO;
    }

    /**
     * Busca y devuelve todos los medios de pagos de un vendedor.
     *
     * @param vendedorId Identificador del vendedor que se esta buscando.
     * @return JSONArray {@link VentaDTO} - Los medios de pago encontrados
     * en el vendedor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<VentaDTO> getVentas(@PathParam("vendedorId") Long vendedorId)
    {
        LOGGER.log(Level.INFO, "VendedorVentasResource getVentas: input: {0}", vendedorId);
        List<VentaDTO> listaDetailDTOs = ventasListEntity2DTO(logica.getVentas(vendedorId));
        LOGGER.log(Level.INFO, "VendedorVentasResource getVentas: output: {0}", listaDetailDTOs);
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
     * @return JSON {@link VentaDTO} - El medio de pago buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la venta.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la venta en
     * el vendedor.
     */
    @GET
    @Path("{ventaId: \\d+}")
    public VentaDTO getVenta(@PathParam("vendedorId") Long vendedorId, @PathParam("ventaId") Long ventaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "VendedorVentasResource getVenta: input: vendedorsID: {0} , mediosPagoId: {1}", new Object[]
        {
            vendedorId, ventaId
        });
        if (logicaVenta.findVenta(ventaId) == null)
        {
            throw new WebApplicationException("El recurso /vendedores/" + vendedorId + "/ventas/" + ventaId + " no existe.", 404);
        }
        VentaDTO ventaDTO = new VentaDTO(logica.getVenta(vendedorId, ventaId));
        LOGGER.log(Level.INFO, "VendedorVentasResource getVenta: output: {0}", ventaDTO);
        return ventaDTO;
    }

    /**
     * Elimina una venta asociada a un vendedor
     * @param vendedorId Identificador del vendedor
     * @param ventaId Identificador de la venta
     * @throws BusinessLogicException Si la venta no existe o no está asociada
     * al vendedor.
     */
    @DELETE
    @Path("{ventaId: \\d+}")
    public void borrarVenta (@PathParam("vendedorId") Long vendedorId, @PathParam("ventaId") Long ventaId) throws BusinessLogicException
    {
        if (getVenta(vendedorId, ventaId) == null)
            throw new WebApplicationException("El recurso vendedor/" + vendedorId + "/ventas/ "  + ventaId + " no existe.", 404);
        logica.eliminarVenta(vendedorId, ventaId);
    }
    /**
     * Convierte una lista de VentaEntity a una lista de VentaDTO.
     * @param entityList Lista de VentaEntity a convertir.
     * @return Lista de VentaDTO convertida.
     */
    private List<VentaDTO> ventasListEntity2DTO(List<VentaEntity> entityList)
    {
        List<VentaDTO> list = new ArrayList();
        for (VentaEntity entity : entityList)
        {
            list.add(new VentaDTO(entity));
        }
        return list;
    }
}