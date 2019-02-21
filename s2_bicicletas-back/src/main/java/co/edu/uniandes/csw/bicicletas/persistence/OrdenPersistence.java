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

    private static final Logger LOGGER = Logger.getLogger(OrdenPersistence.class.getName());

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
}
