/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.entities.ItemCarritoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.ItemCarritoPersistence;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class CompradorItemCarritoLogic {

    private static final Logger LOGGER = Logger.getLogger(CompradorItemCarritoLogic.class.getName());

    @Inject
    private ItemCarritoPersistence itemPersistence;

   @Inject
    private CompradorPersistence compradorPersistence;
    @Inject
    private BicicletaPersistence bicicletaPersistence;

    /**
     * Agregar un item a un comprador
     *
     * @param itemId El id item a guardar
     * @param compradorId El id del comprador en la cual se va a guardar el
     * item.
     * @return El item creado.
     */
    public ItemCarritoEntity addItemCarrito(Long compradorId, ItemCarritoEntity item) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un item a un comprador con id = {0}", compradorId);
         
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
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", compradorId);
        return item;
    }

    /**
     * Retorna todos los items asociados a un comprador
     *
     * @param compradorId El ID del comprador buscada
     * @return La lista de item del comprador
     */
    public List<ItemCarritoEntity> getItemsCarrito(Long compradorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los items asociados al comprador con id = {0}", compradorId);
      CompradorEntity compr = compradorPersistence.find(compradorId);
        if(compr == null){
        throw new BusinessLogicException("No existe una comprador con el id \"" + compradorId + "\"");
    }
        return compr.getItems();
    }

    /**
     * Retorna un item asociado a un comprador
     *
     * @param compradorId El id del comprador a buscar.
     * @param itemId El id del item a buscar
     * @return El item encontrado dentro del comprador.
     * @throws BusinessLogicException Si el item no se encuentra en la comprador
     */
    public ItemCarritoEntity getItemCarrito(Long compradorId, Long itemId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el item con id = {0} del comprador con id = " + compradorId, itemId);
    
       CompradorEntity compr = compradorPersistence.find(compradorId);
        if(compr == null){
        throw new BusinessLogicException("No existe una comprador con el id \"" + compradorId + "\"");
        }
  
        ItemCarritoEntity t = itemPersistence.find(compradorId,itemId );
        
        return t;
    }


     /**
     * Elimina una resena
     * @param compradorId el id de la bicicleta a la que se quiere añadir la reseña
     * @param itemId id de la resena que se quiere borrar
     * @throws BusinessLogicException Si la reseña o la bicicleta asociada no exiten
     */
    public void deleteItemCarrito(Long compradorId, Long itemId) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar la resena con id = {0}", itemId);
        ItemCarritoEntity buscada = getItemCarrito(compradorId, itemId);
        
        if(buscada == null)
            throw new BusinessLogicException("El item con id = " + itemId + " no existe o no está asociada al comprador =" + compradorId + "\"");
        
        itemPersistence.delete(buscada.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la item con id = {0}", itemId);
    }
    
     public ItemCarritoEntity ubdateItemCarrito(Long compradorId, ItemCarritoEntity item) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una resena con id", item.getId());
           
             BicicletaEntity bike = bicicletaPersistence.find(item.getBicicleta().getId());
        if(bike == null)
            throw new BusinessLogicException("No existe una bicicleta con el id \"" + item.getBicicleta().getId() + "\"");

        CompradorEntity compr = compradorPersistence.find(compradorId);
        if(compr == null){
          throw new BusinessLogicException("No existe una comprador con el id \"" + item.getBicicleta().getId() + "\"");
            
        }
   
        item.setBicicleta(bike);
        item.setComprador(compr);
        itemPersistence.update(item);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar una resena con id", item.getId());
        return item;
    }
    
    
    
}
