/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.entities;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan José Torres
 */
@Entity
public class VendedorEntity extends UsuarioEntity
{

    private static final Logger LOGGER = Logger.getLogger(VendedorEntity.class.getName());

    private String cedula;

    private String telefono;

    // @PodamExclude
    //@OneToMany (mappedBy = "vendedor")
    //private List<VentaEntity> ventas;
    public VendedorEntity()
    {

    }

    /**
     * @return the cedula
     */
    public String getCedula()
    {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula)
    {
        this.cedula = cedula;
    }

    /**
     * @return the telefono
     */
    public String getTelefono()
    {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    /**
     * @return the ventas
     *
     * public List<VentaEntity> getVentas() { return ventas; }
     */
    /**
     * @param ventas the ventas to set
     *
     * public void setVentas(List<VentaEntity> ventas) { this.ventas = ventas; }
     */
}
