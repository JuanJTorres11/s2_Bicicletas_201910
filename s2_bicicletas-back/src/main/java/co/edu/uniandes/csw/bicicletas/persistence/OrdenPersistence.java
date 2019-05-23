/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateo
 */
@Stateless
public class OrdenPersistence {

    /**
     * Logger que permite imprimir eventos
     */
    private static final Logger LOGGER = Logger.getLogger(OrdenPersistence.class.getName());

    /**
     * Manejador de la persistencia
     */
    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param ordenEntity objeto orden que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public OrdenEntity create(OrdenEntity ordenEntity) {
        LOGGER.log(Level.INFO, "Creando una orden nueva");
        em.persist(ordenEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una orden nueva");
        return ordenEntity;
    }

    /**
     * Devuelve todas las ordenes de la base de datos.
     *
     * @return una lista con todas las ordenes que encuentre en la base de datos.
     */
    public List<OrdenEntity> findAll() {
        TypedQuery<OrdenEntity> query = em.createQuery("select u from OrdenEntity u", OrdenEntity.class);
        return query.getResultList();
    }

     /**
     * Busca si hay alguna orden con el id que se envía de argumento
     *
     * @param ordenesId: id correspondiente a la orden buscada.
     * @return una orden.
     */
    public OrdenEntity find(long ordenesId) {
        LOGGER.log(Level.INFO, "Consultando editorial con id={0}", ordenesId);
        return em.find(OrdenEntity.class, ordenesId);
    }
    
    /**
     * Busca una orden asociada a un comprador. 
     *@param compradorId comprador asociado.
     * @param ordenId: id correspondiente a la orden buscada.
     * @return una orden.
     */
    public OrdenEntity findByComprador(Long compradorId, Long ordenId)
    {
        LOGGER.log(Level.INFO, "Consultando la orden con id = {0} del comprador con id = " + compradorId, ordenId);
        TypedQuery<OrdenEntity> q = em.createQuery("select m from OrdenEntity m where (m.comprador.id = :compradorId) and (m.id = :ordenId)", OrdenEntity.class);
        q.setParameter("compradorId", compradorId);
        q.setParameter("ordenId", ordenId);
        List<OrdenEntity> results = q.getResultList();
        OrdenEntity medio = null;
        if (results == null)
            medio = null;
          else if (results.isEmpty())
            medio = null;
          else if (results.size() >= 1)
            medio = results.get(0);
        
        LOGGER.log(Level.INFO, "Saliendo de consultar la orden con id = {0} del comprador con id = " + compradorId, ordenId);
        return medio;
    }
    
     /**
     *
     * Borra una orden de la base de datos recibiendo como argumento el id de la
     * orden.
     *
     * @param Id: id correspondiente a la orden a borrar.
     */
    public void delete(Long id) {
        OrdenEntity enti = em.find(OrdenEntity.class, id);
        em.remove(enti);
    }
}
