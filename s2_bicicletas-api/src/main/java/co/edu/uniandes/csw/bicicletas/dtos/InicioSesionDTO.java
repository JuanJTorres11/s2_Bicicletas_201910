/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.io.Serializable;

/**
 * Representa un objeto con login y password para poderlo enviar como JSON y
 * autenticar usuarios.
 * @author Juan José Torres <jj.torresr@uniandes.edu.co>
 */
public class InicioSesionDTO implements Serializable
{

    private String login;

    private String password;

    public InicioSesionDTO()
    {
    }

    public InicioSesionDTO(String log, String pass)
    {
        login = log;
        password = pass;
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
