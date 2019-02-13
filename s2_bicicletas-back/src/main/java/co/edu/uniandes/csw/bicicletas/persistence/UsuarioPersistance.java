/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.UsuarioEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan José Torres
 */
@Stateless
public class UsuarioPersistance
{
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistance.class.getName());
    
    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;
}

public UsuarioEntity create (UsuarioEntity user)
{
LOGGER.log(Level.INFO, "Creando una usuario nuevo");
/* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
*/
em.persist(user);
LOGGER.log(Level.INFO, "Saliendo de crear un usuario nuevo");
return user;
}