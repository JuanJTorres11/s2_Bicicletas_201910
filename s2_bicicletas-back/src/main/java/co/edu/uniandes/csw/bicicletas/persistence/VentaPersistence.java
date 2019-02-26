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
public class VentaPersistence 
{
 
    private static final Logger LOGGER = Logger.getLogger(VentaPersistence.class.getName());
    
    @PersistenceContext(unitName= "bicicletasPU")
    protected EntityManager em;
    
    public VentaEntity crearEntity(VentaEntity pP)
    {
        LOGGER.log(Level.INFO, "Entrando para crear una nueva venta.");
        em.persist(pP);
        LOGGER.log(Level.INFO, "Saliendo de crear una venta nueva.");
        return pP;
    }
    
    public VentaEntity encontrarEntity(Long ventaID)
    {
        return em.find(VentaEntity.class, ventaID);
    }
    
     public List<VentaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las ventas");
        Query q = em.createQuery("select u from VentaEntity u");
        return q.getResultList();
    }
     
     public VentaEntity findById(Long id) {
         
        TypedQuery query = em.createQuery("Select e From VentaEntity e where e.id = : id", VentaEntity.class);
        
        query = query.setParameter("id", id);
        
        List<VentaEntity> obtiene = query.getResultList();
        VentaEntity result;
        if (obtiene == null) {
            result = null;
        } else if (obtiene.isEmpty()) {
            result = null;
        } else {
            result = obtiene.get(0);
        }
        return result;
    }
    
    public void delete(long id){
        VentaEntity enti = em.find(VentaEntity.class, id);
        em.remove(enti);
    }
    
    public VentaEntity update(VentaEntity pComprador){
        return em.merge(pComprador);
    }
/**
    public VentaEntity findByName(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     */
    
}
