/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Entity;

/**
 *
 * @author Juan José Torres
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable
{
    private static final Logger LOGGER = Logger.getLogger(UsuarioEntity.class.getName());
    
    private String nombre;
    
    private String cedula;
    
    private String login;
    
    private String password;

public UsuarioEntity  () {
}

}