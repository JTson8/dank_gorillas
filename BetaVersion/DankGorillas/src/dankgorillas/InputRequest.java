/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankgorillas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author hartnetts
 */
public class InputRequest 
{
    JLabel text = new JLabel();
    JTextField input = new JTextField();
    Font font;
    boolean compleate = false;
    Graphics g;
    int maxChars;
    Color col= null;
    
    public InputRequest(String prompt, Graphics g, int chars, Color c, int fontSize)
    {
        col = c;
        input.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                keyEvt(evt);
            }
        });
        
        font = new Font (Font.DIALOG, Font.BOLD , fontSize);
        this.g = g;
        g.setFont(font);
        maxChars = chars;
        
        text.setText(prompt);
        text.setForeground(col);
        text.setFont(font);
        
        input.setForeground(col);
        input.setBackground(null);
        input.setBorder(null);
        input.setFont(font); 
    }
    
    public void useAsLabel()
    {
        lockValue();
        input.setVisible(false);
    }
    
    public InputRequest(String prompt, Graphics g, int chars, Color c)
    {
           this(prompt,g,chars,c,11);
    }
    
    public void add(int x, int y, JPanel pan)
    {
        pan.add(text);
        pan.add(input);
        size(x, y);
    }
    
    public void size(int x, int y)
    {
        int promptWidth = g.getFontMetrics().stringWidth(text.getText());
        int promptHeight = g.getFontMetrics().getHeight();
        text.setBounds(x ,y , promptWidth, promptHeight);
        input.setBounds(x + promptWidth, y, 100, promptHeight);
    }
    
    public void remove(JPanel pan)
    {
        pan.remove(text);
        pan.remove(input);
    }
    
    public void lockValue()
    {
        input.setEditable(false);
        input.getCaret().setVisible(false);
        //input.removeKeyListener(input.getKeyListeners()[0]);
    }
    
    public void resetValue()
    {
        input.setEditable(true);
        input.getCaret().setVisible(true);
        input.setText("");
    }
    
    public void focus()
    {
        input.requestFocusInWindow();
    }
    
        
    private void keyEvt(java.awt.event.KeyEvent evt) 
    {            
        if(input.getText().length() >= maxChars && 
                evt.getKeyCode() != evt.VK_BACK_SPACE)
        {
            evt.consume();
        }
    } 
}