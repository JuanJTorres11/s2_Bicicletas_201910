/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Mateo
 */
public class OrdenDetailDTO extends OrdenDTO implements Serializable{
    
    /**
     * Lista de tipo BicicletaDTO compradas en una orden
     */
    //private List<BicicletaDTO> bicicletas;
    
    /**
     * Constructor por defecto
     */
    public OrdenDetailDTO(){
        
    }
}



