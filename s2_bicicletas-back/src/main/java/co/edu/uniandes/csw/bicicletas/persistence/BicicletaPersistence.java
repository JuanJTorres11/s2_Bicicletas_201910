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
 * Clase que maneja la persistencia para Bicicleta. Se conecta a través del Entity
 * @author Andrea
 */
@Stateless
public class BicicletaPersistence {

    private static final Logger LOGGER = Logger.getLogger(BicicletaPersistence.class.getName());

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     * @param bikeEntity objeto bicicleta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BicicletaEntity create(BicicletaEntity bikeEntity) {

        em.persist(bikeEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una bicicleta nueva");
        return bikeEntity;
    }

    /**
     * Busca si hay alguna bicicleta con el id que se envía de argumento
     * @param bikeID id correspondiente a la bicicleta buscado.
     * @return la bicicleta
     */
    public BicicletaEntity find(Long bikeID) {
        LOGGER.log(Level.INFO, "Consultando bicicleta con id={0}", bikeID);
        return em.find(BicicletaEntity.class, bikeID);
    }
    
     /**
     * Devuelve todas las bicicletas de la base de datos.
     *
     * @return una lista con todos las bicicletas que encuentre en la base de datos,
     * "select u from BicicletaEntity u" es como un "select * from BicicletaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
   public List<BicicletaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las bicicletas");
        TypedQuery query = em.createQuery("select u from BicicletaEntity u", BicicletaEntity.class);
        return query.getResultList();
    }

   /**
    * Actualiza un libro.
    * @param bikeEntity la bicicleta que viene con los nuevos cambios. Por ejemplo
    * el precio pudo cambiar. En ese caso, se haria uso del método update.
    * @return una bicicleta con los cambios aplicados.
    */
    public BicicletaEntity update(BicicletaEntity bikeEntity) {
        LOGGER.log(Level.INFO, "Actualizando bicicleta con id = {0}", bikeEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la bicicleta con id = {0}", bikeEntity.getId());
        return em.merge(bikeEntity);
    }

    /**
     * Borra una bicicleta de la base de datos recibiendo como argumento el id de la bicicleta
     * @param bikeId id correspondiente a la bicicleta que se quiere borrar.
     */
    public void delete(Long bikeId) {
        LOGGER.log(Level.INFO, "Borrando bicicleta con id = {0}", bikeId);
        BicicletaEntity entity = em.find(BicicletaEntity.class, bikeId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la bicicleta con id = {0}", bikeId);
    }
    
    /**
     * Busca si hay alguna bicicleta con la referencia que se envía de argumento
     * @param referencia referencia de la bicicleta que se está buscando
     * @return null si no existe ninguna bicicleta con la referencia del argumento. Si
     * existe alguna devuelve la primera.
     */
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
