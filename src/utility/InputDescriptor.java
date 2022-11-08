/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 *
 * @author Duy
 */
public abstract class InputDescriptor {
    
    @Override
    public abstract java.lang.String toString();
    
    
    /**
     * generator char
     */
    public static class Char extends InputDescriptor {
        char character ;
        /**
         *
         * @param min
         * @param max
         * @return
         */
         public  Char(int min, int max) {
           character = (char)( ThreadLocalRandom.current().nextInt(min,max));
         }
        
        @Override
        public java.lang.String toString() {
            return java.lang.String.valueOf(character+"\t");
        }
    }
    
    public static class Integer extends InputDescriptor {
        long input = 0;
        public Integer(long min, long max) {
            input = ThreadLocalRandom.current().nextLong(min,max);;
        }
        
        @Override
        public java.lang.String toString() {
            java.lang.String output = java.lang.String.valueOf(input);
            return output+"\t";
        }
        
    }
    /**
     * generator number
     */
    public static class Number extends InputDescriptor {
        double input;
        DecimalFormat df ;
        public Number(double min, double max, int decimals) {
             input = ThreadLocalRandom.current().nextDouble(min, max);
             java.lang.String decimal = "##.";
             for (int i = 0; i < decimals; i++) {
                decimal+="#";
            }
                df = new DecimalFormat(decimal);
        }
        
        @Override
        public java.lang.String toString() {
            return java.lang.String.valueOf(df.format(input)+"\t");
        }
    }
    /**
     * generator string 
     */
    public static class String extends InputDescriptor {
        java.lang.String input;
        int leng;
        int typeinput;
        public String(int minLength, int maxLength, int type) {
           leng = ThreadLocalRandom.current().nextInt(minLength,maxLength);
           typeinput = type;
        }
        
        @Override
        public java.lang.String toString() {
            if (typeinput==1) {
                input = randomNumeric(leng);
            }if(typeinput==2){
                input = randomAlphabetic(leng);
            }
             else{
                input = randomAlphanumeric(leng);
            }
            return "\n"+input+"\n";
        }
    }
    /**
     * generator Matrix status bug
     */
    public static class Matrix extends InputDescriptor {
        ArrayList<InputDescriptor> array = new ArrayList<>();
        int row;
        public Matrix(int rows, int columns, InputDescriptor type) {
            
            for (int i = 0; i < rows*columns+1; i++) {
                array.add(type);
            }
            row = rows;
        }
        
        @Override
        public java.lang.String toString() {
            java.lang.String input = "";
            for (int i = 1; i < array.size(); i++) {
                if(i%row==0){
                   input+= array.get(i)+"\n";
                }else{
                   input+= array.get(i)+"\t";
                }
                
            }
            return input;
        }
    }
}
