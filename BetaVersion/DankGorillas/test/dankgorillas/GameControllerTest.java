/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankgorillas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kwasniewskik
 */
public class GameControllerTest {
    
    public GameControllerTest() {
    }
    
    
    
  
 
    

    /**
     * Test of gameSetup method, of class GameController.
     */

    /**
     * Test of nextGame method, of class GameController.
     */
 

    /**
     * Test of drawGame method, of class GameController.
     */


    /**
     * Test of gameTick method, of class GameController.
     */


    /**
     * Test of makeCityscape method, of class GameController.
     */
    @Test
    public void testMakeCityscape() {
        System.out.println("makeCityscape");
        for (int i = 0;i < 20; i++)
        {
            SystemFrame sysframe = new SystemFrame();
            Game_Panel panel = new Game_Panel(sysframe,"hi","hi",3,4);
            GameController instance = new GameController(panel,3,9.8f);
            instance.gameHeight = 640;
            instance.gameWidth = 320;
            instance.background = new BufferedImage(instance.gameWidth, instance.gameHeight, BufferedImage.TYPE_INT_ARGB);
            instance.makeCityscape();
            for(int j = 0; j < instance.collidables.size(); j++)
            {
                if (instance.collidables.get(j) instanceof Building)
                {
                    assertEquals(false,instance.collidables.get(j).height < 61 || instance.collidables.get(j).height > 249);
                    assertEquals(false,instance.collidables.get(j).x > 610);
                }
            }
        }
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
        
        
        
        
        
        
        
        
    }

    /**
     * Test of doExplosion method, of class GameController.
     */

    
}
