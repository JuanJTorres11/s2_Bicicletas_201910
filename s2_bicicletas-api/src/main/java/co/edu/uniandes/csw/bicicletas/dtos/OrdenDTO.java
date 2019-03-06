/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.OrdenEntity;
import java.io.Serializable;

/**
 *
 * @author Mateo
 */
public class OrdenDTO implements Serializable {

    /**
     * Identificador de la orden
     */
    private Long identificador;

    /**
     * Fecha de realizacion de la orden
     */
    private String fecha;

    /**
     * Cantidad de productos comprados en la orden
     */
    private Integer cantidad;

    /**
     * Costo total de la orden
     */
    private Double costoTotal;

    /**
     * Medio de pago utilizado en la orden
     */
    //private MedioPagoDTO medioPago;

    /**
     * Comprador de la orden
     */
    private CompradorDTO comprador;

    public OrdenDTO(OrdenEntity ordenEntity) {
        if (ordenEntity != null) {
            this.identificador = ordenEntity.getId();
            this.fecha = ordenEntity.getFecha();
            this.cantidad = ordenEntity.getCantidad();
            this.costoTotal = ordenEntity.getCostoTotal();
            if (ordenEntity.getComprador() != null) {
                // this.comprador = new CompradorDTO(ordenEntity.getComprador());
            } else {
                this.comprador = null;
            }
        }
    }

    public OrdenEntity toEntity() {
        OrdenEntity ordenEntity = new OrdenEntity();
        ordenEntity.setId(this.identificador);
        ordenEntity.setFecha(this.getFecha());
        ordenEntity.setCantidad(this.getCantidad());
        ordenEntity.setCostoTotal(this.getCostoTotal());
        return ordenEntity;
    }

    /**
     * Constructor por defecto
     */
    public OrdenDTO() {

    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
     * @return the costoTotal
     */
    public Double getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    /**
     * @return the comprador
     */
    public CompradorDTO getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(CompradorDTO comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the identificador
     */
    public Long getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

}
