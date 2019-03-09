/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.OrdenPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo
 */
@Stateless
public class OrdenBicicletaLogic {
    
    @Inject
    private BicicletaPersistence bicicletaPersistence;

    @Inject
    private OrdenPersistence ordenPersistence;
    
    /**
     * Agregar una bicicleta a la Orden
     *
     * @param bicicletaId El id de la bicicleta a guardar
     * @param idOrden El id de la orden en la cual se va a guardar la
     * bicicleta.
     * @return La bicicleta creada.
     */
    public BicicletaEntity addBicicleta(Long bicicletaId, Long idOrden) {
        OrdenEntity ordenEntity = ordenPersistence.find(idOrden);
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        bicicletaEntity.setOrden(ordenEntity);
        return bicicletaEntity;
    }
    
    /**
     * Retorna todos las biciletas asociadas a una orden
     *
     * @param ordenNombre El ID de la bicicleta buscada
     * @return La lista de bicicletas de la orden
     */
    public List<BicicletaEntity> getBicicletas(Long idOrden) {
        return ordenPersistence.find(idOrden).getBicicletasOrden();
    }
    
     /**
     * Retorna una bicicleta asociada a una orden
     *
     * @param ordenNombre El id de la orden a buscar.
     * @param bicicletaId El id de la bicicleta a buscar
     * @return La bicileta encontrada dentro de la orden.
     * @throws BusinessLogicException Si la bicicleta no se encuentra en la
     * orden
     */
    public BicicletaEntity getBicicleta(Long idOrden, Long bicicletaId) throws BusinessLogicException {
        List<BicicletaEntity> bicicletas = ordenPersistence.find(idOrden).getBicicletasOrden();
        BicicletaEntity bicicletaEntity = bicicletaPersistence.find(bicicletaId);
        int index = bicicletas.indexOf(bicicletaEntity);
         if (index >= 0) {
            return bicicletas.get(index);
        }
        throw new BusinessLogicException("La bicicleta no está asociada a la orden");
    }
}
