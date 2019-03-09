/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.csw.bicicletas.ejb.CategoriaBicicletasLogic;
import co.edu.uniandes.csw.bicicletas.ejb.CategoriaLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
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
 * Clase que implementa el recurso "categoria/{id}/bicicletas".
 *
 * @author Andrea
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaBicicletasResource {

    private static final Logger LOGGER = Logger.getLogger(CategoriaBicicletasResource.class.getName());

    @Inject
    private CategoriaBicicletasLogic categoriaBicicletasLogic;

    @Inject
    private BicicletaLogic bikeLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CategoriaLogic catLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda una bicicleta dentro de una categoria con la informacion que
     * recibe el la URL. Se devuelve la bicicleta que se guarda en la categoria.
     *
     * @param categoriaNombre Identificador de la categoria que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param bicicletaId Identificador de la bicicleta que se desea guardar.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link BicicletaDTO} - La bicicleta guardada en la
     * categoria.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la bicicleta.
     */
    @POST
    @Path("{bicicletaId: \\d+}")
    public BicicletaDTO addBicicleta(@PathParam("categoriaNombre") String categoriaNombre, @PathParam("bicicletaId") Long bicicletaId) {
        LOGGER.log(Level.INFO, "CategoriaBicicletasResource addBicicleta: input: categoriaNombre: {0} , bicicletaId: {1}", new Object[]{categoriaNombre, bicicletaId});
        if (bikeLogic.getBicicleta(bicicletaId) == null) {
            throw new WebApplicationException("El recurso /bicicletas/" + bicicletaId + " no existe.", 404);
        }
        BicicletaDTO bikeDTO = new BicicletaDTO(categoriaBicicletasLogic.addBicicleta(bicicletaId, categoriaNombre));
        LOGGER.log(Level.INFO, "CategoriaBicicletasResource addBicicleta: output: {0}", bikeDTO.toString());
        return bikeDTO;
    }

    /**
     * Busca y devuelve todos las bicicletas que existen en la categoria.
     *
     * @param categoriaNombre Identificador de la categoria que se esta
     * buscando. Este debe ser una cadena de dígitos.
     * @return JSONArray {@link BicicletaDetailDTO} - Las bicicletas encontradas
     * en la categoria. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<BicicletaDetailDTO> getBicicletas(@PathParam("categoriaNombre") String categoriaNombre) {
        LOGGER.log(Level.INFO, "CategoriaBicicletasResource getBicicletas: input: {0}", categoriaNombre);
        List<BicicletaDetailDTO> listaDetailDTOs = listEntity2DTO(categoriaBicicletasLogic.getBicicletas(categoriaNombre));
        LOGGER.log(Level.INFO, "CategoriaBicicletasResource getBicicletas: output: {0}", listaDetailDTOs.toString());
        return listaDetailDTOs;
    }

    /**
     * Busca la bicicleta con el id asociado dentro de la categoria con el
     * nombre asociado.
     *
     * @param categoriaNombre
     * @param bikeId
     * @return
     * @throws BusinessLogicException Error de lógica que se genera cuando no se
     * encuentra la bicicleta en la categoria.
     */
    @GET
    @Path("{bicicletaId: \\d+}")
    public BicicletaDetailDTO getBicicleta(@PathParam("categoriaNombre") String categoriaNombre, @PathParam("bicicletaId") Long bikeId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CategoriaBicicletasResource getBicicleta: input: editorialsID: {0} , booksId: {1}", new Object[]{categoriaNombre, bikeId});
        if (bikeLogic.getBicicleta(bikeId) == null) {
            throw new WebApplicationException("El recurso /categorias/" + categoriaNombre + "/bicicletas/" + bikeId + " no existe.", 404);
        }
        BicicletaDetailDTO bikeDetailDTO = new BicicletaDetailDTO(categoriaBicicletasLogic.getBicicleta(categoriaNombre, bikeId));
        LOGGER.log(Level.INFO, "CategoriaBicicletasResource getBicicleta: output: {0}", bikeDetailDTO.toString());
        return bikeDetailDTO;
    }

    /**
     * Remplaza las instancias de Bicicleta asociadas a una instancia de
     * Categoria
     *
     * @param categoriaNombre Identificador de la categoria que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param bicicletas JSONArray {@link BookDTO} El arreglo de bicicletas
     * nuevo para la categoria.
     * @return JSON {@link BookDTO} - El arreglo de bicicletas guardado en la
     * categoria.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la bici.
     */
    @PUT
    public List<BicicletaDetailDTO> replaceBicicletas(@PathParam("categoriaNombre") String categoriaNombre, List<BicicletaDetailDTO> bicicletas) {
        LOGGER.log(Level.INFO, "CategoriaBicicletasResource replaceBicicletas: input: categoriaNombre: {0} , books: {1}", new Object[]{categoriaNombre, bicicletas.toString()});
        for (BicicletaDetailDTO bike : bicicletas) {
            if (bikeLogic.getBicicleta(bike.getId()) == null) {
                throw new WebApplicationException("El recurso /bicicletas/" + bike.getId() + " no existe.", 404);
            }
        }
        List<BicicletaDetailDTO> listaDetailDTOs = listEntity2DTO(categoriaBicicletasLogic.replaceBicicletas(categoriaNombre, listDTO2Entity(bicicletas)));
        LOGGER.log(Level.INFO, "CategoriaBicicletasResource replaceBicicletas: output: {0}", listaDetailDTOs.toString());
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
