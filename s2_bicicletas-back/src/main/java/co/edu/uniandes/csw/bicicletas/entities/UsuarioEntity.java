/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.MappedSuperclass;

/**
 * @author Juan José Torres
 */
@MappedSuperclass
public abstract class UsuarioEntity extends BaseEntity implements Serializable
{

    private static final Logger LOGGER = Logger.getLogger(UsuarioEntity.class.getName());

    private String nombre;

    private String login;

    private String password;

    public UsuarioEntity()
    {
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the medioPagos
     *
     * public List<MedioPagoEntity> getMedioPagos() { return medioPagos; }
     */
    /**
     * @param medioPagos the medioPagos to set
     *
     * public void setMedioPagos(List<MedioPagoEntity> medioPagos) {
     * this.medioPagos = medioPagos; }
     */
}
