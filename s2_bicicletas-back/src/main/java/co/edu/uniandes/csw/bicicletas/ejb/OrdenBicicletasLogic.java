/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.OrdenPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Orden y Bicicleta.
 * @author Mateo
 */
@Stateless
public class OrdenBicicletasLogic {
    
    @Inject
    private BicicletaPersistence bicicletaPersistence;

    @Inject
    private OrdenPersistence ordenPersistence;
    
    
    /**
     * Retorna todos las biciletas asociadas a una orden
     *
     * @param ordenId El ID de la orden buscada
     * @return La lista de bicicletas de la categoria
     */
    public List<BicicletaEntity> getBicicletas(Long ordenId) {
        return ordenPersistence.find(ordenId).getBicicletasOrden();
    }
    
    /**
     * Retorna una bicicleta asociada a una orden
     *
     * @param ordenId El id de la orden a buscar.
     * @param bicicletaId El id de la bicicleta a buscar
     * @return La biciCleta encontrada dentro de la orden.
     * @throws BusinessLogicException Si la bicicleta no se encuentra en la
     * orden
     */
    public BicicletaEntity getBicicleta(Long ordenId, Long bicicletaId) throws BusinessLogicException {
         List<BicicletaEntity> bicicletas = ordenPersistence.find(ordenId).getBicicletasOrden();
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        int index = bicicletas.indexOf(bicicletaEntity);
         if (index >= 0) {
            return bicicletas.get(index);
        }
        throw new BusinessLogicException("La bicicleta no está asociada a la orden");
    }
}
