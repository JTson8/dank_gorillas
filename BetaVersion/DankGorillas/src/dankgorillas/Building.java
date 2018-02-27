package dankgorillas;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Building object that handles the manipulation of a building object. 
 */
public class Building extends Entity
{
   private final int MAX_BUILDING_COLOR_RANGE = 3;
   private final int MIN_BUILDING_COLOR_RANGE = 1;
   private final int MAX_WINDOW_COLOR_RANGE = 2;
   private final int MIN_WINDOW_COLOR_RANGE = 1;
   
	
    Building(int _x, int _y,int width,int height)
    {
        super(_x,_y,width,height);
    }

    /*
    Select a color to make a building.
    <return> A color object.
    */
    private Color GetABuildingColor()
    {
        Color color;
        int random = RandomGenerationNumerator(MAX_BUILDING_COLOR_RANGE, MIN_BUILDING_COLOR_RANGE);
        switch(random)
        {
            case 1:
                color =  Color.RED;
                break;
            case 2:
                color = Color.CYAN;
                break;
            default:
                color = Color.lightGray;
                break;
        }
        return color;		
    }
    
    /*
    Select a color to make a window.
    <return> A color object.
    */
    private Color BuildingLights()
    {
       Color color;
       int random = RandomGenerationNumerator(MAX_WINDOW_COLOR_RANGE, MIN_WINDOW_COLOR_RANGE);
       switch(random)
       {
          case 2:
             color = Color.YELLOW;
             break;
          default:
             color = Color.BLACK;
             break;
       }
       return color;
    }
    
    /*
    Generate a random number between the max and min values.
    <params>
       max - highest value to choos from a range of integers.
       min - lowest value to chose from a range of integers.
    <return> A a randomly selected number between two values.
    */
    private int RandomGenerationNumerator(int max, int min)
    {
       return ((int)(Math.random() * max + min));
    }
    
    /*
    Draw the building graphic using the given graphics destination.
    <params>
       g is the graphics destination.
    */
    @Override
    public void draw(Graphics g)
    {
        int numwindowstall = (height- 15)/15;
        int numwindowsacross = (width - 10)/10;
        g.setColor(GetABuildingColor());
        g.fill3DRect(Math.round(x),Math.round(y),width,height,false);
        int curX,curY;
        curX = 10;
        curY = 15;
        //g.setColor(Color.yellow);
        for (int i= 0; i < numwindowsacross; i++)
        {
            for(int j = 0; j < numwindowstall; j++)
            {
               g.setColor(BuildingLights());
               g.fill3DRect(Math.round(x + curX), Math.round(y + curY), 3, 6, false);
               curY = curY + 15;
            }
            curX = curX + 10;
            curY = 15;
        }
    }
}