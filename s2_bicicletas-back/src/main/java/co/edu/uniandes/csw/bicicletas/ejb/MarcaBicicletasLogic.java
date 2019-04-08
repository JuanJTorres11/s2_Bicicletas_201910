/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.MarcaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *  Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Marca y Bicicleta.
 * @author Mateo
 */
@Stateless
public class MarcaBicicletasLogic {
    
    /**
     * Lógica de la bicicleta
     */
     @Inject
    private BicicletaPersistence bicicletaPersistence;

     /**
      * Lógica de la marca
      */
    @Inject
    private MarcaPersistence marcaPersistence;
    
    /**
     * Agregar una bicicleta a la marca
     *
     * @param bicicletaId El id de la bicicleta a guardar
     * @param marcaNombre El nombre de la marca en la cual se va a guardar la
     * bicicleta.
     * @return La bicicleta creada.
     */
    public BicicletaEntity addBicicleta(Long bicicletaId, String marcaNombre) {
        MarcaEntity marcaEntity = marcaPersistence.findByName(marcaNombre);
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        bicicletaEntity.setMarca(marcaEntity);
         return bicicletaEntity;
    }

    /**
     * Retorna todos las biciletas asociadas a una marca
     *
     * @param marcaNombre El ID de la bicicleta buscada
     * @return La lista de bicicletas de la marca
     */
    public List<BicicletaEntity> getBicicletas(String marcaNombre) {
        return marcaPersistence.findByName(marcaNombre).getBicicletasMarca();
    }
    
    /**
     * Retorna una bicicleta asociada a una marca
     *
     * @param marcaNombre El id de la marca a buscar.
     * @param bicicletaId El id de la bicicleta a buscar
     * @return La bicicleta encontrada dentro de la marca.
     * @throws BusinessLogicException Si la bicicleta no se encuentra en la
     * marca
     */
    public BicicletaEntity getBicicleta(String marcaNombre, Long bicicletaId) throws BusinessLogicException {
         List<BicicletaEntity> bicicletas = marcaPersistence.findByName(marcaNombre).getBicicletasMarca();
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        int index = bicicletas.indexOf(bicicletaEntity);
         if (index >= 0) {
            return bicicletas.get(index);
        }
        throw new BusinessLogicException("La bicicleta no está asociada a la marca");
    }
    
    /**
     * Remplazar bicicletas de una marca
     *
     * @param bicicletas Lista de bicicletas que serán los de la marca.
     * @param marcaNombre El id de la marca que se quiere actualizar.
     * @return La lista de bicicletas actualizada.
     */
    public List<BicicletaEntity> replaceBicicletas(String marcaNombre, List<BicicletaEntity> bicicletas) {
        MarcaEntity marcaEntity = marcaPersistence.findByName(marcaNombre);
        List<BicicletaEntity> lista = bicicletaPersistence.findAll();
        for (BicicletaEntity bike : lista) {
            if (lista.contains(bike)) {
                bike.setMarca(marcaEntity);
            } else if (bike.getMarca()!= null && bike.getMarca().equals(marcaEntity)) {
                bike.setMarca(null);
            }
        }
        return lista;
    }
}
