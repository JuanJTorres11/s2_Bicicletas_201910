/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.CompradorPersistence;
import java.util.List;
import java.util.logging.*;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class CompradorLogic {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CompradorLogic.class.getName());

    /**
     * Persistencia de la clase comprador
     */
    @Inject
    private CompradorPersistence cp;

    /**
     * Se crea un nuevo Comprador
     *
     * @param pComprador usuario a crear en el sistema
     * @return el Comprador creado
     * @throws BusinessLogicException si se rompe alguna regla de negocio. <br>
     * 1.Ninguno de los atributos es vacio o nulo. <br>
     * 2.No hay otro comprador con el mismo login. <br>
     */
    public CompradorEntity createComprador(CompradorEntity pComprador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un comprador.");

        if (pComprador.getLogin().isEmpty()) {
            throw new BusinessLogicException("El login del comprador no puede estar vacío.");
        } else if (cp.findByLogin(pComprador.getLogin()) != null) {
            throw new BusinessLogicException("Ya existe un comprador con el nombre \"" + pComprador.getLogin() + "\"");
        }

        if (pComprador.getPassword().isEmpty()) {
            throw new BusinessLogicException("El password del comprador no puede estar vacío.");
        }

        if (pComprador.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre del comprador no puede estar vacío.");
        }

        cp.create(pComprador);

        LOGGER.log(Level.INFO, "Termina proceso de crear una comprador.");
        return pComprador;
    }

    /**
     * Elimina una venta asignada por parametro.
     *
     * @param id de la venta a eliminar.
     */
    public void deleteComprador(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la comprador con id = {0}", id);
        cp.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la editorial con id = {0}", id);
    }

    /**
     * Retorna todos los compradores registrados.
     *
     * @return una lista con todos los compradores.
     */
    public List<CompradorEntity> getCompradores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las editoriales");
        List<CompradorEntity> ventas = cp.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las editoriales");
        return ventas;
    }

    /**
     * obtener el comprador con un login dado por parametro.
     *
     * @param pLoggin el login del comprador a buscar.
     * @return el comprador con el login correspondiente.
     */
    public CompradorEntity getByLogin(String pLoggin) {
        LOGGER.log(Level.INFO, "Inicia proceso de búsqueda de un comprador por Loggin.");

        CompradorEntity ret = cp.findByLogin(pLoggin);
        if (ret == null) {
            LOGGER.log(Level.SEVERE, "No existe el vendedor con login {0}", pLoggin);
        }

        LOGGER.log(Level.INFO, "Termina proceso de búsqueda de un comprador por Loggin");
        return ret;
    }

    /**
     * Se actualiza un nuevo vendedor
     *
     * @param pComprador usuario a actualizar en el sistema
     * @return el Comprador actualizado
     * @throws BusinessLogicException si se rompe alguna regla de negocio. <br>
     * 1.Ninguno de los atributos es vacio o nulo. <br>
     * 2.No hay otro comprador con el mismo login. <br>
     */
    public CompradorEntity updateComprador(CompradorEntity pComprador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar Comprador.");
        if (pComprador.getLogin().isEmpty()) {
            throw new BusinessLogicException("El login del comprador no puede estar vacío.");
        }

        if (pComprador.getPassword().isEmpty()) {
            throw new BusinessLogicException("El password del comprador no puede estar vacío.");
        }

        if (pComprador.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre del comprador no puede estar vacío.");
        }

        CompradorEntity ret = cp.update(pComprador);

        LOGGER.log(Level.INFO, "Termina proceso de actualizar Comprador.");

        return ret;
    }

    /**
     * Se retorna el Comprador con id asignado.
     *
     * @param id ID del comprador a buscar
     * @return comprador con id o null si no existe.
     */
    public CompradorEntity getComprador(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el comprador con id = {0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        CompradorEntity compradorEntity = cp.find(id);
        if (compradorEntity == null) {
            LOGGER.log(Level.SEVERE, "La comprador con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el comprador con id = {0}", id);
        return compradorEntity;
    }

}
