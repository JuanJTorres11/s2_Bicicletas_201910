/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;

/**
 * @author Juan José Torres
 */
public class VendedorDTO extends UsuarioDTO
{

    private Long telefono;

    public VendedorDTO()
    {
        super();
    }

    public VendedorDTO(VendedorEntity vendedor)
    {
        super(vendedor);
        if (vendedor != null)
        {
            telefono = vendedor.getTelefono();
        }
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
    
    public VendedorEntity toEntity()
    {
        VendedorEntity vendedor = new VendedorEntity();
        vendedor.setId(id);
        vendedor.setNombre(nombre);
        vendedor.setLogin(login);
        vendedor.setPassword(password);
        vendedor.setTelefono(telefono);
        return vendedor;
    }
}
