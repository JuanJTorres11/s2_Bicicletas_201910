/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.CategoriaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
* Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Categoria y Bicicleta.
 * @author Andrea
 */
@Stateless
public class CategoriaBicicletasLogic {
    
    private static final Logger LOGGER = Logger.getLogger(CategoriaBicicletasLogic.class.getName());
    
    @Inject
    private BicicletaPersistence bicicletaPersistence;

    @Inject
    private CategoriaPersistence categoriaPersistence;
    
    
    /**
     * Agregar una bicicleta a la categoria
     *
     * @param bicicletaId El id de la bicicleta a guardar
     * @param categoriaNombre El nombre de la categoria en la cual se va a guardar la
     * bicicleta.
     * @return La bicicleta creada.
     */
    public BicicletaEntity addBicicleta(Long bicicletaId, String categoriaNombre) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una bicicleta a la categoria con id = {0}", categoriaNombre);
        CategoriaEntity categoriaEntity = categoriaPersistence.findByName(categoriaNombre);
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        bicicletaEntity.setCategoria(categoriaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una bicicleta a la categoria con id = {0}", categoriaNombre);
        return bicicletaEntity;
    }

    
     /**
     * Retorna todos las biciletas asociadas a una categoria
     *
     * @param categoriaNombre El ID de la bicicleta buscada
     * @return La lista de bicicletas de la categoria
     */
    public List<BicicletaEntity> getBicicletas(String categoriaNombre) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las bicicletas asociados a la categoria con id = {0}", categoriaNombre);
        return categoriaPersistence.findByName(categoriaNombre).getBicicletas();
    }
    
     /**
     * Retorna una bicicleta asociada a una categoria
     *
     * @param categoriaNombre El id de la categoria a buscar.
     * @param bicicletaId El id de la bicicleta a buscar
     * @return La bicileta encontrada dentro de la categoria.
     * @throws BusinessLogicException Si la bicicleta no se encuentra en la
     * categoria
     */
    public BicicletaEntity getBicicleta(String categoriaNombre, Long bicicletaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la bicicleta con id = {0} de la categoria con id = " + categoriaNombre, bicicletaId);
        List<BicicletaEntity> bicicletas = categoriaPersistence.findByName(categoriaNombre).getBicicletas();
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        int index = bicicletas.indexOf(bicicletaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la bicicleta con id = {0} de la categoria con id = " + categoriaNombre, bicicletaId);
        if (index >= 0) {
            return bicicletas.get(index);
        }
        throw new BusinessLogicException("La bicicleta no está asociada a la categoria");
    }
    
     /**
     * Remplazar bicicletas de una categoria
     *
     * @param bicicletas Lista de bicicletas que serán los de la categoria.
     * @param categoriaNombre El id de la categoria que se quiere actualizar.
     * @return La lista de bicicletas actualizada.
     */
    public List<BicicletaEntity> replaceBicicletas(String categoriaNombre, List<BicicletaEntity> bicicletas) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la categoria con id = {0}", categoriaNombre);
        CategoriaEntity categoriaEntity = categoriaPersistence.findByName(categoriaNombre);
        List<BicicletaEntity> lista = bicicletaPersistence.findAll();
        for (BicicletaEntity bike : lista) {
            if (lista.contains(bike)) {
                bike.setCategoria(categoriaEntity);
            } else if (bike.getCategoria()!= null && bike.getCategoria().equals(categoriaEntity)) {
                bike.setCategoria(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la categoria con id = {0}", categoriaNombre);
        return lista;
    }


}
