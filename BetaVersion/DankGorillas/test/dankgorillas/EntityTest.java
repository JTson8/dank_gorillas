/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankgorillas;

//import java.awt.Graphics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for JUnit testing entity objects.
 */
public class EntityTest 
{
    private final int COORDINATE_A = 500;
    private final int COORDINATE_B = 200;
    
    
    public EntityTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}


    /*
    Test state chanin methods.
    */
    @Test
    public void testStateChange()
    {
        /*
        Sun state change.
        */
        Sun sun = new Sun(COORDINATE_A, COORDINATE_B);
        assertTrue(sun.IsTheSunSmiling());
        
        sun.SetSmile(false);
        assertFalse(sun.IsTheSunSmiling());
        
        
        /*
        Gorilla State change.
        */
        Gorilla monkey = new Gorilla(COORDINATE_A, COORDINATE_B);
        assertFalse(monkey.Dancing());

        monkey.VictoryDance();
        assertTrue(monkey.Dancing());
    }
    
    
    
    /**
     * Test of collideWith method, of class Entity.
     */
    @Test
    public void testCollideWith() 
    {
        /*
        Testing banana collision with buildings.
        */
        Entity banA = new Banana(500, 200);
        
        Entity buildingA = new Building(500, 200, 100, 500);
        Entity buildingB = new Building(500, 205, 100, 500);
        Entity buildingC = new Building(475, 190, 100, 500);
        Entity buildingD = new Building(600, 220, 100, 500);
        
        assertTrue(banA.collideWith(buildingA)); //Test for collision with the edge of a building
        assertTrue(banA.collideWith(buildingB)); //Test for collision with the side of a building
        assertFalse(banA.collideWith(buildingD)); //No collision detected
        assertTrue(banA.collideWith(buildingC)); //Collision occured inside of the buidling's area
        
        
        /*
        Testing banana collision with the sun.
        */
        Entity banB = new Banana(500, 230);
        
        Entity sunA = new Sun(496,226); //inside of the sun
        Entity sunB = new Sun(600, 230); //Out of range in X-direction
        Entity sunC = new Sun(500, 0); //Out of range in y-direction
        Entity sunD = new Sun(500, 230); //On inside target
        Entity sunE = new Sun(500, 230); //On edge collision
        
        assertFalse(banB.collideWith(sunB));
        assertFalse(banB.collideWith(sunC));
        assertTrue(banB.collideWith(sunA));
        assertTrue(banB.collideWith(sunD));
        assertTrue(banB.collideWith(sunE));
        
        
        /*
        Testing banana collision with the Gorilla.
        */        
        Entity banC = new Banana(500, 230);
        
        Entity gorA = new Gorilla(496,226); //inside of the gorilla
        Entity gorB = new Gorilla(600, 230); //Out of range in X-direction
        Entity gorC = new Gorilla(500, 0); //Out of range in y-direction
        Entity gorD = new Gorilla(500, 230); //On inside target
        Entity gorE = new Gorilla(500, 230); //On edge collision
        
        assertFalse(banC.collideWith(gorB));
        assertFalse(banC.collideWith(gorC));
        assertTrue(banC.collideWith(gorA));
        assertTrue(banC.collideWith(gorD));
        assertTrue(banC.collideWith(gorE));
    }
    

    /**
     * Test of draw method, of class Entity.
     */
    /*
    @Test
    public void testDraw() 
    {
        System.out.println("draw");
        Graphics g = null;
        //Entity instance = new Entity();
        //instance.draw(g);
    }*/
    //Possible to remove since the pourpose of the test is more visual.
    
}
