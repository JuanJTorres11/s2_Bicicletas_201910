/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.persistence;

import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mateo
 */

@Stateless
public class MarcaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MarcaPersistence.class.getName());
    
    @PersistenceContext(unitName = "bicicletasPU")
    protected EntityManager em;
    
    public MarcaEntity create(MarcaEntity entity){
        em.persist(entity);
        return entity;
    }
}
