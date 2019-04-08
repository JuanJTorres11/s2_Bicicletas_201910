/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.csw.bicicletas.ejb.OrdenBicicletasLogic;
import co.edu.uniandes.csw.bicicletas.ejb.OrdenLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "orden/{id}/bicicletas".
 *
 * @author Mateo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdenBicicletasResource {
    
    /**
     * Lógica de la asociación entre una orden y sus bicicletas
     */
    @Inject
    private OrdenBicicletasLogic ordenBicicletasLogic;

    /**
     * Lógica de una bicicleta
     */
    @Inject
    private BicicletaLogic bikeLogic; 
    
    /**
     * Lógica de una orden
     */
    @Inject
    private OrdenLogic ordenLogic;
    
    /**
     * Busca y devuelve todos las bicicletas que existen en la orden.
     * 
     * @param ordenId Identificador de la orden que se esta
     * buscando. Este debe ser una cadena de dígitos.
     * @return JSONArray {@link BicicletaDetailDTO} - Las bicicletas encontradas
     * en la orden. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<BicicletaDetailDTO> getBicicletas(@PathParam("ordenNombre") Long ordenId) {
       List<BicicletaDetailDTO> listaDetailDTOs = listEntity2DTO(ordenBicicletasLogic.getBicicletas(ordenId));
        return listaDetailDTOs;
    }
    
    /**
     * Busca la bicicleta con el id asociado dentro de la orden con el
     * id asociado.
     * 
     * @param ordenId Identificador de la orden que se esta
     * buscando. Este debe ser una cadena de dígitos.
     * @param bikeId Identificador de la bicicleta que se esta
     * buscando. Este debe ser una cadena de dígitos.
     * @return Detalle de la bicicleta buscada
     * @throws BusinessLogicException Error de lógica que se genera cuando no se
     * encuentra la bicicleta en la orden.
     */
    @GET
    @Path("{bicicletaId: \\d+}")
    public BicicletaDetailDTO getBicicleta(@PathParam("ordenId") Long ordenId, @PathParam("bicicletaId") Long bikeId) throws BusinessLogicException {
         if (bikeLogic.getBicicleta(bikeId) == null) {
            throw new WebApplicationException("El recurso /ordenes/" + ordenId + "/bicicletas/" + bikeId + " no existe.", 404);
        }
        BicicletaDetailDTO bikeDetailDTO = new BicicletaDetailDTO(ordenBicicletasLogic.getBicicleta(ordenId, bikeId));
       return bikeDetailDTO;
    }
    
    /**
     *Convierte una lista de Entity a una lista de DetailDTO.
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
