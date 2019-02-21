/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



/**
 *
 * @author Andres Donoso
 */
@Stateless
public class MedioPagoPersistence {
    private static final Logger LOGGER = Logger.getLogger(MedioPagoPersistence.class.getName());
    
    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;
    
    public MedioPagoEntity create(MedioPagoEntity medioPago) {
        LOGGER.log(Level.INFO, "Creando un medio de pago nuevo.");
        
        em.persist(medioPago);
        LOGGER.log(Level.INFO, "Saliendo de crear un medio de pago nuevo.");
        return medioPago;
    }
    
    public MedioPagoEntity find(Long medioPagosId) {
        LOGGER.log(Level.INFO, "Buscando un medio de pago por id.");
        return em.find(MedioPagoEntity.class, medioPagosId);
    }
    
    public List<MedioPagoEntity> findAll() {
        LOGGER.log(Level.INFO, "Entrando a buscar todos los ids.");
        TypedQuery<MedioPagoEntity> query = em.createQuery("Select u from MedioPagoEntity u", MedioPagoEntity.class);
        LOGGER.log(Level.INFO, "Saliendo de buscar todos los ids.");
        
        return query.getResultList();
    }
    
    public MedioPagoEntity findByNumber(Integer numero) {
        LOGGER.log(Level.INFO, "Consultando medio de pago por número ", numero);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From MedioPagoEntity e where e.numeroTarjeta = :numero", MedioPagoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("numero", numero);
        // Se invoca el query se obtiene la lista resultado
        List<MedioPagoEntity> sameName = query.getResultList();
        MedioPagoEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar medio de pago por número ", numero);
        return result;
    }
    
    public MedioPagoEntity update(MedioPagoEntity medioPago) {
        LOGGER.log(Level.INFO, "Actualizando medio de pago con id = {0}", medioPago.getId());
        
        LOGGER.log(Level.INFO, "Saliendo de actualizar el medio de pago con id = {0}", medioPago.getId());
        return em.merge(medioPago);
    }
    
    public void delete(Long medioPagosId) {
        LOGGER.log(Level.INFO, "Borrando medio de pago con id = {0}", medioPagosId);
        
        MedioPagoEntity medioPago = em.find(MedioPagoEntity.class, medioPagosId);
        em.remove(medioPago);
        
        LOGGER.log(Level.INFO, "Saliendo de borrar la categoria con id = {0}", medioPagosId);
    }
}
