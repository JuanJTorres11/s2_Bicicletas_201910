/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.VentaEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import javax.ejb.Stateless;

/**
 *
 * @author Juan Lozano
 */
@Stateless
public class VentaLogic 
{
    public VentaEntity create(VentaEntity edi) throws BusinessLogicException
    {
        if(edi == null)
        {
            
        }
        // verificar que no hay login iguales, implementar en la clase persistence el findbyname para poder implementarlo en esta.
        //if()
       // {
        
       // }
        
        return null;
    }
}
