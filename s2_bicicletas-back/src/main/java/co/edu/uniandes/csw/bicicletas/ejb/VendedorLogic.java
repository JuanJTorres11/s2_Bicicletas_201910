/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
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
    private VendedorPersistence persistencia;
    
    VendedorEntity createVendedor (VendedorEntity user) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de usuario ");
        if (persistencia.findByLogin(user.getLogin()) != null)
            throw new BusinessLogicException("Ya existe ese Login");
      persistencia.create(user);
      LOGGER.log(Level.INFO, "Se creó el usuario");
      return user;
    }
}