/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.csw.bicicletas.ejb.MarcaBicicletasLogic;
import co.edu.uniandes.csw.bicicletas.ejb.MarcaLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
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
 * Clase que implementa el recurso "marca/{id}/bicicletas".
 *
 * @author Mateo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarcaBicicletasResource {
    
    @Inject
    private MarcaBicicletasLogic marcaBicicletasLogic;

    @Inject
    private BicicletaLogic bikeLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private MarcaLogic marLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.


    /**
     * Guarda una bicicleta dentro de una marca con la informacion que
     * recibe el la URL. Se devuelve la bicicleta que se guarda en la marca.
     *
     * @param marcaNombre Identificador de la marca que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param bicicletaId Identificador de la bicicleta que se desea guardar.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link BicicletaDTO} - La bicicleta guardada en la
     * marca.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la bicicleta.
     */
    @POST
    @Path("{bicicletaId: \\d+}")
    public BicicletaDTO addBicicleta(@PathParam("marcaNombre") String marcaNombre, @PathParam("bicicletaId") Long bicicletaId) {
         if (bikeLogic.getBicicleta(bicicletaId) == null) {
            throw new WebApplicationException("El recurso /bicicletas/" + bicicletaId + " no existe.", 404);
        }
        BicicletaDTO bikeDTO = new BicicletaDTO(marcaBicicletasLogic.addBicicleta(bicicletaId, marcaNombre));
        return bikeDTO;
    }
    
    /**
     * Busca y devuelve todos las bicicletas que existen en la marca.
     *
     * @param marcaNombre Nombre de la marca que se esta
     * buscando. Este debe ser una cadena de caracteres.
     * @return JSONArray {@link BicicletaDetailDTO} - Las bicicletas encontradas
     * en la marca. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<BicicletaDetailDTO> getBicicletas(@PathParam("marcaNombre") String marcaNombre) {
        List<BicicletaDetailDTO> listaDetailDTOs = listEntity2DTO(marcaBicicletasLogic.getBicicletas(marcaNombre));
        return listaDetailDTOs;
    }
    
    /**
     * Busca la bicicleta con el id asociado dentro de la marca con el
     * nombre asociado.
     *
     * @param marcaNombre
     * @param bikeId
     * @return
     * @throws BusinessLogicException Error de lógica que se genera cuando no se
     * encuentra la bicicleta en la marca.
     */
    @GET
    @Path("{bicicletaId: \\d+}")
    public BicicletaDetailDTO getBicicleta(@PathParam("marcaNombre") String marcaNombre, @PathParam("bicicletaId") Long bikeId) throws BusinessLogicException {
        if (bikeLogic.getBicicleta(bikeId) == null) {
            throw new WebApplicationException("El recurso /marcas/" + marcaNombre + "/bicicletas/" + bikeId + " no existe.", 404);
        }
        BicicletaDetailDTO bikeDetailDTO = new BicicletaDetailDTO(marcaBicicletasLogic.getBicicleta(marcaNombre, bikeId));
         return bikeDetailDTO;
    }
    
    /**
     * Remplaza las instancias de Bicicleta asociadas a una instancia de
     * Marca
     *
     * @param marcaNombre Identificador de la marca que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param bicicletas JSONArray {@link BookDTO} El arreglo de bicicletas
     * nuevo para la marca.
     * @return JSON {@link List<BicicletaDetailDTO>} - El arreglo de bicicletas guardado en la
     * marca.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la bici.
     */
    @PUT
    public List<BicicletaDetailDTO> replaceBicicletas(@PathParam("marcaNombre") String marcaNombre, List<BicicletaDetailDTO> bicicletas) {
        for (BicicletaDetailDTO bike : bicicletas) {
            if (bikeLogic.getBicicleta(bike.getId()) == null) {
                throw new WebApplicationException("El recurso /bicicletas/" + bike.getId() + " no existe.", 404);
            }
        }
        List<BicicletaDetailDTO> listaDetailDTOs = listEntity2DTO(marcaBicicletasLogic.replaceBicicletas(marcaNombre, listDTO2Entity(bicicletas)));
        return listaDetailDTOs;
    }
    
    /**
     *Convierte una lista de Entity a una lista de DetailDTO.
     * @param bicicletas
     * @return
     */
    private List<BicicletaDetailDTO> listEntity2DTO(List<BicicletaEntity> bicicletas) {
        List<BicicletaDetailDTO> list = new ArrayList();
        for (BicicletaEntity entity : bicicletas) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de BicicletaDetailDTO a una lista de
     * BicicletakEntity.
     *
     * @param dtos Lista de BicicletaDetailDTO a convertir.
     * @return Lista de BicicletakEntity convertida.
     */
    private List<BicicletaEntity> listDTO2Entity(List<BicicletaDetailDTO> dtos) {
        List<BicicletaEntity> list = new ArrayList<>();
        for (BicicletaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
