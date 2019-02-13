/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.UsuarioDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

/**
 * @author Juan José Torres
 */
@Path("bicicletas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    @POST
    public UsuarioDTO crearUsuario(UsuarioDTO user) {
        return user;
    }

    @GET
    public List<UsuarioDTO> darUsuarios() {
        return null;
    }

    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO darUsuarioId(@PathParam("id") long id) {
        return null;
    }

    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO actualizarUsuario(@PathParam("id") long id, UsuarioDTO user) {
        return user;
    }

    @DELETE
    @Path("{id: \\d+}")
    public UsuarioDTO eliminarUsuario(@PathParam("id") long id) {
        return null;
    }
}
