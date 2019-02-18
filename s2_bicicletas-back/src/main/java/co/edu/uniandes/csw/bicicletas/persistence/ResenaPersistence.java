/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andrea
 */
@Stateless
public class ResenaPersistence {

    private static final Logger LOGGER = Logger.getLogger(ResenaPersistence.class.getName());

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    public ResenaEntity create(ResenaEntity resenaEntity) {

        em.persist(resenaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una resena nueva");
        return resenaEntity;
    }

    public ResenaEntity find(Long resenaId) {
        LOGGER.log(Level.INFO, "Consultando resena con id={0}", resenaId);
        return em.find(ResenaEntity.class, resenaId);
    }

    public List<ResenaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las resenas");
        TypedQuery query = em.createQuery("select u from ResenaEntity u", ResenaEntity.class);
        return query.getResultList();
    }

    public ResenaEntity update(ResenaEntity resenaEntity) {
        LOGGER.log(Level.INFO, "Actualizando resena con id = {0}", resenaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la resena con id = {0}", resenaEntity.getId());
        return em.merge(resenaEntity);
    }

    public void delete(Long resenaId) {
        LOGGER.log(Level.INFO, "Borrando resena con id = {0}", resenaId);
        ResenaEntity entity = em.find(ResenaEntity.class, resenaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la resena con id = {0}", resenaId);
    }

}
