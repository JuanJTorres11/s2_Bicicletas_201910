/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.test.logic;

import co.edu.uniandes.csw.bicicletas.ejb.CategoriaLogic;
import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CategoriaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.CategoriaPersistence;
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
 * @author Andres Donoso
 */
@RunWith(Arquillian.class)
public class CategoriaLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CategoriaLogic cl;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<CategoriaEntity> data = new ArrayList<>();
    private List<BicicletaEntity> bicicletasData = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CategoriaEntity.class.getPackage())
                .addPackage(CategoriaLogic.class.getPackage())
                .addPackage(CategoriaPersistence.class.getPackage())
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
        em.createQuery("delete from CategoriaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            BicicletaEntity bicicletas = factory.manufacturePojo(BicicletaEntity.class);
            em.persist(bicicletas);
            bicicletasData.add(bicicletas);
        }
        
        for (int i = 0; i < 3; i++) {
            CategoriaEntity categoria = factory.manufacturePojo(CategoriaEntity.class);
            em.persist(categoria);
            data.add(categoria);
            if (i == 0) {
                bicicletasData.get(i).setCategoria(categoria);
            }
        }
    }
    
    @Test
    public void createCategoriaTest() throws BusinessLogicException {
        CategoriaEntity categoria = factory.manufacturePojo(CategoriaEntity.class);
        CategoriaEntity resultado = cl.createCategoria(categoria);
        Assert.assertNotNull(resultado);
        
        CategoriaEntity entity = em.find(CategoriaEntity.class, resultado.getId());
        Assert.assertEquals(categoria.getId(), entity.getId());
        Assert.assertEquals(categoria.getNombre(), entity.getNombre());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCategoriaConMismoNombreTest() throws BusinessLogicException {
        CategoriaEntity categoria = factory.manufacturePojo(CategoriaEntity.class);
        categoria.setNombre(data.get(0).getNombre());
        
        cl.createCategoria(categoria);
    }
    
    @Test
    public void getCategoriasTest() {
        List<CategoriaEntity> categorias = cl.getCategorias();
        Assert.assertEquals(data.size(), categorias.size());
        
        for(CategoriaEntity categoria: categorias) {
            boolean encontrado = false;
            for(CategoriaEntity entity: data) {
                if(entity.getId().equals(categoria.getId())) encontrado = true;
            }
            
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void getCategoriaTest() {
        CategoriaEntity categoria = data.get(0);
        CategoriaEntity resultado = cl.getCategoria(categoria.getId());
        
        Assert.assertNotNull(resultado);
        Assert.assertEquals(categoria.getId(), resultado.getId());
        Assert.assertEquals(categoria.getNombre(), resultado.getNombre());
    }
    
    @Test
    public void updateCategoriaTest() throws BusinessLogicException {
        CategoriaEntity categoria = data.get(0);
        CategoriaEntity entity = factory.manufacturePojo(CategoriaEntity.class);
        entity.setId(categoria.getId());
        cl.updateCategoria(entity);
        CategoriaEntity resultado = em.find(CategoriaEntity.class, categoria.getId());
        
        Assert.assertEquals(entity.getId(), resultado.getId());
        Assert.assertEquals(entity.getNombre(), resultado.getNombre());
    }
    
    @Test
    public void deleteCategoriaTest() throws BusinessLogicException {
        CategoriaEntity categoria = data.get(1);
        cl.deleteCategoria(categoria.getId());
        CategoriaEntity resultado = em.find(CategoriaEntity.class, categoria.getId());
        
        Assert.assertNull(resultado);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void deleteCategoriaConBicicletasTest() throws BusinessLogicException {
        CategoriaEntity categoria = data.get(0);
        cl.deleteCategoria(categoria.getId());
    }
}
