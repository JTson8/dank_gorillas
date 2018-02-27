/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankgorillas;

import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hartnetts
 */
public class GameController 
{
    final int ENTER = 10;
    final int scoreX = 268;
    final int scoreY = 310;
    final int DANCE_TIME = 90;
    int PLAYER1_TURN = 0;
    int PLAYER2_TURN = 1;
    int ARROWOFFSET = 40;
    int turn = 0;
    int gameWidth, gameHeight = 0;
    float gravity = 9.8f;
    long frameDelta = 1;
    int gamesToWin = 3;
    float wind = 0f;
    int gor1Index, gor2Index;
    int player1Score = 0;
    int player2Score = 0;
    int danceTimer = 0;
    
    InputRequest angleInput;
    float angle;
    InputRequest velocityInput;
    float velocity;
    InputRequest name1;
    InputRequest name2;
    InputRequest score;

    
    Game_Panel game;
    BufferedImage background;
    BufferedImage foreground;
    ArrayList<Entity> foregroundEntities = new ArrayList();
    ArrayList<Entity> collidables = new ArrayList();
    
    Banana banana = null;
    Explosion explosion = null;
    Sun sun = null;
    boolean bananaInFlight = false;
    int offScreenTolerance = 50;
    
    Arrow arr = null;

    GameController(Game_Panel g, int toWin, float grav) 
    {
        turn = 0;
        game = g;
        gamesToWin = toWin;
        gravity = grav;
        
    }
    
    
    
