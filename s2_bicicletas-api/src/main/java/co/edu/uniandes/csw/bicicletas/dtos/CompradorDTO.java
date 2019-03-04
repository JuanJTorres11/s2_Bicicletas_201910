/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.io.Serializable;

/**
 *
 * @author Juan Lozano
 */
public class CompradorDTO extends UsuarioDTO implements Serializable{
    
    /**
     * Atributo que representa el nombre del comprador.
     */
    private String nombre;
    
    /** 
     * Atributo que representa el login del comprador. 
     */
    private String login;
    
    /** 
     * Atributo que representa la clave del comprador.
     */
    private String password;
    
    /**
     * Metodo constructor de la clase CompradorDTO
     */
    public CompradorDTO()
    {
        
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
