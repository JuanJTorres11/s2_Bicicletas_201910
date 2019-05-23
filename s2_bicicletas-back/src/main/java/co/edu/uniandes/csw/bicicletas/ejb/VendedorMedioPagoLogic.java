package co.edu.uniandes.csw.bicicletas.ejb;

import co.edu.uniandes.csw.bicicletas.entities.MedioPagoEntity;
import co.edu.uniandes.csw.bicicletas.entities.VendedorEntity;
import co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bicicletas.persistence.MedioPagoPersistence;
import co.edu.uniandes.csw.bicicletas.persistence.VendedorPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Juan José Torres-Andres Donoso
 */
@Stateless
public class VendedorMedioPagoLogic
{

    private static final Logger LOGGER = Logger.getLogger(VendedorMedioPagoLogic.class.getName());

    /**
     * Conexión a la persistencia del vendedor.
     */
    @Inject
    private VendedorPersistence vendedorPersistence;

    /**
     * Conexión a la persistencia del medio de pago.
     */
    @Inject
    private MedioPagoPersistence medioPagoPersistence;
    
    public static final String DEBITO = "Debito";
    public static final String CREDITO = "Credito";
    public static final String VISA = "VISA";
    public static final String MASTERCARD = "MASTERCARD";

    /**
     * Agrega un nuevo medio de pago al vendedor.
     *
     * @param medio Medio de pago que se desea agregar.
     * @param vendedorId Id del vendedor al que se la va a agregar el medio de
     * pago.
     * @return medio de pago agregado.
     * @throws co.edu.uniandes.csw.bicicletas.exceptions.BusinessLogicException
     */
    public MedioPagoEntity addMedioPago(Long vendedorId, MedioPagoEntity medio) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un medio de pago a un vendedor con id = {0}", vendedorId);
        if(medioPagoPersistence.findByNumberAndVendedor(vendedorId, medio.getNumeroTarjeta()) != null) {
            throw new BusinessLogicException("Ya existe un medio de pago con este número.");
        }
        verificarReglasDeNegocio(medio);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedorId);
        vendedorEntity.getMediosPago().add(medio);
        medio.setVendedor(vendedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un medio de pago al vendedor con id = {0}", vendedorId);
        return medio;
    }

    /**
     * Retorna una lista con los medios de pago del vendedor.
     * @param vendedorId Id del vendedor.
     * @return lista con los medios de pago.
     */
    public List<MedioPagoEntity> getMediosPago(Long vendedorId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los medios de pago del vendedor con id = {0}", vendedorId);
        return vendedorPersistence.find(vendedorId).getMediosPago();
    }

    /**
     * Retorna el medio de pago asociado al vendedor.
     * @param vendedorId Id del vendedor.
     * @param medioPagoId Id del medio de pago.
     * @return medio de pago asociado al vendedor.
     * @throws BusinessLogicException si el medio de pago no existe.
     */
    public MedioPagoEntity getMedioPago(Long vendedorId, Long numero) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el medio de pago con id: {0} del vendedor con id: {1}", new Object[] {vendedorId, numero});
        Long medioPagoId = medioPagoPersistence.findByNumber(numero).getId();
        MedioPagoEntity medioPago = medioPagoPersistence.findByVendedor(vendedorId, medioPagoId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el medio de pago con id: {0} del vendedor con id: {1} ", new Object[] {vendedorId, numero});
        if (medioPago == null)
        {
            throw new BusinessLogicException("El medio de pago no está asociado al vendedor");
        }
        return medioPago;
    }

    /**
     * Reemplaza los medios de pago de un vendedor.
     * @param vendedorId Id del vendedor.
     * @param mediosPago Medios de pago que se agregarán.
     * @return lista de medios de pago actualizada.
     */
    public List<MedioPagoEntity> replaceMedioPagos(Long vendedorId, List<MedioPagoEntity> mediosPago)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el vendedor con id = {0}", vendedorId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedorId);
        List<MedioPagoEntity> medioPagoList = medioPagoPersistence.findAll();
        for (MedioPagoEntity medioPago : medioPagoList)
        {
            if (mediosPago.contains(medioPago))
            {
                if(medioPago.getVendedor() == null) {
                    medioPago.setVendedor(vendedorEntity);
                }
            }
            else 
            {
                medioPago.setVendedor(null);
            }
        }
        System.out.println("SALIO");
        vendedorEntity.setMediosPago(mediosPago);
        for(MedioPagoEntity medioPago: mediosPago) {
            medioPagoPersistence.update(medioPago);
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el vendedor con id = {0}", vendedorId);
        return vendedorEntity.getMediosPago();
    }
    
    /**
     * Se elimina un medio de pago asociado a un vendedor
     * @param vendedorId Identificador del Vendedor.
     * @param medioPagoId Identificador del medio de pago.
     * @throws BusinessLogicException Si el medio de pago no existe o no está asociado al vendedor indicado.
     */
    public void eliminarMedioPago (Long vendedorId, Long medioPagoId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el medio de pago con id: {0} del vendedor con id: {1} ", new Object[] {vendedorId, medioPagoId});
        MedioPagoEntity borrar = getMedioPago(vendedorId, medioPagoId);
        if (borrar == null)
            throw new BusinessLogicException("El medio de pago con id = " + medioPagoId + " no esta asociado a el vendedor con id: " + vendedorId);
        
        medioPagoPersistence.delete(borrar.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el medio de pago con id = {0} del vendedor con id: {1}", new Object[] {vendedorId, medioPagoId});
    }
    
    private void verificarReglasDeNegocio(MedioPagoEntity medioPago) throws BusinessLogicException {
        //Número de tarjeta
        if(medioPago.getNumeroTarjeta() == null) {
            throw new BusinessLogicException("El numero de la tarjeta no puede ser null");
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
                    throw new BusinessLogicException("El número de la tarjeta no es VISA.");
                }

                int primerosDigitos = Integer.parseInt(digitos.substring(0, 2));
                if(medioPago.getTipoCredito().equals(MASTERCARD) && primerosDigitos < 51 || 
                        primerosDigitos > 55) {
                    throw new BusinessLogicException("El número de la tarjeta no es MASTERCARD.");
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