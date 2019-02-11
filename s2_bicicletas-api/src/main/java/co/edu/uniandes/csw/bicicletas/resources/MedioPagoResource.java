/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.resources;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Andres Donoso
 */
@Path("medioPagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MedioPagoResource {
    private static final Logger LOGGER = Logger.getLogger(MedioPagoResource.class.getName());
    
    
}
