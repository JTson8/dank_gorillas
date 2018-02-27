/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankgorillas;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author tollefsonj
 */
public class Explosion extends Entity
{
    //private final int EXP_BUILD = 18;
    //private final int EXP_BUILDY = 13;
    Color hole;
    
    Explosion (float _x, float _y, int width, int height)
    {
        super ((int)_x, (int)_y, width, height);
    }
    @Override
    public void draw (Graphics g)
    {
        g.setColor(Color.red);
        g.fillOval(Math.round(x) - width/2, Math.round(y) - height/2, width, height);
        if (height > 0)
        {
            width -= 3;
            height -= 3;
        }
    }
    public void dig(Graphics g)
    {
        g.setColor(hole);
        g.fillOval(Math.round(x) - width/2, Math.round(y) - height/2, width, height);
    }
}
