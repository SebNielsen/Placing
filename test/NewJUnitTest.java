/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import battleship.examples.RandomHunter;
import battleship.interfaces.Position;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tobias
 */
public class NewJUnitTest {
    
    public NewJUnitTest() {
        
        
        
    }
    
     @Test
     public void testLoadPos() {
         RandomHunter hunter = new RandomHunter();
         int posX = hunter.getPosList().get(3).x;
         int posY = hunter.getPosList().get(3).y;
         assertEquals("Testing for position y is  ", 0, posY);
         assertEquals("Testing for position x is  ", 3, posX);
     }
}
