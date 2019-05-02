/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import co.edu.uniandes.csw.bicicletas.podam.TelefonoStrategy;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Juan José Torres
 */
@Entity
public class VendedorEntity extends UsuarioEntity
{

    /**
     * Telefono del vendedor.
     */
    @PodamStrategyValue(TelefonoStrategy.class)
    private Long telefono;

    /**
     * Lista de ventas asociadas al vendedor.
     */
    @PodamExclude
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<VentaEntity> ventas;

    /**
     * Lista de medios de pago asociadas al vendedor.
     */
    @PodamExclude
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MedioPagoEntity> mediosPago;

    /**
     * 
     * Constructor vacio.
     */
    public VendedorEntity ()
    {
        this.nombre = "";
        this.login = "";
        this.password = "";
        this.telefono = -1L;
        this.ventas = new ArrayList<>();
        this.mediosPago = new ArrayList<>();
    }
    /**
     * @return the telefono
     */
    public Long getTelefono()
    {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Long telefono)
    {
        this.telefono = telefono;
    }

    /**
     * @return the ventas
     */
    public List<VentaEntity> getVentas()
    {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(List<VentaEntity> ventas)
    {
        this.ventas = ventas;
    }

    /**
     * @return the mediosPago
     */
    public List<MedioPagoEntity> getMediosPago()
    {
        return mediosPago;
    }

    /**
     * @param mediosPago the mediosPago to set
     */
    public void setMediosPago(List<MedioPagoEntity> mediosPago)
    {
        this.mediosPago = mediosPago;
    }
}