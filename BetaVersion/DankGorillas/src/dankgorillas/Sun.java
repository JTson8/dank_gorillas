package dankgorillas;

import java.awt.Graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Sun object that handles the manipulation of a sun object. 
 */
public class Sun extends Entity
{
    private final int SUN_SPRITE_SIZE = 48;
    private boolean sunSmiling;
    private Image img[];
    
    /**
     * Default constructor for the Sun object.
     * @param init_x is the initial x-coordinate.
     * @param init_y is the initial y-coordinate.
     */
    Sun(int init_x, int init_y)
    {
        super( init_x - 24, init_y, 64, 64 );
        img = new Image[2];
        sunSmiling = true;
        SetArray();
    }
    
    /**
     * Populates an array of images to animate the sun.
     */
    private void SetArray()
    {
        try
        {
            img[0] = ImageIO.read(new File("SunPic.png"));
            img[1] = ImageIO.read(new File("SunHitPic.png"));
            img[0] = img[0].getScaledInstance(SUN_SPRITE_SIZE, SUN_SPRITE_SIZE, 0);
            img[1] = img[1].getScaledInstance(SUN_SPRITE_SIZE, SUN_SPRITE_SIZE, 0);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Sun.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Sun image failed to load. " + ex);
        }
        
    }
    
    /**
     * Draw the sun graphic using the given graphics destination.
     * @param g graphics of the calling panel.
     */
    @Override
    public void draw(Graphics g)
    {
        try
        {
            if(sunSmiling) 
               g.drawImage(img[0], Math.round(x), Math.round(y), null);
            else
               g.drawImage(img[1], Math.round(x), Math.round(y), null);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    
    /**
     * Set the sun smiling state.
     * @param smil the desired sun state.
     */
    public void SetSmile(boolean smil)
    {
        sunSmiling = smil;
    }
    
    /**
     * Get the state of the sun smiling.
     * @return True if the sun state is set to smiling, false otherwise.
     */
    public boolean IsTheSunSmiling()
    {
        return sunSmiling;
    }
}