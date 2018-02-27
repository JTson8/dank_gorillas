package dankgorillas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;
/**
 *
 * @author hartnetts
 */
public class Gorilla extends Entity
{
    private Image [] img = new Image[3];
    private final int gorWH = 32;
    private boolean dance = false;
    private int img_Index = 1;
    private int danceTime = 0;
    
    Gorilla(int _x, int _y)
    {
        super(_x, _y, 32, 32);
        try 
        {
            img [0] = ImageIO.read(new File("GorillaPic.png"));
            img [1] = ImageIO.read(new File("GorillaLeftArm.png"));
            img [2] = ImageIO.read(new File("GorillaRightArm.png"));
            for(int i = 0; i < 3; i++)
            {
                Image temp = img[i].getScaledInstance(gorWH, gorWH, 0);
                img[i] = temp;
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    @Override
    public void draw(Graphics g)
    {
        try
        {
            if(dance)
            {
                danceTime++;
                if(danceTime > 5)
                {
                    img_Index = (img_Index == 1)? 2:1;
                    danceTime = 0;
                }
                g.drawImage(img[img_Index], Math.round(x), Math.round(y), null);
            }
            else
            {
                img_Index = 0;
                g.drawImage(img[img_Index], Math.round(x), Math.round(y), null);
            }
        }
        catch (Exception e)
        {
           System.err.println(e);
        }

    }

    void VictoryDance() 
    {
        dance = !dance;
        img_Index = 1;
    }
    
    boolean Dancing() 
    {
        return dance;
    } 
}
