/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.OrdenLogic;
import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.OrdenPersistence;
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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Mateo
 */
@RunWith(Arquillian.class)
public class OrdenLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private OrdenLogic ordenLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<OrdenEntity> data = new ArrayList<OrdenEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenEntity.class.getPackage())
                .addPackage(OrdenLogic.class.getPackage())
                .addPackage(OrdenPersistence.class.getPackage())
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
        em.createQuery("delete from OrdenEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            OrdenEntity entity = factory.manufacturePojo(OrdenEntity.class);
            entity.setCostoTotal(i + 10.0);
            entity.setCantidad(i);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Orden.
     *
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    @Test
    public void createOrdenTest() throws Exception {
        OrdenEntity newEntity = factory.manufacturePojo(OrdenEntity.class);
        newEntity.setCostoTotal(10.0);
        newEntity.setCantidad(1);
        OrdenEntity result = ordenLogic.createOrden(newEntity);
        Assert.assertNotNull(result);
        OrdenEntity entity = em.find(OrdenEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para crear una Orden con un costo negativo
     *
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    @Test
    public void createOrdenConCostoInvalido() throws Exception {
        OrdenEntity newEntity = factory.manufacturePojo(OrdenEntity.class);
        newEntity.setCostoTotal(-10.0);
        try {
            ordenLogic.createOrden(newEntity);
            fail("Debió arrojar excepción");
        } catch (BusinessLogicException e) {}
        newEntity.setCostoTotal(null);

        try {
            ordenLogic.createOrden(newEntity);
            fail("Debió arrojar excepción");
        } catch (BusinessLogicException e) {}
    }
    
    /**
     * Prueba para consultar la lista de Ordenes.
     */
    @Test
    public void getOrdenesTest() {
        List<OrdenEntity> list = ordenLogic.getOrdenes();
        Assert.assertEquals(data.size(), list.size());
        for (OrdenEntity entity : list) {
            boolean found = false;
            for (OrdenEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Orden.
     */
    @Test
    public void getOrdenTest() {
        OrdenEntity entity = data.get(0);
        OrdenEntity resultEntity = ordenLogic.getOrden(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
}
