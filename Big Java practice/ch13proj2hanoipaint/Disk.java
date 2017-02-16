

package ch13proj2hanoipaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 *
 * @author Ryan Heinrich
 * 
 */
public class Disk {
    private int size;
    private int position;//position can only be 1, 2,or 3
    
    private int xLeft;
    private int yTop;
    private int diskColor;
    
    public Disk(int aPosition,int aSize){
        position = aPosition;
        size = aSize;
        diskColor = (int)Math.floor((Math.random() * 4) + 1);
        
    }
    //I have to find a way to randomly draw colors
    public void drawRing(Graphics2D g2){
        int displace = 150;
            xLeft = 150 +(displace * (position -1)) - (size*30/2);
            yTop = 200 - (size*30/2);
        
        Ellipse2D.Double ring = new Ellipse2D.Double(xLeft, yTop, size*30, size*30);
        //int newColor = (int)Math.floor((Math.random() * 4) + 1);
        //System.out.println(newColor);
        if(diskColor  == 1){
            g2.setColor(Color.BLUE);
        }else if(diskColor  == 2){
            g2.setColor(Color.RED);
        }else if(diskColor  == 3){
            g2.setColor(Color.GREEN);
        }  
        else  if(diskColor  == 4){
            g2.setColor(Color.MAGENTA);
        }
        //first Ring.
        g2.fill(ring);
        g2.setColor(Color.BLACK);
        g2.draw(ring);
    }
   
    public void moveDisk( int newPos){
        position = newPos;
    }
    
    public int getSize(){
        return size;
    }
    
    public int getLocation(){
        return position;
    }
}
