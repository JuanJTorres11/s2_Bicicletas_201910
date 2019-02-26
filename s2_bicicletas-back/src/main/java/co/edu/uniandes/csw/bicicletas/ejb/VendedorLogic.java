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
    private VendedorPersistence pVendedor;

    @Inject
    private CompradorPersistence pComprador;

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
            if (user.getTelefono().length()!= 10 || user.getTelefono().charAt(0) == '3')
                throw new BusinessLogicException("El telefono no es valido");
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
}
