/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.MedioPagoPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres Donoso
 */
@Stateless
public class MedioPagoLogic {
    private static final Logger LOGGER = Logger.getLogger(MedioPagoLogic.class.getName());
    
    public final static String DEBITO = "Débito";
    public final static String CREDITO = "Crédito";
    public final static String VISA = "VISA";
    public final static String MASTERCARD = "MASTERCARD";
    
    @Inject private MedioPagoPersistence mpp;
    
    /**
     * Crea un nuevo medio de pago con la entidad pasada por parámetro.
     * @param medioPago medio de pago que se va a crear. medioPago != null.
     * @throws BusinessLogicException   1. Si el número de la tarjeta es null.
     *                                  2. Si el medio de pago ya existe.
     *                                  3. Si el formato del número de la tarjeta no es válido.
     *                                  4. Si el formato del código de verificación no es válido.
     *                                  5. Si la dirección está vacía o es null.
     *                                  6. Si el tipo de la tarjeta está vacía o es null.
     *                                  7. Si el tipo de la tarjeta no es válido.
     *                                  8. Si el tipo de crédito no es válido.
     * @return medio de pago creado.
     */
    public MedioPagoEntity createMedioPago(MedioPagoEntity medioPago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Empezando proceso de crear un medio de pago.");
        
        /**
         * Reglas de negocio
         */
        verificarReglasDeNegocio(medioPago);
        
        //Creación del medio de pago.
        mpp.create(medioPago);
        
        LOGGER.log(Level.INFO, "Terminando proceso de crear un medio de pago.");
        return medioPago;
    }
    
    /**
     * Retorna el medio de pago con el id dado.
     * @param medioPagoId id del medio de pago.
     * @return medio de pago encontrado.
     */
    public MedioPagoEntity getMedioPago(Long medioPagoId) {
        LOGGER.log(Level.INFO, "Comenzando proceso de dar el medio pago con id = {0}", medioPagoId);
        
        MedioPagoEntity medioPago = mpp.find(medioPagoId);
        
        LOGGER.log(Level.INFO, "Terminando proceso de dar el medio pago con id = {0}", medioPagoId);
        return medioPago;
    }
    
    /**
     * Actualiza el medio de pago.
     * @param medioPago medio de pago actualizado. medioPago != null.
     * @throws BusinessLogicException   1. Si el medio de pago no existe.
     *                                  2. Si el número de la tarjeta es null.
     *                                  3. Si el medio de pago ya existe.
     *                                  4. Si el formato del número de la tarjeta no es válido.
     *                                  5. Si el formato del código de verificación no es válido.
     *                                  6. Si la dirección está vacía o es null.
     *                                  7. Si el tipo de la tarjeta está vacía o es null.
     *                                  8. Si el tipo de la tarjeta no es válido.
     *                                  9. Si el tipo de crédito no es válido.
     * @return medio de pago actualizado.
     */
    public MedioPagoEntity updateMedioPago(MedioPagoEntity medioPago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Comenzando proceso de actualizar el medio pago");
        
        verificarReglasDeNegocio(medioPago);
        
        //Actualización de medio de pago
        mpp.update(medioPago);
        
        LOGGER.log(Level.INFO, "Terminando proceso de actualizar el medio pago");
        return medioPago;
    }
    
    /**
     * Elimina el medio de pago con el id especificado.
     * @param medioPagoId Id del medio de pago. medioPagoId != null.
     * @throws BusinessLogicException   1. Si no existe el medio de pago.
     */
    public void deleteMedioPago(Long medioPagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Comenzando proceso de eliminar el medio pago");
        
        if(mpp.find(medioPagoId) == null) {
            throw new BusinessLogicException("No existe el medio de pago con el id especificado.");
        }
        
        mpp.delete(medioPagoId);
        LOGGER.log(Level.INFO, "Terminando proceso de eliminar el medio pago");
    }
    
    /**
     * Verifica las reglas de negocio.
     * @param medioPago medio de pago a verificar. medioPago != null.
     * @throws BusinessLogicException   1. Si el número de la tarjeta es null.
     *                                  2. Si el medio de pago ya existe.
     *                                  3. Si el formato del número de la tarjeta no es válido.
     *                                  4. Si el formato del código de verificación no es válido.
     *                                  5. Si la dirección está vacía o es null.
     *                                  6. Si el tipo de la tarjeta está vacía o es null.
     *                                  7. Si el tipo de la tarjeta no es válido.
     *                                  8. Si el tipo de crédito no es válido.
     */
    private void verificarReglasDeNegocio(MedioPagoEntity medioPago) throws BusinessLogicException {
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
            int digito = digitos.charAt(i) - '0';
            int numero = posicion%2 == 0 ? digito *2 : digito;
            if(numero > 9) numero -= 9;
            
            suma += numero;
            
            posicion ++;
        }
        
