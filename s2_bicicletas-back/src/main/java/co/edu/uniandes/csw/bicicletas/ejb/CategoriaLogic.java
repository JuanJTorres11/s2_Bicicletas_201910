/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.CategoriaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author andydonoso
 */
@Stateless
public class CategoriaLogic {
    @Inject private CategoriaPersistence cp;
    
    public CategoriaEntity createCategoria(CategoriaEntity categoria) throws BusinessLogicException {
        if(cp.findByName(categoria.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una categoría con el nombre \"" + categoria.getNombre() + "\"");
        } else if(categoria.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre de la categoría no puede estar vacío.");
        }
        
        cp.create(categoria);
        return categoria;
    }
}
