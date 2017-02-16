

package ch13proj2hanoipaint;

import static ch13proj2hanoipaint.HanoiPaint.called;
import static ch13proj2hanoipaint.HanoiPaint.discNumber;
import static ch13proj2hanoipaint.HanoiPaint.moveList;
import static ch13proj2hanoipaint.HanoiPaint.nextMove;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Ryan Heinrich
 * 
 * 
 */
public class TowerComponent extends JComponent{
    public ArrayList<Disk> DiskList = new ArrayList<>();
    
    public TowerComponent(){//lol is drawing the last one first.
        for(int i = 0; i < discNumber;i++){
            Disk disk1 = new Disk(1,i+1);
            DiskList.add(disk1);
            //disk1.drawRing(g2);
        }
    }
    
    public void paintComponent(Graphics g){    
        Graphics2D g2 = (Graphics2D) g;   
        
        //lol this is so overly complicated but if it works it works.
        //okay so it is not moving disk 0.
        if(called == true){
        System.out.println(nextMove);
            for(int i = 0; i < nextMove; i++){
                Move tempMove = moveList.get(i);//get move
                int disk = tempMove.getDisk();//get target disk
                Disk temp = DiskList.get(disk-1);//get variable for target disk
                System.out.println(tempMove.getPosition()+" pos");
                temp.moveDisk(tempMove.getPosition());//change the disks location.
            }
            called = false;
        }
        
        for(int i = discNumber-1; 0 <= i;i--){//this draws the rings in the proper order.
            Disk temp = DiskList.get(i);
            temp.drawRing(g2);
        }
        
        
        //first draw the 3 polls.
        for(int i = 0; i < 3; i++){
            int displace = 150;
            Ellipse2D.Double poll = new Ellipse2D.Double(150+ (i*displace)-(15/2), 200-(15/2), 15, 15);
            g2.setColor(Color.BLACK);
            g2.fill(poll);
        }
        
        
        
        //first Ring.
        //g2.setColor(Color.BLUE);
        //g2.fill(disk1.);
        //g2.setColor(Color.WHITE);
        //g2.fill(blueRing2);
    }
}
