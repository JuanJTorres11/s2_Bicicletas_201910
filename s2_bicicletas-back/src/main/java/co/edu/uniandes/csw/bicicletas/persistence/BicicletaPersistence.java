/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
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
public class BicicletaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(BicicletaPersistence.class.getName());


    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    public BicicletaEntity create(BicicletaEntity resenaEntity) {

        em.persist(resenaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una bicicleta nueva");
        return resenaEntity;
    }

    public BicicletaEntity find(Long resenaId) {
        LOGGER.log(Level.INFO, "Consultando bicicleta con id={0}", resenaId);
        return em.find(BicicletaEntity.class, resenaId);
    }

    public List<BicicletaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las bicicletas");
        TypedQuery query = em.createQuery("select u from BicicletaEntity u", BicicletaEntity.class);
        return query.getResultList();
    }

    public BicicletaEntity update(BicicletaEntity resenaEntity) {
        LOGGER.log(Level.INFO, "Actualizando bicicleta con id = {0}", resenaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la bicicleta con id = {0}", resenaEntity.getId());
        return em.merge(resenaEntity);
    }

    public void delete(Long resenaId) {
        LOGGER.log(Level.INFO, "Borrando bicicleta con id = {0}", resenaId);
        BicicletaEntity entity = em.find(BicicletaEntity.class, resenaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la bicicleta con id = {0}", resenaId);
    }

}
