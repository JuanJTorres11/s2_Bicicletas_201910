/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import co.edu.uniandes.csw.bicicletas.dtos.VendedorDTO;
import co.edu.uniandes.csw.bicicletas.dtos.VendedorDetailDTO;
import co.edu.uniandes.csw.bicicletas.ejb.VendedorLogic;
import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.bicicletas.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;

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

    /**
     * Conexión con el front para crear un nuevo vendedor
     * @param vendedor a crear
     * @return JSON {@link VendedorDTO} El vendedor creado en base de datos
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper}
     */
    @POST
    public VendedorDTO crearVendedor(VendedorDTO vendedor) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Se creará al vendedor con id. {0}", vendedor.getId());
        VendedorDTO nuevo = null;
        nuevo = new VendedorDTO(logica.createVendedor(vendedor.toEntity()));
        return nuevo;
    }

   /**
    * Retorna la lista de todos los vendedores
    * @return JSONArray {@link VendedorDetailDTO} 
    */
    @GET
    public List<VendedorDetailDTO> darVendedores()
    {
        LOGGER.log(Level.INFO, "Se dará la lista con todos los vendedores");
        List<VendedorDetailDTO> vendedores = new ArrayList<>();
        List<VendedorEntity> vEntity = logica.findAllVendedores();
        for (VendedorEntity v : vEntity)
        {
            vendedores.add(new VendedorDetailDTO(v));
        }
        return vendedores;
    }

    /**
     * Retorna el vendedor por id
     *
     * @param id id del vendeodr a buscar
     * @return JSON {@link VendedorDetailDTO} el vendedor con el id si existe
     */
    @GET
    @Path("{id: \\d+}")
    public VendedorDetailDTO darVendedorId(@PathParam("id") long id)
    {
         LOGGER.log(Level.INFO, "Se buscará al vendedor con id: {0}", id);
        VendedorEntity vE = logica.findVendedor(id);
        if (vE != null)
        {
            return new VendedorDetailDTO(vE);
        }
        else
        {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
    }

    /**
     * ACtualiza la información de un Vendedor ya registrado
     *
     * @param id id del vendedor a actualizar
     * @param vendedor objeto con la información actualizada
     * @return El nuevo objeto guardado en la base de datos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper}
     * Se lanza si alguno de los datos a
     * actualizar incumple con las reglas de negocio.
     */
    @PUT
    @Path("{id: \\d+}")
    public VendedorDetailDTO actualizarVendedor(@PathParam("id") long id, VendedorDetailDTO vendedor) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Se actualizará al vendedor con id: {0}", id);
        VendedorEntity vE = logica.findVendedor(id);
        if (vE != null)
        {
            return new VendedorDetailDTO(logica.updateVendedor(vE));
        }
        else
        {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
    }

    /**
     * Elimina un vendedor con un id dado, si existe.
     * @param id id del vendedor a borrar.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarVendedor(@PathParam("id") long id)
    {
        LOGGER.log(Level.INFO, "Se eliminará al vendedor con id: {0}", id);
        if (logica.findVendedor(id) != null)
        {
            logica.deleteVendedor(id);
        }
        else
        {
            
        }
    }

    /**
     * Redirecciona a la clase de asociación entre Vendedor y Medios de Pago
     * @param id Identificador del vendedor
     * @return Clase que maneja los métodos CRUD de la relación Vendedor/MediosPago
     */
     @Path("{vendedorId: \\d+}/mediosPago")
    public Class<VendedorMedioPagoResource> getMediosPagoResource(@PathParam("vendedorId") Long id)
    {
        if (logica.findVendedor(id) == null)
        {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
        return VendedorMedioPagoResource.class;
    }
    
     /**
     * Redirecciona a la clase de asociación entre Vendedor y Venta
     * @param id Identificador del vendedor
     * @return Clase que maneja los métodos CRUD de la relación Vendedor/MediosPago
     */
     @Path("{vendedorId: \\d+}/ventas")
    public Class<VendedorVentaResource> getVentasResource(@PathParam("vendedorId") Long id)
    {
        if (logica.findVendedor(id) == null)
        {
            throw new WebApplicationException("El vendedor con id: " + id + " no existe", 404);
        }
        return VendedorVentaResource.class;
    }
}