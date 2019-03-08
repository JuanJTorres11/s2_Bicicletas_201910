/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicicletas.podam;

import java.security.SecureRandom;
import java.util.Random;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 *
 * @author Juan José Torres <jj.torresr@uniandes.edu.co>
 */
public class TelefonoStrategy implements AttributeStrategy<Long>
{

    private Random r = new Random();
    
    @Override
    public Long getValue()
    {
        long max = 4000000000l;
        long min = 3000000000l;
        return (r.nextLong() % (max-min)) + min;
    }
}
