/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankgorillas;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Graphics;

/**
 *
 * @author hartnetts
 */
public class MainMenu extends BasePanel
{
    final int ENTER = 10;
    final int INTRO = 86;//whatever the code is for V
    final int PLAY = 80;//whatever the ode is for P
    
    //Input Defaults
    final String dP1 = "Player 1";
    final String dP2 = "Player 2";
    final int dgames = 3;
    final float dgravity = 9.8f;
    
    InputRequest p1;
    InputRequest p2;
    InputRequest game;
    InputRequest grav;
    InputRequest prompt;
    
    String p1Name = "";
    String p2Name = "";
    int gamesToWin = 0;
    float gravity = 9.8f;
    
    private enum InputState 
    {
        PLAYER1, PLAYER2, GAMES, GRAVITY, READY
    }
    InputState state;
    
    public MainMenu(SystemFrame parent)
    {
        parentFrame = parent;
    }
    
    @Override
    public void setup()
    {
        setLayout(null);
        p1 = new InputRequest("Name of Player 1 (Default = 'Player 1'): ",
            getGraphics(), 10, Color.LIGHT_GRAY, 15);
        p2 = new InputRequest("Name of Player 2 (Default = 'Player 2'): ",
            getGraphics(), 10, Color.LIGHT_GRAY, 15);
        game = new InputRequest("Play to how many total points (Default = 3)? ",
            getGraphics(), 2, Color.LIGHT_GRAY, 15);
        grav = new InputRequest("Gravity in Meters/Sec (Earth = 9.8)? ",
            getGraphics(), 5, Color.LIGHT_GRAY, 15);
        prompt = new InputRequest("Press P to play or V to view the intro.",
            getGraphics(), 10, Color.LIGHT_GRAY, 15);
        addListeners();
        addInput(p1, 130, 100);
        state = InputState.PLAYER1;
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        
        
    }
    
    public void addListeners()
    {
        p1.input.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) 
            {
                if(evt.getKeyCode() == ENTER)
                {
                    p1Name = p1.input.getText();
                    if(p1Name.equals(""))
                        p1Name = dP1;
                    addInput(p2, 130, 125);
                    p1.lockValue();
                }
            }
        });
        
        p2.input.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) 
            {
                if(evt.getKeyCode() == ENTER)
                {
                    p2Name = p2.input.getText();
                    if(p2Name.equals(""))
                        p2Name = dP2;
                    addInput(game, 130, 150);
                    p2.lockValue();
                }
            }
        });
        
        game.input.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) 
            {
                if(evt.getKeyCode() == ENTER)
                {
                    if(GameValid())
                    {
                        game.lockValue();
                        addInput(grav, 130, 175);
                    }
                }
            }
        });
        
        grav.input.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) 
            {
                if(evt.getKeyCode() == ENTER)
                {
                    if(GravityValid())
                    {
                        grav.lockValue();
                        addInput(prompt, 180, 250);
                        prompt.lockValue();
                    }
                }
            }
        });
        
        prompt.input.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) 
            {
                if(evt.getKeyCode() == PLAY)
                {
                    System.out.println("giving  "+ gravity);
                    Game_Panel gamePanel = new Game_Panel(parentFrame, p1Name, p2Name, gamesToWin, gravity);
                    parentFrame.switchPanel(gamePanel);
                }
                if(evt.getKeyCode() == INTRO)
                {
                    Intro_Panel introPanel = new Intro_Panel(parentFrame, p1Name, p2Name, gamesToWin, gravity);
                    parentFrame.switchPanel(introPanel);
                }
            }
        });
    }
    
    private boolean GameValid()
    {
        String gameInput = game.input.getText();
        if(gameInput.equals("") || gameInput.equals("0"))
        {
            gamesToWin = dgames;
            return true;
        }
        else if (!gameInput.matches("[0-9]+"))
        {
            game.input.setText("");
            return false;
        }
        else
        {
            gamesToWin = Integer.parseInt(gameInput);
            return true;
        }
    }
    
    private boolean GravityValid()
    {
        String gravInput = grav.input.getText();
        if(gravInput.equals("") || gravInput.equals("0"))
        {
            gravity = dgravity;
            return true;
        }
        if (!gravInput.matches("[0-9.]+"))
        {
            grav.input.setText("");
            return false;
        }
        if( gravInput.length() - 1 > gravInput.replace(".", "").length())
        {
                return false;
        }
        gravity = Float.parseFloat(gravInput);
        return true;
    }
}
