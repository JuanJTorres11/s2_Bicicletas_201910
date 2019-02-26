/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.MedioPagoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Andres Donoso
 */
public class MedioPagoLogic {
    private static final Logger LOGGER = Logger.getLogger(MedioPagoLogic.class.getName());
    
    public final static String DEBITO = "Débito";
    public final static String CREDITO = "Crédito";
    public final static String VISA = "VISA";
    public final static String MASTERCARD = "MASTERCARD";
    
    @Inject private MedioPagoPersistence mpp;
    
    public MedioPagoEntity createMedioPago(MedioPagoEntity medioPago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Empezando proceso de crear un medio de pago.");
        
        /**
         * Reglas de negocio
         */
        
        //Número de tarjeta
        if(medioPago.getNumeroTarjeta() == null) {
            throw new BusinessLogicException("El numero de la tarjeta no puede ser null");
        } else if(mpp.findByNumber(medioPago.getNumeroTarjeta()) != null) {
            throw new BusinessLogicException("Ya existe un medio de pago con este número.");
        }
        
        String digitos = medioPago.getNumeroTarjeta().toString().trim();
        if(digitos.length() < 13 || digitos.length() > 18 || medioPago.getNumeroTarjeta() < 0) {
            throw new BusinessLogicException("El número de la tarjeta no tiene un formato válido");
        }
        
        int suma = 0, posicion = 0;
        for(int i = digitos.length() - 2; i >= 0; i--) {
            int numero = posicion%2 == 0 ? digitos.charAt(i)*2 : digitos.charAt(i);
            if(numero > 9) numero -= 9;
            
            suma += numero;
            
            posicion ++;
        }
        if((10 - (suma%10)) != digitos.charAt(digitos.length() - 1)) {
            throw new BusinessLogicException("El número de la tarjeta no es válido.");
        }
        
        //Código de verificación
        String codigoVerificacion = medioPago.getCodigoVerificacion().toString().trim();
        if(medioPago.getTipoTarjeta().equals(CREDITO)) {
            if(codigoVerificacion.length() < 3 ||
                    codigoVerificacion.length() > 4 ||
                    medioPago.getCodigoVerificacion() < 0) {
                throw new BusinessLogicException("El código de verificación no tiene un formato válido");
            }
            
            //Número de tarjeta
            if(medioPago.getTipoCredito().equals(VISA) && 
                    Integer.valueOf(digitos.charAt(0)) != 4) {
                throw new BusinessLogicException("El número de la tarjeta no es válido.");
            }
            
            int primerosDigitos = Integer.parseInt(digitos.substring(0, 1));
            if(medioPago.getTipoCredito().equals(MASTERCARD) && primerosDigitos < 51 || 
                    primerosDigitos > 55) {
                throw new BusinessLogicException("El número de la tarjeta no es válido.");
            }
        }
        
        //Fecha de vencimiento
        String fecha = medioPago.getFechaVencimiento();
        if(medioPago.getFechaVencimiento() == null || medioPago.getFechaVencimiento().isEmpty()) {
            throw new BusinessLogicException("La fecha de vencimiento no puede estar vacía.");
        }
        
        //Creación del medio de pago.
        mpp.create(medioPago);
        return medioPago;
    }
}
