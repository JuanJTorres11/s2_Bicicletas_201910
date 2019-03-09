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

    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;

    public CompradorEntity create(CompradorEntity pP) {
        em.persist(pP);
        return pP;
    }

    public CompradorEntity find(Long compradorID) {
        return em.find(CompradorEntity.class, compradorID);
    }

    public void delete(long id) {
        CompradorEntity enti = em.find(CompradorEntity.class, id);
        em.remove(enti);
    }

    public CompradorEntity update(CompradorEntity pComprador) {
        return em.merge(pComprador);
    }

    public List<CompradorEntity> findAll() {
        TypedQuery<CompradorEntity> query = em.createQuery("select u from CompradorEntity u", CompradorEntity.class);
        return query.getResultList();
    }

//    
    public CompradorEntity findByLogin(String login) {
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From CompradorEntity e where e.login = :loggin", CompradorEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("loggin", login);
        // Se invoca el query se obtiene la lista resultado
        List<CompradorEntity> logins = query.getResultList();
        CompradorEntity result;

        if (logins == null) {
            result = null;
        } else if (logins.isEmpty()) {
            result = null;
        } else {
            result = logins.get(0);
        }

        return result;
    }
}
