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
 * Clase que maneja la persistencia para Reseña. Se conecta a través del Entity
 * @author Andrea
 */
@Stateless
public class ResenaPersistence {

    private static final Logger LOGGER = Logger.getLogger(ResenaPersistence.class.getName());

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

     /**
     * Método para persisitir la reseña en la base de datos.
     * @param resenaEntity objeto resena que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ResenaEntity create(ResenaEntity resenaEntity) {

        em.persist(resenaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una resena nueva");
        return resenaEntity;
    }

/**
     * Buscar una reseña
     *
     * Busca si hay alguna reseña asociada a una bicicleta y con un ID específico
     *
     * @param bicicletaId El ID de la bicicleta con respecto al cual se busca
     * @param resenaId El ID de la reseña buscada
     * @return La reseña encontrada o null. Nota: Si existe una o más reseñas
     * devuelve siempre la primera que encuentra
     */    
    public ResenaEntity find(Long bicicletaId, Long resenaId) {
        LOGGER.log(Level.INFO, "Consultando resena con id={0}", resenaId);
         TypedQuery<ResenaEntity> q = em.createQuery("select p from ResenaEntity p where (p.bicicleta.id = :bicicletaId) and (p.id = :resenaId)", ResenaEntity.class);

        q.setParameter("bicicletaId", bicicletaId);
        q.setParameter("resenaId", resenaId);
        List<ResenaEntity> results = q.getResultList();
        ResenaEntity review = null;
        if (results == null) {
            review = null;
        } else if (results.isEmpty()) {
            review = null;
        } else if (results.size() >= 1) {
            review = results.get(0);
        }
        return review;
    }
    
    
    /**
     * Busca y retorna todas las reseñas asociadas a una bicicleta
     * @param bicicletaId El ID de la bicicleta con respecto al cual se busca
     * @return una lista de reseñas o null si la bicicleta no tiene reseñas
     */
   public List<ResenaEntity> findAll(Long bicicletaId) {
        
        LOGGER.log(Level.INFO, "Consultando resenas de bicicleta con id={0}", bicicletaId);
         TypedQuery<ResenaEntity> q = em.createQuery("select p from ResenaEntity p where (p.bicicleta.id = :bicicletaId))", ResenaEntity.class);

        q.setParameter("bicicletaId", bicicletaId);
        List<ResenaEntity> results = q.getResultList();
         if (results == null) {
            return null;
        } else if (results.isEmpty()) {
            return null;
        } 
        return results;
    }

    /**
     * Actualizar una reseña
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param resenaEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public ResenaEntity update(ResenaEntity resenaEntity) {
        LOGGER.log(Level.INFO, "Actualizando resena con id = {0}", resenaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la resena con id = {0}", resenaEntity.getId());
        return em.merge(resenaEntity);
    }

     /**
     * Eliminar una reseña
     *
     * Elimina la reseña asociada al ID que recibe
     *
     * @param resenaId El ID de la reseña que se desea borrar
     */
    public void delete(Long resenaId) {
        LOGGER.log(Level.INFO, "Borrando resena con id = {0}", resenaId);
        ResenaEntity entity = em.find(ResenaEntity.class, resenaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la resena con id = {0}", resenaId);
    }

}
