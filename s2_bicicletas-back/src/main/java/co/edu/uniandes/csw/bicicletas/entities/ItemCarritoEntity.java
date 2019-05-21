/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Juan Lozano
 */
@Entity
public class ItemCarritoEntity extends BaseEntity implements Serializable {
    
    /**
    * Cantidad de productos que entrar al carrito.
    */
    private Integer cantidad;
    
    
    /**
     * Constructor vacio
     */
    public ItemCarritoEntity() {

    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
