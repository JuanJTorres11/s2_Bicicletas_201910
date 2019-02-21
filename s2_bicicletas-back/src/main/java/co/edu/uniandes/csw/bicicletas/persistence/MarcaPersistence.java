/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
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
public class MarcaPersistence {

    private static final Logger LOGGER = Logger.getLogger(MarcaPersistence.class.getName());

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param marcaEntity objeto marca que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MarcaEntity create(MarcaEntity marcaEntity) {
        LOGGER.log(Level.INFO, "Creando una marca nueva");
        em.persist(marcaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una marca nueva");
        return marcaEntity;
    }

    /**
     * Busca si hay alguna marca con el id que se envía de argumento
     *
     * @param marcasId: id correspondiente a la marca buscada.
     * @return una marca.
     */
    public MarcaEntity find(long marcasId) {
        LOGGER.log(Level.INFO, "Consultando editorial con id={0}", marcasId);
        return em.find(MarcaEntity.class, marcasId);
    }

    /**
     * Busca si hay alguna editorial con el nombre que se envía de argumento
     *
     * @param name: Nombre de la editorial que se está buscando
     * @return null si no existe ninguna editorial con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public MarcaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando editorial por nombre ", name);
        TypedQuery query = em.createQuery("Select e From MarcaEntity e where e.name = :name", MarcaEntity.class);
        query = query.setParameter("name", name);
        List<MarcaEntity> sameName = query.getResultList();
        MarcaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar editorial por nombre ", name);
        return result;
    }

    /**
     * Devuelve todas las marcas de la base de datos.
     *
     * @return una lista con todas las marcas que encuentre en la base de datos.
     */
    public List<MarcaEntity> findAll() {
        TypedQuery<MarcaEntity> query = em.createQuery("select u from MarcaEntity u", MarcaEntity.class);
        return query.getResultList();
    }

    /**
     *
     * Borra una marca de la base de datos recibiendo como argumento el id de la
     * marca
     *
     * @param marcasId: id correspondiente a la marca a borrar.
     */
    public void delete(Long marcasId) {
        LOGGER.log(Level.INFO, "Borrando marca con id = {0}", marcasId);
        MarcaEntity entity = em.find(MarcaEntity.class, marcasId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la marca con id = {0}", marcasId);
    }

    /**
     * Actualiza una marca.
     *
     * @param marcaEntity: la marca que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una marca con los cambios aplicados.
     */
    public MarcaEntity update(MarcaEntity marcaEntity) {
        LOGGER.log(Level.INFO, "Actualizando marca con id = {0}", marcaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la marca con id = {0}", marcaEntity.getId());
        return em.merge(marcaEntity);
    }

}
