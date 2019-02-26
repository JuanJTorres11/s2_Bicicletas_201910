/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.BicicletaLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.BicicletaPersistence;
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
 * @author Andrea
 */
@RunWith(Arquillian.class)
public class BicicletaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyección de la dependencia a la clase EditorialLogic cuyos métodos se
     * van a probar.
     */
    @Inject
    private BicicletaLogic bicicletaLogic;

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
    private List<BicicletaEntity> data = new ArrayList<BicicletaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BicicletaEntity.class.getPackage())
                .addPackage(BicicletaLogic.class.getPackage())
                .addPackage(BicicletaPersistence.class.getPackage())
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
        em.createQuery("delete from BicicletaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            BicicletaEntity entity = factory.manufacturePojo(BicicletaEntity.class);

            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * Prueba para crear una Bicicleta.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createBicicletaTest() throws BusinessLogicException {
        BicicletaEntity newEntity = factory.manufacturePojo(BicicletaEntity.class);
        BicicletaEntity result = bicicletaLogic.createBicicleta(newEntity);
        Assert.assertNotNull(result);
        BicicletaEntity entity = em.find(BicicletaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
    }

    /**
     * Prueba para crear una bicicleta con la misma referencia de otra Bicicleta
     * que ya existe.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createBicicletaConMismaReferenciaTest() throws BusinessLogicException {
        BicicletaEntity newEntity = factory.manufacturePojo(BicicletaEntity.class);
        newEntity.setReferencia(data.get(0).getReferencia());
        bicicletaLogic.createBicicleta(newEntity);
    }

    /**
     * Prueba para eliminar una Bicicleta.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteBicicletaTest() throws BusinessLogicException {
        BicicletaEntity entity = data.get(1);
        bicicletaLogic.deleteBicicleta(entity.getId());
        BicicletaEntity deleted = em.find(BicicletaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /*
    @Test
    public void getBicicletaTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void getBicicletasTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void getBicicletaPorReferenciaTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void updateBicicletaTest() {
        Assert.assertTrue(true);
    }*/
}
