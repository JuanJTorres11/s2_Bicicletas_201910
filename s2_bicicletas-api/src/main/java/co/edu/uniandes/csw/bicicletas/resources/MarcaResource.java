/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.MarcaDTO;
import co.edu.uniandes.csw.bicicletas.dtos.MarcaDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.MarcaLogic;
import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author Mateo
 */
@Path("marcas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MarcaResource {

    /**
     * Logica de la marca
     */
    @Inject
    private MarcaLogic marcaLogic;

    /**
     * Servicio que crea una marca y la persiste
     * @param marca Marca a crear y persistir
     * @return marcaDTO. El DTO de la marca creada
     * @throws BusinessLogicException  Si hubo un problema de lógica al crear la marca
     */
    @POST
    public MarcaDTO createMarca(MarcaDTO marca) throws BusinessLogicException {
        MarcaEntity marcaEntity = marca.toEntity();
        MarcaEntity nuevoMarcaEntity = marcaLogic.createMarca(marcaEntity);
        MarcaDTO nuevoMarcaDTO = new MarcaDTO(nuevoMarcaEntity);
        return nuevoMarcaDTO;
    }

    /**
     * Servicio que obtiene la lista de marcas
     * @return Lista de marcas
     */
    @GET
    public List<MarcaDetailDTO> getMarcas() {
        List<MarcaDetailDTO> listaMarcaes = listEntity2DetailDTO(marcaLogic.getMarcas());
        return listaMarcaes;
    }

    /**
     * Servicio que obtiene el detalle de una marca con un id especificado
     * @param id Id de la marca a obtener
     * @return la marca obtenida
     * @throws WebApplicationException Si la marca a obtener no existe
     */
    @GET
    @Path("{id: \\d+}")
    public MarcaDetailDTO getMarca(@PathParam("id") long id) throws WebApplicationException {
        MarcaEntity marcaEntity = marcaLogic.getMarca(id);
        if (marcaEntity == null) {
            throw new WebApplicationException("El recurso /marcas/" + id + " no existe.", 404);
        }
        MarcaDetailDTO detailDTO = new MarcaDetailDTO(marcaEntity);
        return detailDTO;
    }

    /**
     * Servicio que modifica una marca con un id especificado
     * @param id Id de la marca a modificar
     * @param marca Detalle de la marca con los valores a modificar
     * @return Detalle de la marca modificada
     * @throws WebApplicationException Si la marca a modificar no existe
     */
    @PUT
    @Path("{id: \\d+}")
    public MarcaDTO updateMarca(@PathParam("id") long id, MarcaDetailDTO marca) throws WebApplicationException {
        marca.setId(id);
        if (marcaLogic.getMarca(id) == null) {
            throw new WebApplicationException("El recurso /marcas/" + id + " no existe.", 404);
        }
        MarcaDetailDTO detailDTO = new MarcaDetailDTO(marcaLogic.updateMarca(id, marca.toEntity()));
        return detailDTO;
    }

    /**
     * Servicio que elimina una marca con un id especificado
     * @param id Id de la marca a eliminar
     * @throws WebApplicationException Si la marca a eliminar no existe
     * @throws BusinessLogicException Si hubo un error lógico al intentar eliminar la marca
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMarca(@PathParam("id") long id)throws WebApplicationException, BusinessLogicException {
        if (marcaLogic.getMarca(id) == null) {
            throw new WebApplicationException("El recurso /marcas/" + id + " no existe.", 404);
        }
        marcaLogic.deleteMarca(id);
    }

    /**
     * Método auxiliar para convertir una lista de entities a una lista de detalles
     * @param entityList Lista de entities a convertir
     * @return Lista de detalles
     */
    private List<MarcaDetailDTO> listEntity2DetailDTO(List<MarcaEntity> entityList) {
        List<MarcaDetailDTO> list = new ArrayList<>();
        for (MarcaEntity entity : entityList) {
            list.add(new MarcaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Obtener la clase de asociación que relaciona las bicicletas de una marca con la marca
     * @param marcaId Id de la marca cuyas bicicletas se quieren obtener
     * @return Clase de asociación de una marca y sus bicicletas
     */
    @Path("{marcaId: \\d+}/bicicletas")
    public Class<MarcaBicicletasResource> getMarcaBicicletasResource(@PathParam("marcaId") Long marcaId) {
        if (marcaLogic.getMarca(marcaId) == null) {
            throw new WebApplicationException("El recurso /categoriaNombre/" + marcaId + " no existe.", 404);
        }
        return MarcaBicicletasResource.class;
    }
}
