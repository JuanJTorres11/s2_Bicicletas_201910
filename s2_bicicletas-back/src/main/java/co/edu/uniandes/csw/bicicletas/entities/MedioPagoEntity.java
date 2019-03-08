/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andres Donoso
 */
@Entity
public class MedioPagoEntity extends BaseEntity implements Serializable {
    private Long numeroTarjeta;
    private Integer codigoVerificacion;
    private String fechaVencimiento;
    private String direccion;
    private String tipoTarjeta;
    private String tipoCredito;
    
    @PodamExclude
    @ManyToOne
    private VendedorEntity vendedor;
    
    /*@PodamExclude
    @*/
    
    /**
     * Crea un medio de pago vacío.
     */
    public MedioPagoEntity() {
        
    }

    /**
     * Crea un medio de pago con la información pasada por parámetro.
     * @param numeroTarjeta Numero de la tarjeta.
     * @param codigoVerificacion Código de verificación. null si no tiene.
     * @param fechaVencimiento Fecha de vencimiento. Formato: MM/YY
     * @param direccion Dirección de facturación
     * @param tipoTarjeta Tipo de tarjeta. tipoTarjeta = {CREDITO, DEBITO}
     * @param tipoCredito Tipo de crédito. tipoCredito = {VISA, MASTERCARD}. null si no es crédito.
     */
    public MedioPagoEntity(Long numeroTarjeta, Integer codigoVerificacion, String fechaVencimiento, String direccion, String tipoTarjeta, String tipoCredito) {
        this.numeroTarjeta = numeroTarjeta;
        this.codigoVerificacion = codigoVerificacion;
        this.fechaVencimiento = fechaVencimiento;
        this.direccion = direccion;
        this.tipoTarjeta = tipoTarjeta;
        this.tipoCredito = tipoCredito;
    }
    
    

    /**
     * @return the numeroTarjeta
     */
    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(Long numeroTarjeta) {
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

    /**
     * @return the tipoTarjeta
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * @param tipoTarjeta the tipoTarjeta to set
     */
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * @return the tipoCredito
     */
    public String getTipoCredito() {
        return tipoCredito;
    }

    /**
     * @param tipoCredito the tipoCredito to set
     */
    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    /**
     * @return the vendedor
     */
    public VendedorEntity getVendedor()
    {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(VendedorEntity vendedor)
    {
        this.vendedor = vendedor;
    }
}
