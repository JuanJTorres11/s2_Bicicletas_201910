/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import java.io.Serializable;

/**
 *
 * @author Andres Donoso
 */
public class MedioPagoDTO implements Serializable {
    public final static String DEBITO = "Debito";
    public final static String CREDITO = "Credito";
    public final static String VISA = "VISA";
    public final static String MASTERCARD = "MASTERCARD";
    
    private Long id;
    private Long numeroTarjeta;
    private Integer codigoVerificacion;
    private String vencimiento;
    private String direccion;
    private String tipoTarjeta;
    private String tipoCredito;

    public MedioPagoDTO() {

    }
    
    public MedioPagoEntity toEntity() {
        MedioPagoEntity medioPago = new MedioPagoEntity(numeroTarjeta, codigoVerificacion, vencimiento, 
                direccion, tipoTarjeta, tipoCredito);
        medioPago.setId(id);
        return medioPago;
    }
    
    /**
     * Construye una categoría a partir de la entidad.
     * @param medioPago entidad de la categoria.
     */
    public MedioPagoDTO(MedioPagoEntity medioPago) {
        if(medioPago != null) {
            this.id = medioPago.getId();
            this.numeroTarjeta = medioPago.getNumeroTarjeta();
            this.codigoVerificacion = medioPago.getCodigoVerificacion();
            this.direccion = medioPago.getDireccion();
            this.tipoCredito = medioPago.getTipoCredito();
            this.tipoTarjeta = medioPago.getTipoTarjeta();
            this.vencimiento = medioPago.getFechaVencimiento();
        }
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
    public int getCodigoVerificacion() {
        return codigoVerificacion;
    }

    /**
     * @param codigoVerificacion the codigoVerificacion to set
     */
    public void setCodigoVerificacion(int codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    /**
     * @return the vencimiento
     */
    public String getVencimiento() {
        return vencimiento;
    }

    /**
     * @param vencimiento the vencimiento to set
     */
    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
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
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }
}
