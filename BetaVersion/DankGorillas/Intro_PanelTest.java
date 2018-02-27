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
public class Intro_PanelTest {
    
    public Intro_PanelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testsGetInputs() {
        // TODO review the generated test code and remove the default call to fail.
        Intro_Panel intro1 = new Intro_Panel("Jonah", "Steven", 5, 15.67);
        Intro_Panel intro2 = new Intro_Panel(null, null, null, null);
        Intro_Panel intro3 = new Intro_Panel(null, null, 100, null);
        
        //Tests T02
        String P1Name = intro1.getP1Name();
        String P2Name = intro1.getP2Name();
        int numGames = intro1.getNumGames();
        float gravity = intro1.getGravity();
        assertEquals("Jonah", P1Name);
        assertEquals("Steven". P2Name);
        assertEquals(5, numGames);
        assertEquals(15.67, gravity);
        fail("Test T02 failed.");
        
        //Tests T03
        String P1Name = intro1.getP1Name();
        String P2Name = intro1.getP2Name();
        int numGames = intro1.getNumGames();
        float gravity = intro1.getGravity();
        assertEquals("Player 1", P1Name);
        assertEquals("Player 2". P2Name);
        assertEquals(3, numGames);
        assertEquals(9.81, gravity);
        fail("Test T03 failed.");
        
        //Tests T04
        int numGames = intro1.getNumGames();
        assertEquals(3, numGames);
        fail("Test T04 failed.");
    }
    
    public void TestsExit() 
    {
        //Tests T06
        Intro_Panel intro1 = new Intro_Panel(null, null, null, null);
        boolean exited = intro.Exit('V');
        
        //Tests T07
        Intro_Panel intro2 = new Intro_Panel(null, null, null, null);
        boolean exited = intro.Exit('P');
    }
}
