/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.OrdenDTO;
import co.edu.uniandes.csw.bicicletas.dtos.OrdenDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.OrdenLogic;
import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Mateo
 */
@Path("ordenes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class OrdenResource {

    private static final Logger LOGGER = Logger.getLogger(OrdenResource.class.getName());

    @Inject
    private OrdenLogic ordenLogic;

    @POST
    public OrdenDTO createOrden(OrdenDTO orden) throws BusinessLogicException {
        OrdenEntity ordenEntity = orden.toEntity();
        OrdenEntity nuevaOrdenEntity = ordenLogic.createOrden(ordenEntity);
        OrdenDTO nuevaOrdenDTO = new OrdenDTO(nuevaOrdenEntity);
        return nuevaOrdenDTO;
    }

    @GET
    @Path("{id: \\d+}")
    public OrdenDetailDTO getOrden(@PathParam("id") long id) throws WebApplicationException {

        OrdenEntity ordenEntity = ordenLogic.getOrden(id);
        if (ordenEntity == null) {
            throw new WebApplicationException("El recurso /ordenes/" + id + " no existe.", 404);
        }
        OrdenDetailDTO detailDTO = new OrdenDetailDTO(ordenEntity);
        return detailDTO;
    }

    @GET
    public List<OrdenDetailDTO> getOrdenes() {
        List<OrdenDetailDTO> listaOrdenes = listEntity2DetailDTO(ordenLogic.getOrdenes());
        return listaOrdenes;
    }
    
    

    private List<OrdenDetailDTO> listEntity2DetailDTO(List<OrdenEntity> entityList) {
        List<OrdenDetailDTO> list = new ArrayList<>();
        for (OrdenEntity entity : entityList) {
            list.add(new OrdenDetailDTO(entity));
        }
        return list;
}

}
