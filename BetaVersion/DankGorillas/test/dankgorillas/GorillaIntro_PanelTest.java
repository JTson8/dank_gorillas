/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankgorillas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tollefsonj
 */
public class GorillaIntro_PanelTest 
{
    
    public GorillaIntro_PanelTest() 
    {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
    }
    
    @Before
    public void setUp() 
    {
    }
    
    @After
    public void tearDown() 
    {
    }

    @Test
    public void testsGorillaVictoryDance() 
    {
        //Tests T05
        Gorilla Gor1 = new Gorilla(100, 200);
        Gorilla Gor2 = new Gorilla(250, 200);
      //  GorillaIntro_Panel GorIntro = new GorillaIntro_Panel(Gor1, Gor2);
        Gor1.VictoryDance();
        assertTrue(Gor1.Dancing());
        assertFalse(Gor2.Dancing());
        Gor2.VictoryDance();
        assertTrue(Gor2.Dancing());
     //   GorIntro.setVisible(true);
    }
    
}
