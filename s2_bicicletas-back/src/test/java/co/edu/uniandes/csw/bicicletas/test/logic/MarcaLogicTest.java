/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.MarcaLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.MarcaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.MarcaPersistence;
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
public class MarcaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MarcaLogic marcaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MarcaEntity> data = new ArrayList<MarcaEntity>();

    private List<BicicletaEntity> bicicletasData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MarcaEntity.class.getPackage())
                .addPackage(MarcaLogic.class.getPackage())
                .addPackage(MarcaPersistence.class.getPackage())
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
        em.createQuery("delete from MarcaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            MarcaEntity entity = factory.manufacturePojo(MarcaEntity.class);
            em.persist(entity);
            data.add(entity);
        }

    }

    /**
     * Prueba para crear una Marca.
     *
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    @Test
    public void createMarcaTest() throws BusinessLogicException {
        MarcaEntity newEntity = factory.manufacturePojo(MarcaEntity.class);
        MarcaEntity result = marcaLogic.createMarca(newEntity);
        Assert.assertNotNull(result);
        MarcaEntity entity = em.find(MarcaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para crear una marca sin nombre
     *
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    @Test
    public void createMarcaConNombreInvalido() throws BusinessLogicException {
        MarcaEntity newEntity = factory.manufacturePojo(MarcaEntity.class);
        newEntity.setNombre("");
        try {
            marcaLogic.createMarca(newEntity);
            fail("Debió arrojar excepción");
        } catch (BusinessLogicException e) {
        }
        newEntity.setNombre(null);
        try {
            marcaLogic.createMarca(newEntity);
            Assert.fail("Debió arrojar excepción");
        } catch (BusinessLogicException e) {
        }
        newEntity.setNombre(data.get(0).getNombre());
        try {
            marcaLogic.createMarca(newEntity);
            Assert.fail("Debió arrojar excepción");
        } catch (BusinessLogicException e) {
        }

    }

    /**
     * Prueba para consultar la lista de Marcas.
     */
    @Test
    public void getMarcasTest() {
        List<MarcaEntity> list = marcaLogic.getMarcas();
        Assert.assertEquals(data.size(), list.size());
        for (MarcaEntity entity : list) {
            boolean found = false;
            for (MarcaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Marca.
     */
    @Test
    public void getMarcaTest() {
        MarcaEntity entity = data.get(0);
        MarcaEntity resultEntity = marcaLogic.getMarca(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para actualizar un Marca.
     */
    @Test
    public void updateMarcaTest() {
        MarcaEntity entity = data.get(0);
        MarcaEntity pojoEntity = factory.manufacturePojo(MarcaEntity.class);
        pojoEntity.setId(entity.getId());
        marcaLogic.updateMarca(pojoEntity.getId(), pojoEntity);
        MarcaEntity resp = em.find(MarcaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar una Marca.
     *
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    @Test
    public void deleteMarcaTest() throws BusinessLogicException {
        MarcaEntity entity = data.get(0);
        marcaLogic.deleteMarca(entity.getId());
        MarcaEntity deleted = em.find(MarcaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminar una Marca con books asociados.
     *
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    //@Test(expected = BusinessLogicException.class)
    public void deleteMarcaConBicicletasAsociadasTest() throws BusinessLogicException {
        MarcaEntity entity = factory.manufacturePojo(MarcaEntity.class);
        BicicletaEntity bike = factory.manufacturePojo(BicicletaEntity.class);
        bicicletasData.add(bike);
        entity.setBicicletas(bicicletasData);
        marcaLogic.createMarca(entity);
        marcaLogic.deleteMarca(entity.getId());
    }
}
