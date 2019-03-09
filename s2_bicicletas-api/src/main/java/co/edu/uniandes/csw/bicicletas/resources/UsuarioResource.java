/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.UsuarioDTO;
import java.util.ArrayList;
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

    /**
     * Retorna una lista con todos los usuarios registrados entre vendedores y
     * compradores.
     * @return lista de todos los usuarios
     */
    @GET
    public List<UsuarioDTO> darUsuarios()
    {
        ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        usuarios.addAll(new VendedorResource().darVendedores());
        usuarios.addAll(new CompradorResource().darCompradores());
        return usuarios;
    }
}