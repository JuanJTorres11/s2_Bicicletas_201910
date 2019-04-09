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

    public VentaEntity create(VentaEntity pP) {
        LOGGER.log(Level.INFO, "Entrando para crear una nueva venta.");
        em.persist(pP);
        LOGGER.log(Level.INFO, "Saliendo de crear una venta nueva.");
        return pP;
    }

    public VentaEntity find(Long ventaID) {
        return em.find(VentaEntity.class, ventaID);
    }

    public List<VentaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las ventas");
        Query q = em.createQuery("select u from VentaEntity u");
        return q.getResultList();
    }

    public void delete(long id) {
        VentaEntity enti = em.find(VentaEntity.class, id);
        em.remove(enti);
    }

    public VentaEntity update(VentaEntity pComprador) {
        return em.merge(pComprador);
    }

    public VentaEntity findById(Long pId) {

        TypedQuery query = em.createQuery("Select e From VentaEntity e where e.id = :id", VentaEntity.class);

        query = query.setParameter("id", pId);

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

    public VentaEntity findByVendedor(Long vendedorId, Long ventaId)
    {
        LOGGER.log(Level.INFO, "Consultando la venta con id = {0} del vendedor con id = " + vendedorId, ventaId);
        TypedQuery<VentaEntity> q = em.createQuery("select p from VentaEntity m where (m.vendedor_id = :vendedorId) and (m.id = :ventaId)", VentaEntity.class);
        q.setParameter("vendedorId", vendedorId);
        q.setParameter("medioPagoId", ventaId);
        List<VentaEntity> results = q.getResultList();
        VentaEntity medio = null;
        if (results == null)
            medio = null;
          else if (results.isEmpty())
            medio = null;
          else if (results.size() >= 1)
            medio = results.get(0);
        
        LOGGER.log(Level.INFO, "Saliendo de consultar la venta con id = {0} del vendedor con id = " + vendedorId, ventaId);
        return medio;
    }
}