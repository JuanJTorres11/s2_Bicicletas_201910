/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.bicicletas.dtos;

import java.util.List;

/**
 *
 * @author Juan José Torres
 */
public class UsuarioDetailDTO extends UsuarioDTO
{
    private List<MedioPagoDTO> medioPagos;
    
    public UsuarioDetailDTO () {
    }
}
