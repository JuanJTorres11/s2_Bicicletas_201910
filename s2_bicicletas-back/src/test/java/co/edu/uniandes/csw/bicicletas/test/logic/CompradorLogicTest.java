/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.CompradorLogic;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
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
 * @author Juan L
 */
 @RunWith(Arquillian.class)
public class CompradorLogicTest 
{
   

    /**
     * Declaración de la instancia de lógica con los métodos a probar
     */
    @Inject
    CompradorLogic logica;

    /**
     * Debido a que se manejaran operaciones transaccionales como crear y
     * eliminar se necesita
     */
    @Inject
    UserTransaction utx;

    /**
     * Se usará para acceder a la base de datos y comprobar que sí se realizaron
     * cambios adecuados
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Lista con los datos a probar
     */
    private List<CompradorEntity> data = new ArrayList<CompradorEntity>();

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(CompradorPersistence.class.getPackage())
                .addPackage(CompradorLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Borra lo que haya en la base de datos y mete indormación nueva antes de
     * ejecutar cada prueba
     */
    @Before
    public void setUp()
    {
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

    /**
     * Borra toda la información de la base de datos
     */
    private void clearData()
    {
        em.createQuery("delete from VendedorEntity").executeUpdate();
        data.clear();
    }

    /**
     * Crea vendedors en la base de datos y los agrega a data
     */
    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++)
        {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * test que crea un comprador.
     */
        @Test
    public void createTest()
    {
        try
        {
            PodamFactory factory = new PodamFactoryImpl();
            CompradorEntity nuevo = factory.manufacturePojo(CompradorEntity.class);
            CompradorEntity resultado = logica.createComprador(nuevo);
            Assert.assertNotNull("lo que retorna el método no debería ser null", resultado);
            CompradorEntity userBd = em.find(resultado.getClass(), resultado.getId());
            Assert.assertNotNull("lo que retorna la base de datos no debería ser null", userBd);
            Assert.assertEquals("lo que retorna la base de datos no es lo que se esperaba", resultado, userBd);
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(VendedorLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test que verifica si estan todos los elementos de la lista con el de la base de datos.
     */
         @Test
    public void findAllTest()
    {
        List<CompradorEntity> list = logica.getCompradores();
//        Assert.assertEquals("el tamaño de las listas debería ser igual", data.size(), list.size());
        Assert.assertTrue("La lista no tiene a todos los elementos esperados", list.containsAll(data));
    }
    
    /**
     * Verifica que no hayan dos compradores con el mismo login.
     * @throws BusinessLogicException 
     */
        @Test(expected = BusinessLogicException.class)
    public void createMismoLoginTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();
        CompradorEntity nuevo = factory.manufacturePojo(CompradorEntity.class);
        nuevo.setLogin(data.get(0).getLogin());
        logica.createComprador(nuevo);
    }
    
    
     /**
     * busca un comprador dado su id. 
     */
        @Test
    public void findByIdTest()
    {
        CompradorEntity userLocal = data.get(0);
        CompradorEntity userBd = logica.getComprador(userLocal.getId());
        Assert.assertNotNull("Lo que retorna la bd no debería se null", userBd);
        Assert.assertEquals("lo que retorna la bd no es lo que se espera", userLocal, userBd);
    }
    
      /**
     * busca un comprador dado su login. 
     */
        @Test
    public void findByLoginTest()
    {
        CompradorEntity userLocal = data.get(0);
        CompradorEntity userBd = logica.getByLogin(userLocal.getLogin());
        Assert.assertNotNull("Lo que retorna la bd no debería se null", userBd);
        Assert.assertEquals("lo que retorna la bd no es lo que se espera", userLocal, userBd);
    }
    
   
    /** 
     * actualiza la informacion de un comprador dado un id.
     */
        @Test
    public void updateTest()
    {
        try
        {
            CompradorEntity local = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            CompradorEntity nuevo = factory.manufacturePojo(CompradorEntity.class);
            nuevo.setId(local.getId());
            logica.updateComprador(nuevo);
            CompradorEntity bd = em.find(CompradorEntity.class, local.getId());
            Assert.assertEquals("No se actualizó correctamente", bd, nuevo);
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(VendedorLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
