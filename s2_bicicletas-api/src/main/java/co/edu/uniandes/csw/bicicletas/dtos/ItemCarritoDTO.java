/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import java.io.Serializable;
import co.edu.uniandes.csw.bicicletas.entities.ItemCarritoEntity;

/**
 *
 * @author Juan Lozano
 */
public class ItemCarritoDTO implements Serializable {

    /**
     * Identificador del item del carrito.
     */
    private Long id;

    /**
     * Cantidad de productos que entrar al carrito.
     */
    private Integer cantidad;
        
   private CompradorEntity comprador;

   private BicicletaEntity bicicleta;

    public ItemCarritoDTO(ItemCarritoEntity itemEntity) {
        if (itemEntity != null) {
            this.id = itemEntity.getId();
            this.cantidad = itemEntity.getCantidad();
            this.bicicleta = itemEntity.getBicicleta();
            this.comprador = itemEntity.getComprador();
        }
    }

    public ItemCarritoEntity toEntity() {
        ItemCarritoEntity itemEntity = new ItemCarritoEntity();
        itemEntity.setId(this.id);
        itemEntity.setCantidad(this.cantidad);
        itemEntity.setComprador(this.comprador);
        itemEntity.setBicicleta(this.bicicleta);
        return itemEntity;
    }

    /**
     * @return the identificador
     */
    public Long getId() {
        return id;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setId(Long identificador) {
        this.id = identificador;
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
