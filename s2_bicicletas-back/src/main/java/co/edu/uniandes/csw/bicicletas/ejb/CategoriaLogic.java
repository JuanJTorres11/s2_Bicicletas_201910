/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.CategoriaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres Donoso
 */
@Stateless
public class CategoriaLogic {
    private final static Logger LOGGER = Logger.getLogger(CategoriaLogic.class.getName());
    
    @Inject private CategoriaPersistence cp;
    
    /**
     * Crea una nueva categoría con la información pasada por parámetro.
     * @param categoria Categoría a crear. Categoria != null.
     * @throws BusinessLogicException 1. Si el nombre de la categoría está vacío
     *                                2. Si la categoría ya existe.
     * @return La categoría creada.
     */
    public CategoriaEntity createCategoria(CategoriaEntity categoria) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear una categoría.");
        
        if(cp.findByName(categoria.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una categoría con el nombre \"" + categoria.getNombre() + "\"");
        } else if(categoria.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre de la categoría no puede estar vacío.");
        }
        
        cp.create(categoria);
        
        LOGGER.log(Level.INFO, "Termina proceso de crear una categoría.");
        return categoria;
    }
    
    /**
     * Retorna la categoría con el id ingresado por parámetro.
     * @param categoriaId Id de la categoría. categoriaId > 0.
     * @return Categoría encontrada, null si no la encontró.
     */
    public CategoriaEntity getCategoria(Long categoriaId) {
        LOGGER.log(Level.INFO, "Inicia proceso búsqueda de una categoría.");
        
        CategoriaEntity categoria = cp.find(categoriaId);
        
        LOGGER.log(Level.INFO, "Termina proceso de búsqueda de una categoría.");
        return categoria;
    }
    
    /**
     * Retorna todas las categorías existentes.
     * @return Lista de categorías.
     */
    public List<CategoriaEntity> getCategorias() {
        LOGGER.log(Level.INFO, "Inicia proceso de retorno de todas las categorías.");
        
        List<CategoriaEntity> categorias = cp.findAll();
        
        LOGGER.log(Level.INFO, "Termina proceso de retorno de todas las categorías.");
        
        return categorias;
    }
    
    /**
     * Retorna la categoría con el nombre indicado.
     * @param nombre Nombre de la categoría. nombre != null && != vacio.
     * @return categoría encontrada, null si no existe.
     */
    public CategoriaEntity getCategoriaPorNombre(String nombre) {
        LOGGER.log(Level.INFO, "Inicia proceso de búsqueda de una categoría por nombre.");
        
        CategoriaEntity categoria = cp.findByName(nombre);
        
        LOGGER.log(Level.INFO, "Termina proceso de búsqueda de una categoría por nombre");
        return categoria;
    }
    
    /**
     * Actualiza la categoría.
     * @param categoria Categoria con datos actualizados. categoria != null.
     * @return Categoría creada.
     */
    public CategoriaEntity updateCategoria(CategoriaEntity categoria) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar categoría.");
        
        if(cp.findByName(categoria.getNombre()) != null) {
            throw new BusinessLogicException("La categoría con el nombre dado ya existe");
        }
        
        if(categoria.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre de la categoría no puede estar vacío.");
        }
        
        cp.update(categoria);
        
        LOGGER.log(Level.INFO, "Termina proceso de actualizar categoría."); 
        
        return categoria;
    }
    
    /**
     * Elimina la categoría con el id dado por parámetro.
     * @param categoriaId Id de la categoría. categoriaId > 0.
     * @throws BusinessLogicException 1. Si hay bicicletas asociadas a la categoría.
     */
    public void deleteCategoria(Long categoriaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Comienza proceso de eliminación de la categoría con id = {0}", categoriaId);
        
        List<BicicletaEntity> bicicletas = getCategoria(categoriaId).getBicicletas();
        if(bicicletas != null && !bicicletas.isEmpty()) {
            throw new BusinessLogicException("La categoría no puede ser eliminada porque tiene bicicletas asociadas.");
        }
        cp.delete(categoriaId);
        
        LOGGER.log(Level.INFO, "Termina proceso de eliminación de la categoría con id = {0}", categoriaId);
    }
}
