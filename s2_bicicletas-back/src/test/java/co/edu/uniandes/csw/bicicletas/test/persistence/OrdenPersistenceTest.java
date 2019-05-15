/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.persistence;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
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
public class OrdenPersistenceTest {

    @Inject
    private OrdenPersistence ordenPersistence;


    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

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
        em.createQuery("delete from OrdenEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            OrdenEntity entity = factory.manufacturePojo(OrdenEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

   /**
     * Prueba para crear un Orden.
     */
    @Test
    public void createOrdenTest() {
        PodamFactory factory = new PodamFactoryImpl();
        OrdenEntity newEntity = factory.manufacturePojo(OrdenEntity.class);

        OrdenEntity result = ordenPersistence.create(newEntity);

        Assert.assertNotNull(result);

        OrdenEntity entity = em.find(OrdenEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de premios.
     */
    @Test
    public void getOrdensTest() {
        List<OrdenEntity> list = ordenPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (OrdenEntity ent : list) {
            boolean found = false;
            for (OrdenEntity entity : data) {
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
    public void getOrdenTest() {
        OrdenEntity entity = data.get(0);
        OrdenEntity newEntity = ordenPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Test de eliminacion de una orden.
     */
     @Test
    public void deleteTest() {
        OrdenEntity orden = data.get(0);
        ordenPersistence.delete(orden.getId());
        OrdenEntity borrado = em.find(OrdenEntity.class, orden.getId());
        Assert.assertNull(borrado);
    }
}
