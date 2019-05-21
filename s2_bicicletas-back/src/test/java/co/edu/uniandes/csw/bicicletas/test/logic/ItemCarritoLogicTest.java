/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.ItemCarritoLogic;
import co.edu.uniandes.csw.bicicletas.entities.ItemCarritoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.ItemCarritoPersistence;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ItemCarritoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    /**
     * Inyección de la dependencia a la clase ItemCarritoLogic cuyos métodos se
     * van a probar.
     */
    @Inject
    private ItemCarritoLogic itemLogic;
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    private UserTransaction utx;
    
        /**
     * Lista que tiene los datos de prueba.
     */
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
                .addPackage(ItemCarritoLogic.class.getPackage())
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
     * Prueba para crear un item.
     *
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    @Test
    public void createItemTest()
    {
        try
        {
            PodamFactory factory = new PodamFactoryImpl();
            ItemCarritoEntity nuevo = factory.manufacturePojo(ItemCarritoEntity.class);
            ItemCarritoEntity resultado = itemLogic.createItemCarrito(nuevo);
            Assert.assertNotNull("lo que retorna el método no debería ser null", resultado);
            ItemCarritoEntity userBd = em.find(resultado.getClass(), resultado.getId());
            Assert.assertNotNull("lo que retorna la base de datos no debería ser null", userBd);
            Assert.assertEquals("lo que retorna la base de datos no es lo que se esperaba", resultado, userBd);
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(VendedorLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para consultar la lista de items.
     */
    @Test
    public void getItemCarritosTest() {
        List<ItemCarritoEntity> list = itemLogic.getItemCarritos();
        Assert.assertEquals(data.size(), list.size());
        for (ItemCarritoEntity entity : list) {
            boolean found = false;
            for (ItemCarritoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
/**
     * Prueba para consultar un item .
     */
    @Test
    public void getItemCarritoTest() {
        ItemCarritoEntity entity = data.get(0);
        ItemCarritoEntity resultEntity = itemLogic.getItemCarrito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para actualizar un Marca.
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    @Test
    public void updateTest()
    {
        try
        {
            ItemCarritoEntity local = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            ItemCarritoEntity nuevo = factory.manufacturePojo(ItemCarritoEntity.class);
            nuevo.setId(local.getId());
            itemLogic.updateItemCarrito(nuevo);
            ItemCarritoEntity bd = em.find(ItemCarritoEntity.class, local.getId());
            Assert.assertEquals("No se actualizó correctamente", bd, nuevo);
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(VendedorLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        /**
     * Prueba para eliminar una Marca.
     *
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    @Test
    public void deleteItemTest() throws BusinessLogicException {
        ItemCarritoEntity entity = data.get(0);
        itemLogic.deleteItemCarrito(entity.getId());
        ItemCarritoEntity deleted = em.find(ItemCarritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
