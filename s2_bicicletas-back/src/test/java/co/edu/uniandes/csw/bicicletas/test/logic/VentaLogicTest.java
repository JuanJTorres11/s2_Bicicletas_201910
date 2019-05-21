/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.VentaLogic;
import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.VentaPersistence;
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
public class VentaLogicTest {

    /**
     * Declaración de la instancia de lógica con los métodos a probar
     */
    @Inject
    VentaLogic logica;

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
    private List<VentaEntity> data = new ArrayList<VentaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VentaEntity.class.getPackage())
                .addPackage(VentaPersistence.class.getPackage())
                .addPackage(VentaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Borra lo que haya en la base de datos y mete indormación nueva antes de
     * ejecutar cada prueba
     */
    @Before
    public void setUp() {
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
     * Borra toda la información de la base de datos
     */
    private void clearData() {
        em.createQuery("delete from VentaEntity").executeUpdate();
        data.clear();
    }

    /**
     * Crea vendedors en la base de datos y los agrega a data
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            VentaEntity entity = factory.manufacturePojo(VentaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createTest() {
        try {
            PodamFactory factory = new PodamFactoryImpl();
            VentaEntity nuevo = factory.manufacturePojo(VentaEntity.class);
            VentaEntity resultado = logica.createVenta(nuevo);
            Assert.assertNotNull("lo que retorna el método no debería ser null", resultado);
            VentaEntity userBd = em.find(resultado.getClass(), resultado.getId());
            Assert.assertNotNull("lo que retorna la base de datos no debería ser null", userBd);
            Assert.assertEquals("lo que retorna la base de datos no es lo que se esperaba", resultado, userBd);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(VendedorLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
        @Test
    public void findAllTest() {
        List<VentaEntity> list = logica.getVentas();
        Assert.assertEquals("el tamaño de las listas debería ser igual", data.size(), list.size());
        Assert.assertTrue("La lista no tiene a todos los elementos esperados", list.containsAll(data));
    }

    @Test(expected = BusinessLogicException.class)
    public void createMismoLoginTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        VentaEntity nuevo = factory.manufacturePojo(VentaEntity.class);
        nuevo.setId(data.get(0).getId());
        logica.createVenta(nuevo);
    }

    @Test
    public void findTest() {
        VentaEntity userLocal = data.get(0);
        VentaEntity userBd = logica.getVenta(userLocal.getId());
        Assert.assertNotNull("Lo que retorna la bd no debería se null", userBd);
        Assert.assertEquals("lo que retorna la bd no es lo que se espera", userLocal, userBd);
    }

    @Test
    public void findByIdTest() {
        VentaEntity userLocal = data.get(0);
        VentaEntity userBd = logica.getVenta(userLocal.getId());
        Assert.assertNotNull("Lo que retorna la bd no debería se null", userBd);
        Assert.assertEquals("lo que retorna la bd no es lo que se espera", userLocal, userBd);
    }



    @Test
    public void updateTest() {
        try {
            VentaEntity local = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            VentaEntity nuevo = factory.manufacturePojo(VentaEntity.class);
            nuevo.setId(local.getId());
            logica.updateVenta(nuevo);
            VentaEntity bd = em.find(VentaEntity.class, local.getId());
            Assert.assertEquals("No se actualizó correctamente", bd, nuevo);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(VendedorLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para eliminar una venta.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteBicicletaTest() throws BusinessLogicException {
        VentaEntity entity = data.get(1);
        logica.deleteVenta(entity.getId());
        VentaEntity deleted = em.find(VentaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
