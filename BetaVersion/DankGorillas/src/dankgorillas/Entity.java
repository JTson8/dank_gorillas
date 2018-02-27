/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankgorillas;

import java.awt.Graphics;


/**
 *
 * @author hartnetts
 */
public class Entity 
{
    float x;
    float y;
    int width;
    int height;
    
    Entity( float init_x, float init_y, int w, int h)
    {
        x = init_x;
        y = init_y;
        width = w;
        height = h;
    }
    
    boolean collideWith(Entity e)
    {
        if (  e == null )
         return false;

      return ( x + width ) >= e.x && ( e.x + e.width ) >= x &&
             ( y + height ) >= e.y && ( e.y + e.height ) >= y;
    }
    
    void SetState() {}
    
    public void draw(Graphics g)
    {
       System.err.println("draw SHOULD BE OVERRIdDEN INTHE CHILD CLASS");
    }
    
    public void act() {}
}
