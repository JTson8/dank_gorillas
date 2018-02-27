package dankgorillas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * 
 */
public class Banana extends Entity
{
    final double ANGULAR_CHANGE = .7;
    private double rotation = 0;
    private final int banW = 5;
    private final int banH = 8;
    private int ang = 0;
    private Image img;
    private float xVel = 0;
    private float startX = 0;
    private float startY = 0;
    public float yVel = 0;
    private float gravity = 0;
    private float wind = 0;
    private float time = 0;
    private int turn;
    private float prevY = 1000;
    public boolean falling = false;
    
    /*
    Default constructor for a Banana object.
    */
    Banana(int _x, int _y)
    {
        super(_x, _y, 5, 8);
        startX = _x;
        startY= _y;
        try 
        {
            img = ImageIO.read(new File("BananaPic.png"));
            img = img.getScaledInstance(banW, banH, 0);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Banana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    Renders a banana sprite object on a given graphics pane.
    */
    @Override
    public void draw(Graphics g)
    {
        ang = (ang + 1) % 4;
        rotation = rotation + ANGULAR_CHANGE;
        
        Graphics2D gd = (Graphics2D)g;
        gd.rotate(rotation*turn, x+width/2, y+height/2);
        gd.drawImage(img, Math.round(x), Math.round(y), null);
        
    }
    
    public void throwMe(float velocity, float angle, float _gravity, float _wind, int turn)
    {
        System.out.println(angle);
        xVel = (float) ( Math.cos(angle * Math.PI / 180) * velocity );
        yVel = (float) - ( Math.sin(angle * Math.PI / 180) * velocity );
        gravity = _gravity; //this should be moved to the constructor
        System.out.println("gravity: " + gravity);
        wind = _wind;
        this.turn = (turn ==0)?1:-1;
    }
    
    public void physics(int delta)
    {
        time += (float)delta / 300;//delta is in milis
        x = startX + (xVel * time) + (.5f * (wind / 5) * time * time);
        y = startY + (yVel * time) + (.5f * gravity * time * time);
        if(y > prevY)
            falling = true;
        prevY = y;
    }
}
