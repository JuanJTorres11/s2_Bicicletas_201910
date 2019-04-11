/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan José Torres <jj.torresr@uniandes.edu.co>
 */
@Stateless
public class VendedorPersistence
{

    private static final Logger LOGGER = Logger.getLogger(VendedorPersistence.class.getName());

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    /**
     * Crea un nuevo vendedor
     * @param vendedor Vendedor a crear
     * @return Representación del vendedor en la BD.
     */
    public VendedorEntity create(VendedorEntity vendedor)
    {
        LOGGER.log(Level.INFO, "Creando una vendedor nuevo");
        em.persist(vendedor);
        LOGGER.log(Level.INFO, "Saliendo de crear un usuario nuevo");
        return vendedor;
    }

    /**
     * Busca un vendedor por su id
     * @param id Identificador del vendedor
     * @return Vendedor con el id dado (si existe)
     */
    public VendedorEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando el vendedor con id: " + id);
        return em.find(VendedorEntity.class, id);
    }

    /**
     * Retorna una lista con todos los vendedores en la BD
     * @return lista de todos los vendedores.
     */
    public List<VendedorEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los vendedores");
        // Se crea un query para buscar todas las editoriales en la base de datos.
        TypedQuery query = em.createQuery("select u from VendedorEntity u", VendedorEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza un vendedor
     * @param user Vendedor con la información actualizada
     * @return Representación que quedó en la bd.
     */
    public VendedorEntity update(VendedorEntity user)
    {
        LOGGER.log(Level.INFO, "Actualizando al vendedor con id: ", user.getId());
        em.merge(user);
        LOGGER.log(Level.INFO, "Saliendo de actualizar el vendedor con id: ", user.getId());
        return user;
    }

    /**
     * Elimina un Vendedor dado su id
     * @param id Identificador del vendedor a eliminar.
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando vendedor con id: " + id);
        // Se busca el que se va a borrar
        VendedorEntity entity = em.find(VendedorEntity.class, id);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el vendedor con id: " + id);
    }

    /**
     * Busca un vendedor por su login y contraseña
     * @param login Login del vendedor
     * @param contraseña Contraseña del vendedor
     * @return Vendedor con 
     */
    public VendedorEntity authVendedor (String login, String contraseña)
    {
        LOGGER.log(Level.INFO, "Consultando vendedor por login ", login);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From VendedorEntity e where e.login = :log and e.password = :pass", VendedorEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("log", login);
        query = query.setParameter("pass", contraseña);
        // Se invoca el query se obtiene la lista resultado
        List<VendedorEntity> logins = query.getResultList();
        VendedorEntity result;

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

        LOGGER.log(Level.INFO, "Saliendo de consultar vendedor por login ", login);
        return result;
    }
    
    /**
     * Encuentra un Vendedor por su login
     * @param login Login del vendedor a buscar
     * @return Vendedor con login dado (si existe)
     */
    public VendedorEntity findByLogin (String login)
    {
        LOGGER.log(Level.INFO, "Consultando vendedor por login ", login);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From VendedorEntity e where e.login = :log", VendedorEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("log", login);
        // Se invoca el query se obtiene la lista resultado
        List<VendedorEntity> logins = query.getResultList();
        VendedorEntity result;

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

        LOGGER.log(Level.INFO, "Saliendo de consultar vendedor por login ", login);
        return result;
    }
}
