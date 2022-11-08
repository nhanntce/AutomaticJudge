/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;

/**
 *
 * @author DuyNH
 */
public class InputGenerator {
    
    // constructor
    public InputGenerator() {
        // TODO
    }
    
    // generate 
    public String Generate(ArrayList<InputDescriptor> descriptors) {
        String input = "";
        for (int i = 0; i < descriptors.size(); i++) {
            input+= descriptors.get(i).toString();
            
        }
        return input;
    }
}
