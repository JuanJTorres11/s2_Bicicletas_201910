/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.VentaDTO;
import javax.ws.rs.*;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Lozano
 */

@Path("ventas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class VentaResource {
    
    /**
     * Logger de programa. 
     */
    private static final Logger LOGGER = Logger.getLogger(VentaResource.class.getName());
    
    /** 
     * Crea una venta.
     * @param pVenta
     * @return Retorna un objeto tipo VentaDTO
     */
    @POST 
    public VentaDTO crearVenta(VentaDTO pVenta)
    {
        return pVenta;
    }
    
    /**
     * Obtiene una venta que se especifica por un id. 
     * @param id id de la venta a buscar.
     * @return retorna la venta respectivo al id.
     */
    @GET 
    @Path("{id: \\d+}")
    public VentaDTO obtenerVenta(@PathParam("id") long id)
    {
        return null;
    }
    
    /**
     * Actualiza la informacion de la venta especificada.
     * @param id id de la venta a actualizar.
     * @return La venta actualizada. 
     */
    @PUT
    @Path("{id: \\d+}")
    public VentaDTO actualizaVenta(@PathParam("id") long id)
    {
        return null;
    }
    
    /**
     * Elimina una venta especifica.
     * @param id id de la venta a eliminar.
     * @return  
     */
    @DELETE
    @Path("{id: \\d+}")
    public VentaDTO eliminarVenta(@PathParam("id") long id)
    {
        return null;
    }
    
//        /**
//     * Retorna una lista con todas las ventas registradas. 
//     */
//    @GET
//    public List<VendedorDetailDTO> darVendedores()
//    {
//        ArrayList<VendedorDetailDTO> vendedores = new ArrayList<VendedorDetailDTO>();
//        List<VendedorEntity> vEntity = logica.findAllVendedores();
//        for (VendedorEntity v : vEntity)
//        {
//            vendedores.add(new VendedorDetailDTO(v));
//        }
//        return vendedores;
//    }
    
    
}
