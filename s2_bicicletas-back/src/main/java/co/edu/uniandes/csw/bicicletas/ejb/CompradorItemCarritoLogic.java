/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.entities.ItemCarritoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
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

    /**
     * Agregar un item a un comprador
     *
     * @param itemId El id item a guardar
     * @param compradorId El id del comprador en la cual se va a guardar el
     * item.
     * @return El item creado.
     */
    public ItemCarritoEntity addBook(Long itemId, Long compradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un item a un comprador con id = {0}", compradorId);
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        ItemCarritoEntity itemEntity = itemPersistence.find(itemId);
        itemEntity.setComprador(compradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", compradorId);
        return itemEntity;
    }

    /**
     * Retorna todos los items asociados a un comprador
     *
     * @param compradorId El ID del comprador buscada
     * @return La lista de item del comprador
     */
    public List<ItemCarritoEntity> getBooks(Long compradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los items asociados al comprador con id = {0}", compradorId);
        return compradorPersistence.find(compradorId).getItems();
    }

    /**
     * Retorna un item asociado a un comprador
     *
     * @param compradorId El id del comprador a buscar.
     * @param itemId El id del item a buscar
     * @return El item encontrado dentro del comprador.
     * @throws BusinessLogicException Si el item no se encuentra en la comprador
     */
    public ItemCarritoEntity getBook(Long compradorId, Long itemId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el item con id = {0} del comprador con id = " + compradorId, itemId);
        List<ItemCarritoEntity> items = compradorPersistence.find(compradorId).getItems();
        ItemCarritoEntity itemEntity = itemPersistence.find(itemId);
        int index = items.indexOf(itemEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el item con id = {0} de un comprador con id = " + compradorId, itemId);
        if (index >= 0) {
            return items.get(index);
        }
        throw new BusinessLogicException("El item no está asociado al comprador");
    }

    /**
     * Remplazar items de un comprador
     *
     * @param items Lista de items que serán los del comprador.
     * @param compradorId El id del comprador que se quiere actualizar.
     * @return La lista de items actualizada.
     */
    public List<ItemCarritoEntity> replaceBooks(Long compradorId, List<ItemCarritoEntity> items) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el comprador  con id = {0}", compradorId);
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        List<ItemCarritoEntity> itemList = itemPersistence.findAll();
        for (ItemCarritoEntity items2 : itemList) {
            if (items.contains(items2)) {
                items2.setComprador(compradorEntity);
            } else if (items2.getComprador() != null && items2.getComprador().equals(compradorEntity)) {
                items2.setComprador(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar un comprador con id = {0}", compradorId);
        return items;
    }
}
