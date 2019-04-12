package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

/**
 * @author Juan José Torres
 */
@MappedSuperclass
public abstract class UsuarioEntity extends BaseEntity implements Serializable
{
    /**
     * Nombre del usuario.
     */
    protected String nombre;

    /**
     * Login del usuario.
     */
    protected String login;

    /**
     * Contraseña del usuario.
     */
    protected String password;

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
}
