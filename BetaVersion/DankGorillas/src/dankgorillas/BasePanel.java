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
 * @author hartnetts
 */
public class BasePanel extends javax.swing.JPanel
{
    final int ENTER = 10;
    int panelWidth = 645;//640
    int panelHeight = 380;//350
    public SystemFrame parentFrame;
    
    void drawText()
    {
    }
    
    public void setup()
    { 
    }
    
    public void addInput(InputRequest ir, int x, int y)
    {
        ir.add(x, y, this);
        ir.focus();
    }
    
    public void removeInput(InputRequest ir)
    {
        ir.remove(this);
    }
}
