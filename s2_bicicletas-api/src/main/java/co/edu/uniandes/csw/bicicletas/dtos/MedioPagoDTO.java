/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.dtos;

import java.io.Serializable;

/**
 *
 * @author andydonoso
 */
public class MedioPagoDTO implements Serializable {
    private int numeroTarjeta;
    private int codigoVerificacion;
    private String vencimiento;
    private String direccion;

    public MedioPagoDTO() {

    }
}
