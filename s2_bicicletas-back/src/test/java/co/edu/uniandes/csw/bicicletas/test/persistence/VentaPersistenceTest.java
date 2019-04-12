/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import co.edu.uniandes.csw.bicicletas.persistence.VentaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import javax.transaction.UserTransaction;
import org.junit.Before;

/**
 *
 * @author Juan Lozano
 */
@RunWith(Arquillian.class)
public class VentaPersistenceTest {
    
    /**
     * persistencia de la veta.
     */
    @Inject
    private VentaPersistence vp; 
    
    /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * User Transaction
     */
    @Inject
    private UserTransaction utx;
    
    /**
     * Lista con las ventas de la BD
     */
    private ArrayList<VentaEntity> data = new ArrayList<>();
    
      /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
   @Deployment
   public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addPackage(VentaEntity.class.getPackage())
            .addPackage(VentaPersistence.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Realiza unos escenarios aleatorios para poder crear las pruebas.
   */
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        
        VentaEntity newEntity = factory.manufacturePojo(VentaEntity.class);
        
        VentaEntity ve = vp.create(newEntity);
        
        Assert.assertNotNull(ve);
        
        VentaEntity ve2 = em.find(VentaEntity.class, ve.getId());
        
        Assert.assertEquals(ve2.getId(), ve.getId());
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
     * limpia la informacion anterior creada.
     */
    private void clearData() {
        
        em.createQuery("delete from VentaEntity").executeUpdate();
    }

    /**
     * Almacena la informacion creada para realizar las pruebas.
     */
    private void insertData() {
       
       PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            VentaEntity entity = factory.manufacturePojo(VentaEntity.class);

            em.persist(entity);

            data.add(entity);
        }

    }
    
    /**
     * Test de eliminacion de una venta.
     */
     @Test
    public void deleteTest() {
        VentaEntity venta = data.get(0);
        vp.delete(venta.getId());
        VentaEntity borrado = em.find(VentaEntity.class, venta.getId());
        Assert.assertNull(borrado);
    }
    
    /**
     * Test de actualizar una venta.
     */
    @Test
    public void updateTest()
    {
        VentaEntity local = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VentaEntity nuevo = factory.manufacturePojo(VentaEntity.class);
        nuevo.setId(local.getId());
        vp.update(nuevo);
        VentaEntity bd = em.find(VentaEntity.class, local.getId());
        Assert.assertEquals("No se actualizó correctamente", bd, nuevo);
    }
        /**
         * Test de obtener toda la lista de las ventas.
         */
        @Test
    public void findAllTest()
    {
//        List<VentaEntity> list = new ArrayList<VentaEntity>();
//        vp.findAll();
        List<VentaEntity> list = vp.findAll();
        Assert.assertEquals("el tamaño de las listas debería ser igual", data.size(), list.size());
        Assert.assertTrue("La lista no tiene a todos los elementos esperados", list.containsAll(data));
    }
    /**
     * Test de buscar una venta por su id.
     */
        @Test
    public void findByIdTest()
    {
        VentaEntity venta = data.get(0);
        VentaEntity ventaBd = vp.findById(venta.getId());
        Assert.assertNotNull("Lo que retorna la bd no debería se null", ventaBd);
        Assert.assertEquals("lo que retorna la bd no es lo que se espera", venta.getId(), ventaBd.getId());
    }
}