    public void gameSetup(int width, int height, int fps, String n1, String n2)
    {
        //frameDelta = 1.0f / (float)fps;
        gameWidth = width;
        gameHeight = height;
        foreground = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g =background.getGraphics();
        g.setColor(game.getBackground());
        g.fillRect(0, 0, 640, 350);
        makeCityscape();
        generateWind();
        angleInput = new InputRequest("Angle: ",
            game.getGraphics(), 4, Color.WHITE, 17);
        velocityInput = new InputRequest("Velocity: ",
            game.getGraphics(), 4, Color.WHITE, 17);
        name1 = new InputRequest(n1,
            game.getGraphics(), 4, Color.WHITE, 17);
        name1.useAsLabel();
        game.addInput(name1, 10, 2);
        name2 = new InputRequest(n2,
            game.getGraphics(), 4, Color.WHITE, 17);
        name2.useAsLabel();
        game.addInput(name2, 530, 2);
        score = new InputRequest("0>Score<0",
            game.getGraphics(), 4, Color.WHITE, 17);
        score.useAsLabel();
        score.text.setBackground(game.getBackground());
        score.text.setOpaque(true);
        game.addInput(score, scoreX, scoreY);
        angleInput.input.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) 
            {
                if(evt.getKeyCode() == ENTER)
                {
                    //record data
                    if(validInput(angleInput.input.getText()))
                    {
                        angleInput.lockValue();
                        game.addInput(velocityInput, 520 * turn + 10, 35);//x is a function of turn
                    }
                    else
                        angleInput.input.setText("");
                }
            }
        });
        
        velocityInput.input.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) 
            {
                if(evt.getKeyCode() == ENTER)
                {
                    if(validInput(velocityInput.input.getText()))
                    {
                        velocityInput.lockValue();
                        if(turn == 0)
                            angle =  Float.parseFloat(angleInput.input.getText());//do error detection here
                        if(turn == 1)
                            angle =  180 - Float.parseFloat(angleInput.input.getText());//do error detection here
                        velocity = Float.parseFloat(velocityInput.input.getText());//probably a method to test float
                        throwBanana(velocity, angle);
                        endTurn();
                    }
                    else
                        velocityInput.input.setText("");
                }
            }
        });
        turn();
    }
    
    private void turn()
    {
        angleInput.resetValue();
        velocityInput.resetValue();
        game.addInput(angleInput, 520 * turn + 10, 20);//x is a function of turn
        
    }
    
    private void endTurn()
    {
        game.removeInput(angleInput);
        game.removeInput(velocityInput);
        //unlock and clear input and 
    }
    
    private void changeTurn()
    {
        turn = (turn + 1) % 2;
    }
    
    private void throwBanana(float vel, float ang)//0 - left turn, 1 - right turn
    {
        int startX;
        int startY;
        if (turn == 0)
        {
            startX = Math.round(collidables.get(gor1Index).x - 5);
            startY = Math.round(collidables.get(gor1Index).y - 5);
        }
        else
        {
            startX = Math.round(collidables.get(gor2Index).x + 32);
            startY = Math.round(collidables.get(gor2Index).y - 5);
        }
        banana = new Banana(startX, startY);
        foregroundEntities.add(banana);
        banana.throwMe(vel, ang, gravity, wind, turn);
    }
    
    
    
    private void bananaCollision(Entity e, float bananaX, float bananaY)
    {
        
        if(e instanceof Sun)
            sun.SetSmile(false);
        else
        {
            changeTurn();
            foregroundEntities.remove(banana);
            banana = null;
            sun.SetSmile(true);
            if(e instanceof Gorilla)
            {
                doExplosion(((Gorilla) e).x + 16, ((Gorilla) e).y + 16, true);
                foregroundEntities.remove(e);
                int dead = collidables.indexOf(e);
                if(dead == gor1Index)
                {
                    player2Score++;
                    ((Gorilla)collidables.get(gor2Index)).VictoryDance();
                }
                else
                {
                    player1Score++;
                    ((Gorilla)collidables.get(gor1Index)).VictoryDance();
                }
                danceTimer = 1;
            }
            else
            {
                doExplosion(bananaX, bananaY, false);
                turn();
            }
        }
    }
    
    public void nextGame()
    {
        score.text.setText( player1Score + ">Score<" + player2Score);
        score.size(scoreX, scoreY);
        collidables.clear();
        foregroundEntities.clear();
        Graphics g = background.getGraphics();
        g.setColor(game.getBackground());
        g.fillRect(0, 0, gameWidth, gameHeight);
        makeCityscape();
        generateWind();
        //turn = 0;//need this
        
        turn();
    }
    
    private void generateWind()
    {
        wind = (float)Math.random()*30 - 15;
        Arrow arrow = new Arrow(320,345,wind);
        arrow.draw(background.getGraphics());
    }
    
    public void drawGame(Graphics g)
    {
        BufferedImage temp = new BufferedImage(gameWidth, gameHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D fg = (Graphics2D)foreground.getGraphics();
        fg.setBackground(new Color(255, 255, 255, 0));
        fg.clearRect(0,0, gameWidth, gameHeight);
        for(Entity e: foregroundEntities)
        {
            e.draw(foreground.getGraphics());
        }
        Graphics tg = temp.getGraphics();
        tg.drawImage(background, 0, 0, null);//null or game?
        tg.drawImage(foreground,0 ,0, null);
        g.drawImage(temp, 0, 0, null);
        //draw both to game_Panel
    }
    
    public void gameTick()
    {
        if(banana != null)
        {
            banana.physics((int)(System.currentTimeMillis() - frameDelta));
            if(banana.x > gameWidth + offScreenTolerance || 
                    banana.x < -offScreenTolerance)
                bananaCollision(null, banana.x, banana.y);
            else
                for(int i = 0; i < collidables.size(); i++)
                {
                    if(!((turn == 0 && i == gor1Index) || (turn == 1 && i == gor2Index)) || (banana.falling))
                    {
                        if (banana.collideWith(collidables.get(i)))
                        {
                            bananaCollision(collidables.get(i), banana.x, banana.y);
                            break;
                        }
                    }
                }
            
        }
        if(danceTimer > 0)
        {
           danceTimer++;
        }
        if(danceTimer >= DANCE_TIME)
        {
           danceTimer = 0;
           if( player1Score + player2Score >= gamesToWin)
           {
              GameOver_Panel gop = new GameOver_Panel(game.parentFrame, 
                      name1.text.getText(), name2.text.getText(), player1Score, player2Score);
              game.gameTimer.stop();
              game.parentFrame.switchPanel(gop);
           }
           else
              nextGame();
        }
        frameDelta = System.currentTimeMillis();
    }
    
    public void makeCityscape()
    {      
        int curX = 0;
	Random rand = new Random();  
	int buildheight = (rand.nextInt(8) + 4)*15 + 15;
	int buildwidth = (rand.nextInt(6) + 4)*10 + 10;
        Building newBuild;
        while(curX < gameWidth)
	{
            if (gameWidth - curX < 101)
                buildwidth = gameWidth - curX;
            newBuild = new Building(curX,(gameHeight - buildheight) - ARROWOFFSET, buildwidth, buildheight);
            newBuild.draw(background.getGraphics());
            collidables.add(newBuild);    // arrowoffset is 40
            curX = curX + buildwidth;
            int slope = rand.nextInt(50); //640
            slope = slope * (rand.nextBoolean() ? -1 : 1);
            if (buildheight < 120)
                slope = Math.abs(slope);
            if (buildheight > 190)
                slope = Math.abs(slope) * -1;
            buildheight = buildheight + slope;
            buildwidth = (rand.nextInt(6) + 4)*10 + 10;
	}
        int build1Index = (rand.nextInt(2) + 1);
        int build2Index = (collidables.size() - rand.nextInt(2) - 2);
        PlaceGorillas(build1Index, build2Index);
    }
    
    
    private void PlaceGorillas(int build1Index, int build2Index)
    {
        int gor1_X = Math.round((collidables.get(build1Index)).x - 16);
        int gor1_Y = Math.round((collidables.get(build1Index)).y - 32);
        int gor2_X = Math.round((collidables.get(build2Index)).x - 16);
        int gor2_Y = Math.round((collidables.get(build2Index)).y - 32);
        int build1Width = (collidables.get(build1Index)).width;
        int build2Width = (collidables.get(build2Index)).width;
        Gorilla G1 = new Gorilla(gor1_X + (build1Width/2), gor1_Y);
        Gorilla G2 = new Gorilla(gor2_X + (build2Width/2), gor2_Y);
        foregroundEntities.add(G1);
        collidables.add(G1);
        gor1Index = collidables.size() - 1;
        foregroundEntities.add(G2);
        collidables.add(G2);
        gor2Index = collidables.size() - 1;
        sun = new Sun(gameWidth/2, 10);
        foregroundEntities.add(sun);
        collidables.add(sun);   
        
     //   arr = new Arrow(gameWidth/2,gameHeight-35,1);
      //  foregroundEntities.add(arr);
    }
    
    public void doExplosion(float xcoor, float ycoor, boolean gorHit)
    {
        if (gorHit)
        {
            explosion = new Explosion(xcoor,ycoor,75,52);
        }
        else
        {
            explosion = new Explosion(xcoor,ycoor,25,25);
        }
        explosion.hole = game.getBackground();
        explosion.dig(background.getGraphics());
        foregroundEntities.add(explosion);
    }
    
    private boolean validInput(String input)
    {
        if (!input.matches("[0-9.]+"))
        {
            return false;
        }
        if( input.length() - 1 > input.replace(".", "").length())
        {
                return false;
        }
        return true;
    }
}
