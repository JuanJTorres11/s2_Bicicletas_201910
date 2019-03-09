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
        } else if (cp.findByLogin(pComprador.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un comprador con el nombre \"" + pComprador.getLogin() + "\"");
        }

        cp.create(pComprador);

        LOGGER.log(Level.INFO, "Termina proceso de crear una comprador.");
        return pComprador;
    }

    public void deleteComprador(Long id) {
        LOGGER.log(Level.INFO, "se borrará el Vendedor con id {}", id);
        LOGGER.log(Level.INFO, "se borró al vendedor con id {}", id);
        cp.delete(id);
    }

    /**
     * Retorna todos los compradores registrados.
     *
     * @return una lista con todos los compradores.
     */
    public List<CompradorEntity> findAllCompradores() {
        LOGGER.log(Level.INFO, "se buscarán todos los Compradores");
        List<CompradorEntity> compradores = cp.findAll();
        if (compradores == null || compradores.isEmpty())
        {
            LOGGER.log(Level.SEVERE, "No existen Compradores");
        }
        LOGGER.log(Level.INFO, "Se termina la busqueda de los Compradores");
        return compradores;
    }

    /**
     * obtener el comprador con un login dado por parametro.
     *
     * @param pLoggin el login del comprador a buscar.
     * @return el comprador con el login correspondiente.
     */
    public CompradorEntity findLogin(String pLoggin) {
        LOGGER.log(Level.INFO, "Inicia proceso de búsqueda de un comprador por Loggin.");

        CompradorEntity ret = cp.findByLogin(pLoggin);

        LOGGER.log(Level.INFO, "Termina proceso de búsqueda de un comprador por Loggin");
        return ret;
    }

    /**
     * Se actualiza un nuevo vendedor
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
        } else if (cp.findByLogin(pComprador.getLogin()) != null) {
            throw new BusinessLogicException("Ya existe un comprador con el nombre \"" + pComprador.getLogin() + "\"");
        }

        if (pComprador.getPassword().isEmpty()) {
            throw new BusinessLogicException("El password del comprador no puede estar vacío.");
        }

        if (pComprador.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre del comprador no puede estar vacío.");
        } else if (cp.findByLogin(pComprador.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un comprador con el nombre \"" + pComprador.getLogin() + "\"");
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
    public CompradorEntity findComprador(Long id) {
        LOGGER.log(Level.INFO, "Se buscará el comprador con id {}", id);
        CompradorEntity buscado = cp.find(id);
        if (buscado == null) {
            LOGGER.log(Level.SEVERE, "No existe el comprador con id {}", id);
        }
        LOGGER.log(Level.INFO, "Se termina la busqueda del comprador con id {}", id);
        return buscado;
    }

}
