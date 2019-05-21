/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

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
    
    
    @PodamExclude
    @ManyToOne
    private CompradorEntity comprador;
    
    @PodamExclude
    @OneToOne
    private BicicletaEntity bicicleta;
    
    
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

    /**
     * @return the comprador
     */
    public CompradorEntity getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(CompradorEntity comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the bicicleta
     */
    public BicicletaEntity getBicicleta() {
        return bicicleta;
    }

    /**
     * @param bicicleta the bicicleta to set
     */
    public void setBicicleta(BicicletaEntity bicicleta) {
        this.bicicleta = bicicleta;
    }
    
    
    
}
