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

    private static final Logger LOGGER = Logger.getLogger(MarcaResource.class.getName());

    @Inject
    private MarcaLogic marcaLogic;

    @POST
    public MarcaDTO createMarca(MarcaDTO marca) throws BusinessLogicException {
        MarcaEntity marcaEntity = marca.toEntity();
        MarcaEntity nuevoMarcaEntity = marcaLogic.createMarca(marcaEntity);
        MarcaDTO nuevoMarcaDTO = new MarcaDTO(nuevoMarcaEntity);
        return nuevoMarcaDTO;
    }

    @GET
    public List<MarcaDetailDTO> getMarcas() {
        List<MarcaDetailDTO> listaMarcaes = listEntity2DetailDTO(marcaLogic.getMarcas());
        return listaMarcaes;
    }

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

    @DELETE
    @Path("{id: \\d+}")
    public void deleteMarca(@PathParam("id") long id)throws WebApplicationException, BusinessLogicException {
        if (marcaLogic.getMarca(id) == null) {
            throw new WebApplicationException("El recurso /marcas/" + id + " no existe.", 404);
        }
        marcaLogic.deleteMarca(id);
    }

    private List<MarcaDetailDTO> listEntity2DetailDTO(List<MarcaEntity> entityList) {
        List<MarcaDetailDTO> list = new ArrayList<>();
        for (MarcaEntity entity : entityList) {
            list.add(new MarcaDetailDTO(entity));
        }
        return list;
    }
}
