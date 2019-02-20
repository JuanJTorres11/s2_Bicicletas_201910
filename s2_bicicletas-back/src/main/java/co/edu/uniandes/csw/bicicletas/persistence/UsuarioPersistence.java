/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan José Torres
 */
@Stateless
public class UsuarioPersistence
{

    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    public UsuarioEntity create(UsuarioEntity user)
    {
        LOGGER.log(Level.INFO, "Creando una usuario nuevo");
        em.persist(user);
        LOGGER.log(Level.INFO, "Saliendo de crear un usuario nuevo");
        return user;
    }

    public UsuarioEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando el usuario con id: " + id);
        return em.find(UsuarioEntity.class, id);
    }

    public List<UsuarioEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todas los usuarios");
        // Se crea un query para buscar todas las editoriales en la base de datos.
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }

    public UsuarioEntity update(UsuarioEntity user)
    {
        LOGGER.log(Level.INFO, "Actualizando al usuario con id: ", user.getId());
        em.merge(user);
        LOGGER.log(Level.INFO, "Saliendo de actualizar el usuario con id: ", user.getId());
        return user;
    }

    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando usuario con id: " + id);
        // Se busca el que se va a borrar
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el usuario con id: " + id);
    }

    public UsuarioEntity findByLogin(String login)
    {
        LOGGER.log(Level.INFO, "Consultando usuario por login ", login);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.login = :log", UsuarioEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("log", login);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> logins = query.getResultList();
        UsuarioEntity result;

        if (logins == null)
        {
            result = null;
        }
        else if (logins.isEmpty())
        {
            result = null;
        }
        else
        {
            result = logins.get(0);
        }

        LOGGER.log(Level.INFO, "Saliendo de consultar usuario por login ", login);
        return result;
    }
}
