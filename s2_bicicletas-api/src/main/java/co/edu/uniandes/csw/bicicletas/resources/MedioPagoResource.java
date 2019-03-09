/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.MedioPagoDTO;
import co.edu.uniandes.csw.bicicletas.ejb.MedioPagoLogic;
import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
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
 * @author Andres Donoso
 */
@Path("medioPagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MedioPagoResource {
    private static final Logger LOGGER = Logger.getLogger(MedioPagoResource.class.getName());
    
    @Inject private MedioPagoLogic medioPagoLogic;
    
    /**
     * Crea un medio de pago con la información dada por parámetro.
     * @param medioPago {@link MedioPagoDTO} - medio de pago que se desea crear.
     * @return JSON {@link MedioPagoDTO} - medio de pago guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper}  1. Si el número de la tarjeta es null.
     *                                                                      2. Si el medio de pago ya existe.
     *                                                                      3. Si el formato del número de la tarjeta no es válido.
     *                                                                      4. Si el formato del código de verificación no es válido.
     *                                                                      5. Si la dirección está vacía o es null.
     *                                                                      6. Si el tipo de la tarjeta está vacía o es null.
     *                                                                      7. Si el tipo de la tarjeta no es válido.
     *                                                                      8. Si el tipo de crédito no es válido.
     */
    @POST
    public MedioPagoDTO crearMedioPago(MedioPagoDTO medioPago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MedioPagoResource crearMedioPago: input: {0}", medioPago);
        
        MedioPagoEntity medioPagoEntity = medioPago.toEntity();
        MedioPagoEntity nuevaCategoria = medioPagoLogic.createMedioPago(medioPagoEntity);
        
        MedioPagoDTO resultado = new MedioPagoDTO(nuevaCategoria);
        
        LOGGER.log(Level.INFO, "MedioPagoResource crearMedioPago: output: {0}", medioPago);
        return resultado;
    }
    
    /**
     * Actualiza el medio de pago con el número dado por parámetro con la información recibida.
     * @param numero Número de la tarjeta.
     * @param medioPago {@link MedioPagoDTO} Medio de pago que se desea guardar.
     * @return JSON {@link MedioPagoDTO} - Categoria guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - 1. Si el medio de pago no existe.
     *                                  2. Si el número de la tarjeta es null.
     *                                  3. Si el medio de pago ya existe.
     *                                  4. Si el formato del número de la tarjeta no es válido.
     *                                  5. Si el formato del código de verificación no es válido.
     *                                  6. Si la dirección está vacía o es null.
     *                                  7. Si el tipo de la tarjeta está vacía o es null.
     *                                  8. Si el tipo de la tarjeta no es válido.
     *                                  9. Si el tipo de crédito no es válido.
     * @throws WebApplicationException {@lnk WebApplicationExceptionMapper} - si el medio de pago no existe.
     */
    @PUT
    @Path("{numero: \\d+}")
    public MedioPagoDTO actualizarMedioPago(@PathParam("numero") Long numero, MedioPagoDTO medioPago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MedioPagoResource actualizarMedioPago: output: numero; {0}, medioPago: {1}", 
                new Object[]{numero, medioPago});
        
        if(medioPagoLogic.getMedioPagoPorNumero(numero) == null) {
            throw new WebApplicationException("El recurso /medioPagos/" + numero + " no existe.", 404);
        }
        MedioPagoEntity medioPagoEntity = medioPago.toEntity();
        medioPagoEntity.setId(medioPagoLogic.getMedioPagoPorNumero(numero).getId());
        MedioPagoDTO medioPagoDTO = new MedioPagoDTO(medioPagoLogic.updateMedioPago(medioPagoEntity));
        
        LOGGER.log(Level.INFO, "MedioPagoResource actualizarMedioPago: output: {0}", medioPagoDTO);
        return medioPagoDTO;
    }
    
    /**
     * Elimina el medio de pago con el nombre dado por parámetro.
     * @param numero Número de el medio de pago.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - 1. Si no existe el medio de pago.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Si el medio de pago no existe.
     */
    @DELETE
    @Path("{numero: \\d+}")
    public void eliminarMedioPago(@PathParam("numero") Long numero) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MedioPagoResource eliminarMedioPago: output: {0}", numero);
        
        MedioPagoEntity medioPago = medioPagoLogic.getMedioPagoPorNumero(numero);
        if(medioPago == null) {
            throw new WebApplicationException("El recurso /medioPagos/" + numero + " no existe.", 404);
        }
        System.out.println("ID DE LA CATEGORIA: " + medioPago.getId());
        medioPagoLogic.deleteMedioPago(medioPago.getId());
        
        LOGGER.log(Level.INFO, "MedioPagoResource eliminarMedioPago: output: void");
    }
    
    /**
     * Retorna el medio de pago con el número asociado.
     * @param numero Nùmero de la categoria.
     * @return JSON {@link MedioPagoDTO} - el medio de pago buscado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Si el medio de pago no existe.
     */
    @GET
    @Path("{numero: \\d+}")
    public MedioPagoDTO darInfoMedioPago(@PathParam("numero") Long numero) {
        LOGGER.log(Level.INFO, "MedioPagoResource darInfoMedioPago: input: {0}", numero);
        
        MedioPagoEntity medioPagoEntity = medioPagoLogic.getMedioPagoPorNumero(numero);
        if(medioPagoEntity == null) {
            throw new WebApplicationException("El recurso /medioPagos/" + numero + " no existe", 404);
        }
        
        MedioPagoDTO medioPago = new MedioPagoDTO(medioPagoEntity);
        LOGGER.log(Level.INFO, "MedioPagoResource darInfoMedioPago: output: {0}", medioPago);
        return medioPago;
    }
}
