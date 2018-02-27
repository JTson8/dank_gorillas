package dankgorillas;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Bear
 */
public class Arrow extends Entity
{
   /**
    * This block is for constant declaration.
    */
   private final double ARROW_ANGLE = 60;
   private final double VER_OFFSET = 2;
   
   /**
    * This block is for global variable declaration.
    */
   private int shaft;
   
   /**
    * Default constructor for the arrow class. 
    * @param init_x is the initial x position to start drawing an arrow.
    * @param init_y is the initial y position to start drawing an arrow.
     * @param windStr is the wind strength.
    */
   public Arrow(int init_x, int init_y, float windStr)
   {
      super(init_x, init_y, 0, 0);
      shaft = (int) windStr;
   }
   
   
   /**
    * Draw method that renders an arrow.
    * @param g is the graphics destination.
    */
   @Override
   public void draw(Graphics g)
   {
      g.setColor(Color.RED);
      int init_X = Math.round(x);
      int init_Y = Math.round(y);
      if(shaft < 0)
      {
         int x2 = init_X + shaft* 4;
         int y2 = init_Y;
         int x3 = (int) (x2 + VER_OFFSET/Math.tan(ARROW_ANGLE));
         int y3 = (int) (y - VER_OFFSET);
         int x4 = (int) (x2 + VER_OFFSET/Math.tan(ARROW_ANGLE));
         int y4 = (int) (y + VER_OFFSET);
         g.drawLine(init_X, init_Y, x2, y2);
         g.drawLine(x2, y2, x3, y3);
         g.drawLine(x2, y2, x4, y4);
      }
      else
      {
         int x2 = init_X + shaft*4;
         int y2 = init_Y;
         int x3 = (int) (x2 - VER_OFFSET/Math.tan(ARROW_ANGLE));
         int y3 = (int) (y - VER_OFFSET);
         int x4 = (int) (x2 - VER_OFFSET/Math.tan(ARROW_ANGLE));
         int y4 = (int) (y + VER_OFFSET);
         g.drawLine(init_X, init_Y, x2, y2);
         g.drawLine(x2, y2, x3, y3);
         g.drawLine(x2, y2, x4, y4);
      }        
   }
}