/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class CompradorPersistence {
    
    
    @PersistenceContext(unitName= "bicicletasPU")
    protected EntityManager em;
    
    public CompradorEntity crearEntity(CompradorEntity pP)
    {
        em.persist(pP);
        return pP;
    }
    
    public CompradorEntity findByName(String login)
    {
        
        TypedQuery query = em.createQuery("Select e From VendedorEntity e where e.login = :loggin", CompradorEntity.class);
         
        query = query.setParameter("loggin", login);
        
        List<CompradorEntity> logins = query.getResultList();
        CompradorEntity result;

        if (logins == null)
        {
            result = null;
        }
        else if (logins.isEmpty())
        {
            result = null;
        }
        else
        {
            result = logins.get(0);
        }
        return result;
    }
    
    public CompradorEntity encontrarEntity(Long compradorID)
    {
        return em.find(CompradorEntity.class, compradorID);
    }
    
    public void delete(long id){
        CompradorEntity enti = em.find(CompradorEntity.class, id);
        em.remove(enti);
    }
    
    public CompradorEntity update(CompradorEntity pComprador){
        return em.merge(pComprador);
    }
/**
    public Object findByName(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
*/
}
