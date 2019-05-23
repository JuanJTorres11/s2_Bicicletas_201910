/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class CompradorPersistence {

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param pCompradorEntity objeto Comprador que se creará en la base de
     * datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CompradorEntity create(CompradorEntity pCompradorEntity) {
        em.persist(pCompradorEntity);
        return pCompradorEntity;
    }

    /**
     * Busca si hay alguna comprador con el id que se envía de argumento
     *
     * @param compradorID: id correspondiente a la comprador buscada.
     * @return un comprador.
     */
    public CompradorEntity find(Long compradorID) {
        return em.find(CompradorEntity.class, compradorID);
    }

    /**
     *
     * Borra una comprador de la base de datos recibiendo como argumento el id
     * de la compador
     *
     * @param id: id correspondiente a la comprador a borrar.
     */
    public void delete(Long id) {
        CompradorEntity enti = em.find(CompradorEntity.class, id);
        em.remove(enti);
    }

    /**
     * Actualiza un comprador.
     *
     * @param pComprador: la comprador que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una Comprador con los cambios aplicados.
     */
    public CompradorEntity update(CompradorEntity pComprador) {
        return em.merge(pComprador);
    }

    /**
     * Devuelve todas las compradores de la base de datos.
     *
     * @return una lista con todas las compradores que encuentre en la base de
     * datos, "select u from CompradorEntity u" es como un "select * from
     * CompradorEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CompradorEntity> findAll() {
        TypedQuery query = em.createQuery("select u from CompradorEntity u", CompradorEntity.class);
        return query.getResultList();
    }

    /**
     * Buscar una comprador
     *
     * Busca si hay algun comprador con un login específico
     *
     * @param login El ID del comprador con respecto al cual se busca
     * @return El comprador encontrada o null. Nota: Si existe uno o mas.
     * devuelve siempre la primera que encuentra
     */
    public CompradorEntity findByLogin(String login) {
        TypedQuery query = em.createQuery("Select e From CompradorEntity e where e.login = :login", CompradorEntity.class);
        query = query.setParameter("login", login);
        List<CompradorEntity> logins = query.getResultList();
        CompradorEntity result;

        if (logins == null) {
            result = null;
        } else if (logins.isEmpty()) {
            result = null;
        } else {
            result = logins.get(0);
        }

        return result;
    }
    
        /**
     * Busca un vendedor por su login y contraseña
     * @param login Login del vendedor
     * @param contrasena Contraseña del vendedor
     * @return Vendedor con 
     */
    public CompradorEntity authVendedor (String login, String contrasena)
    {
        //LOGGER.log(Level.INFO, "Se autenticará al vendedor con login: ", login);
        TypedQuery query = em.createQuery("Select e From CompradorEntity e where e.login = :log and e.password = :pass", CompradorEntity.class);
        query = query.setParameter("log", login);
        query = query.setParameter("pass", contrasena);
        List<CompradorEntity> vendedores = query.getResultList();
        CompradorEntity result;

        if (vendedores == null || vendedores.isEmpty())
        {
            result = null;
        }
        else
        {
            result = vendedores.get(0);
        }

        //LOGGER.log(Level.INFO, "Saliendo de autenticar al vendedor con login: {0}", login);
        return result;
    }
}
