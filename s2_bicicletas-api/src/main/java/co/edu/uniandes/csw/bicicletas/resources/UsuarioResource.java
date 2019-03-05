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
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource
{

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    @GET
    public List<UsuarioDTO> darUsuarios()
    {
        return null;
    }

}
