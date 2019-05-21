/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.ItemCarritoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class ItemCarritoPersistence {
    
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
     * @param itemEntity objeto orden que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ItemCarritoEntity create(ItemCarritoEntity itemEntity) {
        LOGGER.log(Level.INFO, "Creando un item nuevo");
        em.persist(itemEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un item nuevo");
        return itemEntity;
    }
    
        /**
     * Devuelve todas los items de la base de datos.
     *
     * @return una lista con todas las listas de items que encuentre en la base de datos.
     */
    public List<ItemCarritoEntity> findAll() {
        TypedQuery<ItemCarritoEntity> query = em.createQuery("select u from ItemCarritoEntity u", ItemCarritoEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca si hay algun item con el id que se envía de argumento
     *
     * @param itemId: id correspondiente al item buscada.
     * @return un item.
     */
    public ItemCarritoEntity find(long itemId) {
        LOGGER.log(Level.INFO, "Consultando item con id={0}", itemId);
        return em.find(ItemCarritoEntity.class, itemId);
    }
    
         /**
     *
     * Borra un item de la base de datos recibiendo como argumento el id de la
     * orden.
     *
     * @param Id: id correspondiente al item a borrar.
     */
    public void delete(Long id) {
        ItemCarritoEntity enti = em.find(ItemCarritoEntity.class, id);
        em.remove(enti);
    }
    
    /**
     * Buscar un item
     *
     * Busca si hay algun item con un id específico
     *
     * @param id El ID del item con respecto al cual se busca
     * @return La item encontrada o null. Nota: Si existe uno o mas.
     * devuelve siempre la primera que encuentra
     */
    public ItemCarritoEntity findById(Long id) {

        TypedQuery query = em.createQuery("Select e From ItemCarritoEntity e where e.id = :id", ItemCarritoEntity.class);

        query = query.setParameter("id", id);

        List<ItemCarritoEntity> ids = query.getResultList();
        ItemCarritoEntity ret;

        if (ids == null) {
            ret = null;
        } else if (ids.isEmpty()) {
            ret = null;
        } else {
            ret = ids.get(0);
        }
        return ret;
    }
    
       /**
    * Actualiza un item.
    * @param itemEntity el item que viene con los nuevos cambios. Por ejemplo
    * el precio pudo cambiar. En ese caso, se haria uso del método update.
    * @return un item con los cambios aplicados.
    */
    public ItemCarritoEntity update(ItemCarritoEntity itemEntity) {
        LOGGER.log(Level.INFO, "Actualizando item con id = {0}", itemEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el item con id = {0}", itemEntity.getId());
        return em.merge(itemEntity);
    }
}
