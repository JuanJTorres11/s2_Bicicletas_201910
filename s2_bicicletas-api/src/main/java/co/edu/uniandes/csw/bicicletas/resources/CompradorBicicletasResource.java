/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.csw.bicicletas.ejb.CompradorBicicletasLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "comprador/{id}/bicicletas".
 *
 * @author Juan Lozano
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompradorBicicletasResource {

    private static final Logger LOGGER = Logger.getLogger(CompradorBicicletasResource.class.getName());

    /**
     * Lógica de un comprador y bicicleta.
     */
    @Inject
    private CompradorBicicletasLogic compradorBicicletasLogic;

    /**
     * Lógica de una bicicleta
     */
    @Inject
    private BicicletaLogic bicicletaLogic;

    /**
     * Retorna la bicicletas asociadas a un comprador.
     *
     * @param compradorId Identificador del comprador que se busca. Este debe
     * ser una cadena de dígitos.
     * @return JSONArray {@link BicicletaDetailDTO} - Bicicletas asociadas a un
     * comprador. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<BicicletaDetailDTO> getBicicletas(@PathParam("compradorId") Long compradorId) {
        List<BicicletaDetailDTO> listaDetailDTOs = listEntity2DTO(compradorBicicletasLogic.getBicicletas(compradorId));
        return listaDetailDTOs;
    }

    /**
     * Busca la bicicleta asociada al comprador.
     *
     * @param compradorId Identificador del comprador. Este debe ser una cadena
     * de dígitos.
     * @param bicicletaId Identificador de la bicicleta. Este debe ser una
     * cadena de dígitos.
     * @return Detalle de la bicicleta buscada
     * @throws BusinessLogicException Error de lógica que se genera cuando no se
     * encuentra la bicicleta.
     */
    @GET
    @Path("{bicicletaId: \\d+}")
    public BicicletaDetailDTO getBicicleta(@PathParam("compradorId") Long compradorId, @PathParam("bicicletaId") Long bicicletaId) throws BusinessLogicException {
        if (bicicletaLogic.getBicicleta(bicicletaId) == null) {
            throw new WebApplicationException("El recurso /comprador/" + bicicletaId + "/bicicletas/" + bicicletaId + " no existe.", 404);
        }
        BicicletaDetailDTO bikeDetailDTO = new BicicletaDetailDTO(compradorBicicletasLogic.getBicicleta(compradorId, bicicletaId));
        return bikeDetailDTO;
    }

    /**
     * Convierte una lista de Entity a una lista de DetailDTO.
     *
     * @param bicicletas Lista de entities a convertir
     * @return Lista de detalles
     */
    private List<BicicletaDetailDTO> listEntity2DTO(List<BicicletaEntity> bicicletas) {
        List<BicicletaDetailDTO> list = new ArrayList();
        for (BicicletaEntity entity : bicicletas) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }
}
