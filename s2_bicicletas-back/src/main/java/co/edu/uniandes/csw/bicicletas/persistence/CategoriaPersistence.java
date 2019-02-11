/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
