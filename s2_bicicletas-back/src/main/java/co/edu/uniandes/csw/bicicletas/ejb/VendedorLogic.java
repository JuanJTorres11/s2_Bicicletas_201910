/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.VendedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Juan José Torres <jj.torresr@uniandes.edu.co>
 */
public class VendedorLogic
{

    private static final Logger LOGGER = Logger.getLogger(VendedorLogic.class.getName());

    @Inject
    /**
     * Persistencia de vendedor para probar sus métodos.
     */
    private VendedorPersistence pVendedor;

    @Inject
    /**
     * Persistencia de comprador para verificar que no hayan logins repetidos
     */
    private CompradorPersistence pComprador;

    /**
     * Se crea un nuevo vendedor
     * @param user usuaio a crear en el sistema
     * @return el usuario creado
     * @throws BusinessLogicException si se rompe alguna regla de negocio. <br>
     * 1.Ninguno de los atributos es vacio o nulo. <br>
     * 2.No hay otro vendedor o comprador con el mismo login. <br>
     * 3.El número de telefono de celular es valido (10 digitos y empieza por 3)
     */
    VendedorEntity createVendedor(VendedorEntity user) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de usuario ");
        if (user.getLogin() != null && !user.getLogin().equals(""))
        {
            if (pVendedor.findByLogin(user.getLogin()) != null)
            {
                throw new BusinessLogicException("Ya existe un vendedor con ese Login");
            }

            if (pComprador.findByLogin(user.getLogin()) != null)
            {
                throw new BusinessLogicException("Ya existe un comprador con ese Login");
            }
        }
        else
        {
            throw new BusinessLogicException("El login no es valido");
        }

        if (user.getPassword() == null || user.getPassword().equals(""))
        {
            throw new BusinessLogicException("La contraseña no es valida");
        }
        if (user.getNombre() == null || user.getNombre().equals(""))
        {
            throw new BusinessLogicException("El nombre no es valido");
        }
        if (user.getTelefono() != null && !user.getTelefono().equals(""))
        {
            if (user.getTelefono().length() != 10 || user.getTelefono().charAt(0) == '3')
            {
                throw new BusinessLogicException("El telefono no es valido");
            }
            try
            {
                Long.parseLong(user.getTelefono());
            }
            catch (Exception e)
            {
                throw new BusinessLogicException("El telefono " + user.getTelefono() + " no es valido" + e.getMessage());
            }
        }
        else
        {
            throw new BusinessLogicException("El telefono no puede ser vacio");
        }
        pVendedor.create(user);
        LOGGER.log(Level.INFO, "Se creó el usuario");
        return user;
    }

    /**
     * Se retorna el vendedor con id asignado.
     * @param id ID del vendedor a buscar
     * @return vendedor con id o null si no existe.
     */
    public VendedorEntity findVendedor(Long id)
    {
        LOGGER.log(Level.INFO, "Se buscará el vendedor con id " + id);
        VendedorEntity buscado = pVendedor.find(id);
        if (buscado == null)
        {
            LOGGER.log(Level.SEVERE, "No existe el vendedor con id " + id);
        }
        LOGGER.log(Level.INFO, "Se termina la busqueda del vendedor con id " + id);
        return buscado;
    }

    /**
     * Se retornan todos los vendedores.    
    */
    public List<VendedorEntity> findAllVendedores()
    {
        LOGGER.log(Level.INFO, "se buscarán todos los vendedores");
        List<VendedorEntity> vendedores = pVendedor.findAll();
        if (vendedores == null || vendedores.isEmpty())
        {
            LOGGER.log(Level.SEVERE, "No existen vendedores");
        }
        LOGGER.log(Level.INFO, "Se termina la busqueda de los vendedores");
        return vendedores;
    }

     /**
     * Se actualiza un nuevo vendedor
     * @param nuevo usuaio a actualizar en el sistema
     * @return el usuario actualizado
     * @throws BusinessLogicException si se rompe alguna regla de negocio. <br>
     * 1.Ninguno de los atributos es vacio o nulo. <br>
     * 2.No hay otro vendedor o comprador con el mismo login. <br>
     * 3.El número de telefono de celular es valido (10 digitos y empieza por 3)
     */
    public VendedorEntity updateVendedor(VendedorEntity nuevo) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de usuario ");
        if (nuevo.getLogin() != null && !nuevo.getLogin().equals(""))
        {
            if (pVendedor.findByLogin(nuevo.getLogin()) != null)
            {
                throw new BusinessLogicException("Ya existe un vendedor con ese Login");
            }

            if (pComprador.findByLogin(nuevo.getLogin()) != null)
            {
                throw new BusinessLogicException("Ya existe un comprador con ese Login");
            }
        }
        else
        {
            throw new BusinessLogicException("El login no es valido");
        }

        if (nuevo.getPassword() == null || nuevo.getPassword().equals(""))
        {
            throw new BusinessLogicException("La contraseña no es valida");
        }
        if (nuevo.getNombre() == null || nuevo.getNombre().equals(""))
        {
            throw new BusinessLogicException("El nombre no es valido");
        }
        if (nuevo.getTelefono() != null && !nuevo.getTelefono().equals(""))
        {
            if (nuevo.getTelefono().length() != 10 || nuevo.getTelefono().charAt(0) == '3')
            {
                throw new BusinessLogicException("El telefono no es valido");
            }
            try
            {
                Long.parseLong(nuevo.getTelefono());
            }
            catch (Exception e)
            {
                throw new BusinessLogicException("El telefono " + nuevo.getTelefono() + " no es valido" + e.getMessage());
            }
        }
        else
        {
            throw new BusinessLogicException("El telefono no puede ser vacio");
        }

        VendedorEntity bd = pVendedor.update(nuevo);
        LOGGER.log(Level.INFO, "Se termina de actualizar el vendedor");
        return bd;
    }
    
    /**
     * Se elimina al vendedor con id dado
     * @param id ID del vendedor a eliminar.
     */
    public void deleteVendedor (Long id)
    {
        LOGGER.log(Level.INFO, "se borrará el Vendedor con id " + id);
        LOGGER.log(Level.INFO, "se borró al vendedor con id " + id);
        pVendedor.delete(id);
    }
}
