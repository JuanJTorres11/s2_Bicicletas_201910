/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import co.edu.uniandes.csw.bicicletas.entities.CompradorEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Lozano
 */
public class CompradorDTO extends UsuarioDTO implements Serializable{
    
    /**
     * Metodo constructor de la clase CompradorDTO
     */
   public CompradorDTO()
   {
       super();
   }
        
       public CompradorDTO(CompradorEntity pComprador)
    {
        super(pComprador);
    }
   /**
    * Metodo que convierte un compradorEntity
    * @return retorna un compradorEntity
    */
    public CompradorEntity toEntity()
    {
        CompradorEntity vendedor = new CompradorEntity();
        vendedor.setId(id);
        vendedor.setNombre(nombre);
        vendedor.setLogin(login);
        vendedor.setPassword(password);
        return vendedor;
    }
}
