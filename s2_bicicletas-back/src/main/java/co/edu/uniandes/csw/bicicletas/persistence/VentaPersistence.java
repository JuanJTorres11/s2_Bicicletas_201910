/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class VentaPersistence {

    private static final Logger LOGGER = Logger.getLogger(VentaPersistence.class.getName());

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param VentaEntity objeto venta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public VentaEntity create(VentaEntity pVentaEntity) {
        LOGGER.log(Level.INFO, "Entrando para crear una nueva venta.");
        em.persist(pVentaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una venta nueva.");
        return pVentaEntity;
    }

    /**
     * Busca si hay alguna venta con el id que se envía de argumento
     *
     * @param ventasId: id correspondiente a la venta buscada.
     * @return una venta.
     */
    public VentaEntity find(Long ventaID) {
        return em.find(VentaEntity.class, ventaID);
    }

    /**
     * Devuelve todas las ventas de la base de datos.
     *
     * @return una lista con todas las Ventas que encuentre en la base de datos,
     * "select u from VentaEntity u" es como un "select * from VentaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<VentaEntity> findAll() {
        TypedQuery query = em.createQuery("select u from VentaEntity u", VentaEntity.class);
        return query.getResultList();
    }

    /**
     *
     * Borra una venta de la base de datos recibiendo como argumento el id de la
     * venta
     *
     * @param Id: id correspondiente a la venta a borrar.
     */
    public void delete(Long id) {
        VentaEntity enti = em.find(VentaEntity.class, id);
        em.remove(enti);
    }

    /**
     * Actualiza una venta.
     *
     * @param ventaEntity: la vena que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return una venta con los cambios aplicados.
     */
    public VentaEntity update(VentaEntity pComprador) {
        return em.merge(pComprador);
    }

          /**
     * Buscar una venta
     *
     * Busca si hay alguna venta con un login específico
     *
     * @param id El ID de la venta con respecto al cual se busca
     * @return La venta encontrada o null. Nota: Si existe uno o mas.
     * devuelve siempre la primera que encuentra
     */
    public VentaEntity findById(Long id) {

        TypedQuery query = em.createQuery("Select e From VentaEntity e where e.id = :id", VentaEntity.class);

        query = query.setParameter("id", id);

        List<VentaEntity> ids = query.getResultList();
        VentaEntity ret;

        if (ids == null) {
            ret = null;
        } else if (ids.isEmpty()) {
            ret = null;
        } else {
            ret = ids.get(0);
        }
        return ret;
    }
}
