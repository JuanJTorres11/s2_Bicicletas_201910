/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import java.util.List;

/**
 * @author Juan José Torres
 */
public class VendedorDetailDTO extends VendedorDTO
{

    private List<VentaDTO> ventas;

    public VendedorDetailDTO()
    {
        super();
    }

    public VendedorDetailDTO (VendedorEntity vendedor)
    {
        super();
    }

    /**
     * @return the ventas
     */
    public List<VentaDTO> getVentas()
    {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(List<VentaDTO> ventas)
    {
        this.ventas = ventas;
    }
}