        int digitoVerificacion = digitos.charAt(digitos.length() - 1) - '0';
        int verificacion = 10 - (suma%10);
        if(verificacion != digitoVerificacion) {
            throw new BusinessLogicException("El número de la tarjeta no es válido.");
        }
        
        //Código de verificación
        if(medioPago.getCodigoVerificacion() != null) {
            String codigoVerificacion = medioPago.getCodigoVerificacion().toString().trim();
            if(medioPago.getTipoTarjeta().equals(CREDITO)) {
                if(codigoVerificacion.length() < 3 ||
                        codigoVerificacion.length() > 4 ||
                        medioPago.getCodigoVerificacion() < 0) {
                    throw new BusinessLogicException("El código de verificación no tiene un formato válido");
                }

                int primerDigito = digitos.charAt(0) - '0';
                //Número de tarjeta
                if(medioPago.getTipoCredito().equals(VISA) && 
                        primerDigito != 4) {
                    throw new BusinessLogicException("El número de la tarjeta no es válido.");
                }

                int primerosDigitos = Integer.parseInt(digitos.substring(0, 2));
                if(medioPago.getTipoCredito().equals(MASTERCARD) && primerosDigitos < 51 || 
                        primerosDigitos > 55) {
                    throw new BusinessLogicException("El número de la tarjeta no es válido.");
                }
            }
        }
        
        //Fecha de vencimiento
        String fecha = medioPago.getFechaVencimiento();
        if(fecha == null || fecha.isEmpty()) {
            throw new BusinessLogicException("La fecha de vencimiento no puede estar vacía.");
        }
        
        try {
            verificarFecha(fecha, "MM/YY");
        } catch(ParseException e) {
            throw new BusinessLogicException("La fecha de vencimiento no está en el formato correcto");
        }
        
        String[] componentes = fecha.split("/");
        int mesVencimiento = Integer.valueOf(componentes[0]);
        int anioVencimiento = Integer.valueOf(componentes[1]);
        if(anioVencimiento < 0 || anioVencimiento > 99) {
            throw new BusinessLogicException("El año no es válido.");
        } else {
            if(mesVencimiento < 1 || mesVencimiento > 12) {
                throw new BusinessLogicException("El mes no es válido.");
            }
            
            Calendar c = Calendar.getInstance();
            int anioActual = c.get(Calendar.YEAR) % 100;
            int mesActual = c.get(Calendar.MONTH) + 1;
            if(anioVencimiento < anioActual || 
                    (anioVencimiento == anioActual && mesVencimiento < mesActual)) {
                throw new BusinessLogicException("La tarjeta ya está vencida.");
            }
        }
        
        //Dirección
        if(medioPago.getDireccion() == null || medioPago.getDireccion().isEmpty()) {
            throw new BusinessLogicException("La dirección no puede estar vacía.");
        }
        
        //Tipo de tarjeta
        String tipoTarjeta = medioPago.getTipoTarjeta();
        if(tipoTarjeta == null || tipoTarjeta.isEmpty()) {
            throw new BusinessLogicException("El tipo de tarjeta no puede estar vacío.");
        }
        
        if(!tipoTarjeta.equals(CREDITO) && !tipoTarjeta.equals(DEBITO)) {
            throw new BusinessLogicException("El tipo de la tarjeta no es válido");
        }
        
        //tipoCredito
        String tipoCredito = medioPago.getTipoCredito();
        if(tipoCredito != null) {
            if(!tipoCredito.equals(VISA) && !tipoCredito.equals(MASTERCARD)) {
                throw new BusinessLogicException("El tipo de crédito no es válido");
            }
        }
    }
    
    /**
     * Verifica si la fecha está en el formato introducido por parámetro.
     * @param fecha Fecha que se quiere verificar. fecha != null
     * @param formato Formato en el que se quiere verificar. formato != null && != "".
     * @throws ParseException   1. Si el formato no es correcto.
     */
    private void verificarFecha(String fecha, String formato) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat(formato);
        f.setLenient(false);
        
        f.parse(fecha);
    }
}
