/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.entities.ItemCarritoEntity;
import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.ItemCarritoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author JuanLozano
 */
@Stateless
public class ItemCarritoLogic {

    /**
     * Logger que permite imprimir eventos
     */
    private static final Logger LOGGER = Logger.getLogger(OrdenLogic.class.getName());

    /**
     * Persistencia del item
     */
    @Inject
    private ItemCarritoPersistence itemPersistence;
    @Inject
    private CompradorPersistence compradorPersistence;
    @Inject
    private BicicletaPersistence bicicletaPersistence;

    /**
     * Crea un item en la persistencia.
     *
     * @param item La entidad que representa el itemCarrito a persistir.
     * @return El item luego de persistirla.
     * @throws BusinessLogicException Si el item a persistir ya existe.
     */
    public ItemCarritoEntity createItemCarrito(Long compradorId, ItemCarritoEntity item) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de un item");
        
          BicicletaEntity bike = bicicletaPersistence.find(item.getBicicleta().getId());
        if(bike == null)
            throw new BusinessLogicException("No existe una bicicleta con el id \"" + item.getBicicleta().getId() + "\"");

        CompradorEntity compr = compradorPersistence.find(compradorId);
        if(compr == null){
          throw new BusinessLogicException("No existe una comprador con el id \"" + item.getBicicleta().getId() + "\"");
            
        }
        if (item.getCantidad() < 0) {
            throw new BusinessLogicException("La cantidad debe estar establecida como un valor mayor a 0");
        }
        
        item.setComprador(compr);
        item.setBicicleta(bike);
        
        itemPersistence.create(item);
        LOGGER.log(Level.INFO, "Termina proceso de creación de un item");
        return item;
    }

    /**
     * Elimina un item
     *
     * @param id : id del item que se quiere borrar
     */
    public void deleteItemCarrito(Long id) {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar el item con id = {0}", id);
        itemPersistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el item con id = {0}", id);
    }

    /**
     * Retorna un itemCarritoEntity
     *
     * @return Una lista con todas los itemsCarritoEntity
     */
    public List<ItemCarritoEntity> getItemCarritos(Long id) {

        LOGGER.log(Level.INFO, "Inicia proceso de buscar todas las bicletas");
        List<ItemCarritoEntity> items = itemPersistence.findAll(id);
        LOGGER.log(Level.INFO, "Termina proceso de buscar todas los items");
        return items;
    }

    /**
     * Retorna un itemCarritoEntity
     *
     * @param id: ide del item que se quiere consultar
     * @return La entiddad del item que se quiere consultar
     */
    public ItemCarritoEntity getItemCarrito(Long id, Long comprador) {
        LOGGER.log(Level.INFO, "Inicia proceso de buscar un item por id ", id);
        ItemCarritoEntity item = itemPersistence.find(id, comprador);
        LOGGER.log(Level.INFO, "Termina proceso de buscar un item por id", id);
        return item;
    }

   
    
   
}
