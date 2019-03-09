
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Mateo
 */

@Entity
public class OrdenEntity extends BaseEntity implements Serializable {

   
    
    
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
    @PodamExclude
    @OneToOne(mappedBy = "orden", fetch=FetchType.LAZY)
    private MedioPagoEntity medioPago;
    
    @PodamExclude
    @ManyToOne
    private CompradorEntity comprador;
    
    @PodamExclude
    @OneToMany(mappedBy = "orden")
    private List<BicicletaEntity> bicicletasOrden;

    
    public OrdenEntity()
    {
        
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
     * @return the bicicletasOrden
     */
    public List<BicicletaEntity> getBicicletasOrden() {
        return bicicletasOrden;
    }

    /**
     * @param bicicletasOrden the bicicletasOrden to set
     */
    public void setBicicletasOrden(List<BicicletaEntity> bicicletasOrden) {
        this.bicicletasOrden = bicicletasOrden;
    }
    
     /**
     * @return the medioPago
     */
    public MedioPagoEntity getMedioPago() {
        return medioPago;
    }

    /**
     * @param medioPago the medioPago to set
     */
    public void setMedioPago(MedioPagoEntity medioPago) {
        this.medioPago = medioPago;
    }
    
    

}
