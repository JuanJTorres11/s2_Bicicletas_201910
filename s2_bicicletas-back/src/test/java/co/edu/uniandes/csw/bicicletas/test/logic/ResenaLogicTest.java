/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.ResenaLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.ResenaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.ResenaPersistence;
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
public class ResenaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyección de la dependencia a la clase ResenaLogic cuyos métodos se van a
     * probar.
     */
    @Inject
    private ResenaLogic resenaLogic;

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
    private List<ResenaEntity> data = new ArrayList<ResenaEntity>();

    private List<BicicletaEntity> dataBikes = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ResenaEntity.class.getPackage())
                .addPackage(ResenaLogic.class.getPackage())
                .addPackage(ResenaPersistence.class.getPackage())
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
        em.createQuery("delete from ResenaEntity").executeUpdate();
        em.createQuery("delete from BicicletaEntity").executeUpdate();

    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            BicicletaEntity entityB = factory.manufacturePojo(BicicletaEntity.class);
            em.persist(entityB);
            dataBikes.add(entityB);
        }
        for (int i = 0; i < 3; i++) {

            ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);
            entity.setBicicleta(dataBikes.get(1));
            entity.setCalificacion(i);
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
    public void createResenaTest() throws BusinessLogicException {
        ResenaEntity newEntity = factory.manufacturePojo(ResenaEntity.class);
        newEntity.setBicicleta(dataBikes.get(1));
        newEntity.setCalificacion(1);
        ResenaEntity result = resenaLogic.createResena(dataBikes.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        ResenaEntity entity = em.find(ResenaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());

    }

    /**
     * Prueba para crear una bicicleta con la misma referencia de otra Bicicleta
     * que ya existe.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createResenaConMismoIdTest() throws BusinessLogicException {
        ResenaEntity newEntity = factory.manufacturePojo(ResenaEntity.class);
        newEntity.setBicicleta(dataBikes.get(1));
        newEntity.setCalificacion(5);
        newEntity.setId(data.get(0).getId());
        resenaLogic.createResena(dataBikes.get(1).getId(), newEntity);
    }

    /**
     * Prueba para eliminar una Bicicleta.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteResenaTest() throws BusinessLogicException {
        ResenaEntity entity = data.get(0);
        resenaLogic.deleteResena(dataBikes.get(1).getId(), entity.getId());
        ResenaEntity deleted = em.find(ResenaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para obtener una resena por id
     */
    @Test
    public void getResenaTest() throws BusinessLogicException{

        ResenaEntity entity = data.get(0);
        ResenaEntity resultado = resenaLogic.getResena(dataBikes.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entity.getId(), resultado.getId());
        Assert.assertEquals(entity.getCalificacion(), resultado.getCalificacion());
        Assert.assertEquals(entity.getDescripcion(), resultado.getDescripcion());

    }

    /**
     * Prueba para obtener todas las resenas
     */
    @Test
    public void getResenasTest() throws BusinessLogicException{

        List<ResenaEntity> resenasEncontradas = resenaLogic.getResenas(dataBikes.get(1).getId());
        Assert.assertEquals(resenasEncontradas.size(), data.size());
         boolean existe = false;
        for (ResenaEntity r : data) {
            for (ResenaEntity r2 : resenasEncontradas) {
                if (r.getId().equals(r2.getId())) {
                    existe = true;
                    break;
                }
            }
        }
        Assert.assertTrue(existe);
    }

    /**
     * Prueba para actualizar una resena
     */
    @Test
    public void updateResenaTest() throws BusinessLogicException {
        Long idActualizar = data.get(1).getId();
        ResenaEntity nuevaR = factory.manufacturePojo(ResenaEntity.class);
        nuevaR.setCalificacion(0);
        nuevaR.setId(idActualizar);
        resenaLogic.ubdateResena(dataBikes.get(1).getId(), nuevaR);

        ResenaEntity recuperada = resenaLogic.getResena(dataBikes.get(1).getId(), idActualizar);

        Assert.assertEquals(nuevaR.getCalificacion(), recuperada.getCalificacion());
        Assert.assertEquals(nuevaR.getDescripcion(), recuperada.getDescripcion());
        Assert.assertEquals(nuevaR.getTitulo(), recuperada.getTitulo());

    }
}
