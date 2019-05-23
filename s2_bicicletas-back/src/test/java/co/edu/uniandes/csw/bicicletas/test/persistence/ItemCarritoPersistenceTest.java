/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.ItemCarritoEntity;
import co.edu.uniandes.csw.bicicletas.persistence.ItemCarritoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Lozano
 */
@RunWith(Arquillian.class)
public class ItemCarritoPersistenceTest {
    
    @Inject
    private ItemCarritoPersistence itemPersistence;


    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ItemCarritoEntity> data = new ArrayList<ItemCarritoEntity>();
    
        /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItemCarritoEntity.class.getPackage())
                .addPackage(ItemCarritoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
        /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ItemCarritoEntity").executeUpdate();
        data.clear();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            ItemCarritoEntity entity = factory.manufacturePojo(ItemCarritoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un entity.
     */
    @Test
    public void createItemTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ItemCarritoEntity newEntity = factory.manufacturePojo(ItemCarritoEntity.class);

        ItemCarritoEntity result = itemPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ItemCarritoEntity entity = em.find(ItemCarritoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para consultar la lista de premios.
     */
    @Test
    public void getitemsTest() {
        List<ItemCarritoEntity> list = itemPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ItemCarritoEntity ent : list) {
            boolean found = false;
            for (ItemCarritoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Orden.
     */
    @Test
    public void getItemTest() {
        ItemCarritoEntity entity = data.get(0);
       
        ItemCarritoEntity newEntity = itemPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
        /**
     * Test de eliminacion de una orden.
     */
     @Test
    public void deleteTest() {
        ItemCarritoEntity item = data.get(0);
        itemPersistence.delete(item.getId());
        ItemCarritoEntity borrado = em.find(ItemCarritoEntity.class, item.getId());
        Assert.assertNull(borrado);
    }
    
        @Test
    public void updateTest() {

        PodamFactory factory = new PodamFactoryImpl();

        Long idActualizar = data.get(0).getId();
        ItemCarritoEntity nueevoItem = factory.manufacturePojo(ItemCarritoEntity.class);
        nueevoItem.setId(idActualizar);
        itemPersistence.update(nueevoItem);

        ItemCarritoEntity recuperada = itemPersistence.find(idActualizar);

        Assert.assertEquals(nueevoItem.getCantidad(), recuperada.getCantidad());
    }
    
}
