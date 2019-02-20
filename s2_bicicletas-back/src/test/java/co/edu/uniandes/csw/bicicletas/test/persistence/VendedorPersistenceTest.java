/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import co.edu.uniandes.csw.bicicletas.persistence.VendedorPersistence;
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
 * @author Juan José Torres <jj.torresr@uniandes.edu.co>
 */
@RunWith(Arquillian.class)
public class VendedorPersistenceTest
{

    /**
     * declaración de la instancia con los métodos a probar, se usa @inject para
     * que el contenedor maneje su ciclo de vida
     */
    @Inject
    VendedorPersistence persistencia;

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
    private List<VendedorEntity> data = new ArrayList<VendedorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VendedorEntity.class.getPackage())
                .addPackage(VendedorPersistence.class.getPackage())
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
            VendedorEntity entity = factory.manufacturePojo(VendedorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba el método create de la clase VendedorPersistence <br>
     * 1. Prueba que no devuelva nulo el método. <br>
     * 2. Prueba que se haya creado algo en la base de datos. <br>
     * 3. Prueba que lo que se haya creado sea lo que se quería.
     */
    //TODO @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity nuevo = factory.manufacturePojo(VendedorEntity.class);
        VendedorEntity resultado = persistencia.create(nuevo);
        Assert.assertNotNull("lo que retorna el método no debería ser null", resultado);
        VendedorEntity userBd = em.find(resultado.getClass(), resultado.getId());
        Assert.assertNotNull("lo que retorna la base de datos no debería ser null", userBd);
        Assert.assertEquals("lo que retorna la base de datos no es lo que se esperaba", resultado, userBd);
    }

    /**
     * Prueba el método find de la clase VendedorPersistence <br>
     * 1. Prueba que se encuentre algo en la base de datos. <br>
     * 2. Prueba que lo que se encontró sea lo que se esperaba.
     */
    @Test
    public void findTest()
    {
        VendedorEntity userLocal = data.get(0);
        VendedorEntity userBd = persistencia.find(userLocal.getId());
        Assert.assertNotNull("Lo que retorna la bd no debería se null", userBd);
        Assert.assertEquals("lo que retorna la bd no es lo que se espera", userLocal, userBd);
    }

    /**
     * Prueba el método findByLogin de la clase VendedorPersistence <br>
     * 1. Prueba que se encuentre algo en la base de datos. <br>
     * 2. Prueba que lo que se encontró sea lo que se esperaba.
     */
    @Test
    public void findByLoginTest()
    {
        VendedorEntity userLocal = data.get(0);
        VendedorEntity userBd = persistencia.findByLogin(userLocal.getLogin());
        Assert.assertNotNull("Lo que retorna la bd no debería se null", userBd);
        Assert.assertEquals("lo que retorna la bd no es lo que se espera", userLocal, userBd);
    }

    /**
     * Prueba el método findAll en la clase VendedorPesistence <br>
     * 1. Prueba que el tamaño de la lista retornado sea el esperado <br>
     * 2. Prueba que los elementos retornados sean los esperados.
     */
    @Test
    public void findAllTest()
    {
        List<VendedorEntity> list = persistencia.findAll();
        Assert.assertEquals("el tamaño de las listas debería ser igual", data.size(), list.size());
        Assert.assertTrue("La lista no tiene a todos los elementos esperados", list.containsAll(data));
    }

    /**
     * Prueba el método update de la clase VendedorPersistance <br>
     * 1. Prueba que los demás parametros del objeto hayan sido cambiados en la
     * base de datos.
     */
    //TODO @Test
    public void updateTest()
    {
        VendedorEntity local = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity nuevo = factory.manufacturePojo(VendedorEntity.class);
        nuevo.setId(local.getId());
        persistencia.update(nuevo);
        VendedorEntity bd = em.find(VendedorEntity.class, local.getId());
        Assert.assertEquals("No se actualizó correctamente", bd, nuevo);
    }

    /**
     * Prueba el método delete de la clase VendedorPersistence <br>
     * 1. Prueba que no se encuentre ningún vendedor con el id en la bd después
     * de borrarlo.
     */
    // TODO @Test
    public void deleteTest()
    {
        VendedorEntity local = data.get(0);
        persistencia.delete(local.getId());
        VendedorEntity borrado = em.find(local.getClass(), local.getId());
        Assert.assertNull("No se borró de la bd", borrado);
    }
}
