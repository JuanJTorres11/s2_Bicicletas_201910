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
 * @author Andres Donoso
 */
@Entity
public class MedioPagoEntity extends BaseEntity implements Serializable {
    private Integer numeroTarjeta;
    private Integer codigoVerificacion;
    private String fechaVencimiento;
    private String direccion;
    
    public MedioPagoEntity() {
        
    }

    /**
     * @return the numeroTarjeta
     */
    public Integer getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(Integer numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the codigoVerificacion
     */
    public Integer getCodigoVerificacion() {
        return codigoVerificacion;
    }

    /**
     * @param codigoVerificacion the codigoVerificacion to set
     */
    public void setCodigoVerificacion(Integer codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    /**
     * @return the fechaVencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
