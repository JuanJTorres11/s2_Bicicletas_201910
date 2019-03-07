/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.VendedorDTO;
import co.edu.uniandes.csw.bicicletas.dtos.VendedorDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.VendedorLogic;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
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

/**
 * @author Juan José Torres
 */
@Path("vendedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VendedorResource
{

    private static final Logger LOGGER = Logger.getLogger(VendedorResource.class.getName());

    @Inject
    VendedorLogic logica;

    @POST
    public VendedorDTO crearVendedor(VendedorDTO vendedor)
    {
        VendedorDTO nuevo = null;
        try
        {
            nuevo = new VendedorDTO(logica.createVendedor(vendedor.toEntity()));
            return nuevo;
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(VendedorResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return nuevo;
        }
    }

    @GET
    public List<VendedorDetailDTO> darVendedores()
    {
        return null;
    }

    @GET
    @Path("{id: \\d+}")
    public VendedorDetailDTO darVendedorId(@PathParam("id") long id)
    {
        return null;
    }

    @PUT
    @Path("{id: \\d+}")
    public VendedorDetailDTO actualizarVendedor(@PathParam("id") long id, VendedorDetailDTO vendedor)
    {
        return vendedor;
    }

    @DELETE
    @Path("{id: \\d+}")
    public void eliminarVendedor(@PathParam("id") long id)
    {

    }
}
