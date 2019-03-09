/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CompradorPersistetenceTest {
    
    @Inject
    private CompradorPersistence cp; 
    
    @PersistenceContext
    private EntityManager em;
    
     @Inject
    private UserTransaction utx;
     
     private ArrayList<CompradorEntity> data = new ArrayList<>();
    
      /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    
   @Deployment
   public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addPackage(CompradorEntity.class.getPackage())
            .addPackage(CompradorPersistence.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
    
          /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
              try
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                utx.rollback();
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
      em.createQuery("delete from CompradorEntity").executeUpdate();
        data.clear();
    }

    private void insertData() {
       
    PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++)
        {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
            data.add(entity);
        }

    }
    
      @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        
        CompradorEntity newEntity = factory.manufacturePojo(CompradorEntity.class);
        
        CompradorEntity ve = cp.create(newEntity);
        
        Assert.assertNotNull(ve);
        
        CompradorEntity ve2 = em.find(CompradorEntity.class, ve.getId());
        
        Assert.assertEquals(ve2.getNombre(), ve.getNombre());
    }
    
     @Test
    public void deleteTest() {
        CompradorEntity comprador = data.get(0);
        cp.delete(comprador.getId());
        CompradorEntity borrado = em.find(CompradorEntity.class, comprador.getId());
        Assert.assertNull(borrado);
    }
    
        @Test
    public void findByLogginTest()
    {
        CompradorEntity comprador = data.get(0);
        CompradorEntity compradorBd = cp.findByLogin(comprador.getLogin());
        Assert.assertNotNull("Lo que retorna la bd no debería se null", compradorBd);
        Assert.assertEquals("lo que retorna la bd no es lo que se espera", comprador, compradorBd);
    }
    
        @Test
    public void updateTest()
    {
        CompradorEntity local = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CompradorEntity nuevo = factory.manufacturePojo(CompradorEntity.class);
        nuevo.setId(local.getId());
        cp.update(nuevo);
        CompradorEntity bd = em.find(CompradorEntity.class, local.getId());
        Assert.assertEquals("No se actualizó correctamente", bd, nuevo);
    }
    
        @Test
    public void findAllTest()
    {
        List<CompradorEntity> list = cp.findAll();
        Assert.assertEquals("el tamaño de las listas debería ser igual", data.size(), list.size());
        Assert.assertTrue("La lista no tiene a todos los elementos esperados", list.containsAll(data));
    }
    
}
