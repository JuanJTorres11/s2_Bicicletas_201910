/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import java.util.List;
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

    public OrdenEntity create(OrdenEntity entity) {
        em.persist(entity);
        return entity;
    }

    public List<OrdenEntity> findAll() {
        TypedQuery<OrdenEntity> query = em.createQuery("select u from OrdenEntity u", OrdenEntity.class);
        return query.getResultList();
    }
    
    public OrdenEntity find(long id){
        return em.find(OrdenEntity.class, id);
    }
}
