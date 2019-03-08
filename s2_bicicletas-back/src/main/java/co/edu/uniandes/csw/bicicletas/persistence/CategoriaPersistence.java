/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
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
public class CategoriaPersistence {
    private static final Logger LOGGER = Logger.getLogger(CategoriaPersistence.class.getName());
    
    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;
    
    public CategoriaEntity create(CategoriaEntity categoria) {
        LOGGER.log(Level.INFO, "Creando una categoria nueva.");
        
        em.persist(categoria);
        LOGGER.log(Level.INFO, "Saliendo de crear una categoria nueva.");
        return categoria;
    }
    
    public CategoriaEntity find(Long categoriasId) {
        LOGGER.log(Level.INFO, "Buscando una categoría por id.");
        return em.find(CategoriaEntity.class, categoriasId);
    }
    
    public List<CategoriaEntity> findAll() {
        LOGGER.log(Level.INFO, "Entrando a buscar todos los ids.");
        TypedQuery<CategoriaEntity> query = em.createQuery("Select u from CategoriaEntity u", CategoriaEntity.class);
        LOGGER.log(Level.INFO, "Saliendo de buscar todos los ids.");
        
        return query.getResultList();
    }
    
    public CategoriaEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando categoria por nombre ", nombre);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery<CategoriaEntity> query = em.createQuery("SELECT c From CategoriaEntity c where c.nombre = :nombre", CategoriaEntity.class);
        
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<CategoriaEntity> sameName = query.getResultList();
        CategoriaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar categoria por nombre ", nombre);
        return result;
    }
    
    public CategoriaEntity update(CategoriaEntity categoria) {
        LOGGER.log(Level.INFO, "Actualizando categoria con id = {0}", categoria.getId());
        
        LOGGER.log(Level.INFO, "Saliendo de actualizar la categoria con id = {0}", categoria.getId());
        return em.merge(categoria);
    }
    
    public void delete(Long categoriasId) {
        LOGGER.log(Level.INFO, "Borrando categoria con id = {0}", categoriasId);
        
        CategoriaEntity categoria = em.find(CategoriaEntity.class, categoriasId);
        em.remove(categoria);
        
        LOGGER.log(Level.INFO, "Saliendo de borrar la categoria con id = {0}", categoriasId);
    }
}
