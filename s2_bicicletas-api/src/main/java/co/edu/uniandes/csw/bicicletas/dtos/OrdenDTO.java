/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.io.Serializable;

/**
 *
 * @author Mateo
 */
public class OrdenDTO implements Serializable{

    
    /**
     * Identificador de la orden
     */
    private int identificador;
    
    /**
     * Fecha de realizacion de la orden
     */
    private String fecha;
    
    /**
     * Cantidad de productos comprados en la orden
     */
    private int cantidad;
    
    /**
     * Costo total de la orden
     */
    private double costoTotal;
    
    /**
     * Medio de pago utilizado en la orden
     */
  //  private MedioPago medioPago;
    
    /**
     * Constructor por defecto
     */
    public OrdenDTO(){
        
    }

    /**
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
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
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the costoTotal
     */
    public double getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
}
