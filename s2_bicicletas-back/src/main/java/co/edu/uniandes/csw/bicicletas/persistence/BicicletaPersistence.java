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

    public BicicletaEntity create(BicicletaEntity bikeEntity) {

        em.persist(bikeEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una bicicleta nueva");
        return bikeEntity;
    }

    public BicicletaEntity find(Long bikeID) {
        LOGGER.log(Level.INFO, "Consultando bicicleta con id={0}", bikeID);
        return em.find(BicicletaEntity.class, bikeID);
    }

    public List<BicicletaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las bicicletas");
        TypedQuery query = em.createQuery("select u from BicicletaEntity u", BicicletaEntity.class);
        return query.getResultList();
    }

    public BicicletaEntity update(BicicletaEntity bikeEntity) {
        LOGGER.log(Level.INFO, "Actualizando bicicleta con id = {0}", bikeEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la bicicleta con id = {0}", bikeEntity.getId());
        return em.merge(bikeEntity);
    }

    public void delete(Long bikeId) {
        LOGGER.log(Level.INFO, "Borrando bicicleta con id = {0}", bikeId);
        BicicletaEntity entity = em.find(BicicletaEntity.class, bikeId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la bicicleta con id = {0}", bikeId);
    }

    public BicicletaEntity findByReferencia(String referencia) {
        LOGGER.log(Level.INFO, "Consultando bicicleta por referencia", referencia);

        // Se crea un query para buscar bicicletas con la referencia que recibe el método como argumento. ":ref" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From BicicletaEntity e where e.referencia = :ref", BicicletaEntity.class);
        // Se remplaza el placeholder ":ref" con el valor del argumento 
        query = query.setParameter("ref", referencia);
        
        // Se invoca el query se obtiene la lista resultado
        List<BicicletaEntity> sameRef = query.getResultList();
        BicicletaEntity result;
        
        if (sameRef == null) {
            result = null;
        } else if (sameRef.isEmpty()) {
            result = null;
        } else {
            result = sameRef.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar bicicleta por referencia ", referencia);
        return result;
    }
    
    
    

}
