

package ch10ex19clockgui;

import static ch10ex19clockgui.ClockGui.FRAME_HEIGHT;
import static ch10ex19clockgui.ClockGui.FRAME_WIDTH;
import static ch10ex19clockgui.ClockGui.hour;
import static ch10ex19clockgui.ClockGui.minute;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;

/**
 *
 * @author Ryan Heinrich
 */
public class ClockComponent extends JComponent {
    private Ellipse2D.Double circle;
    private Ellipse2D.Double clock;
    private int xValue;
    private int yValue;
    private int radValue = 20;
    
    private double minuteAngle;// = Math.PI/2;
    private double hourAngle;// = Math.PI/2;

    //you have to move the screen to refresh it to show the bars but it works.
    //a fix up would be to reset the month after every time the button is pressed.
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
            circle = new Ellipse2D.Double(xValue-(radValue/2),yValue-(radValue/2),radValue,radValue);        
            g2.draw(circle);
            g2.setColor(Color.MAGENTA);
            g2.fill(circle);
            g2.setColor(Color.BLACK);
            
            int size = 200;
            clock = new Ellipse2D.Double(FRAME_WIDTH/2 -size/2,FRAME_HEIGHT/2 -size/2,size,size); 
            g2.draw(clock);
            
            minuteAngle =Math.PI/2 + (Math.PI*2 *(minute/60.0));
            Line2D.Double minutes = new Line2D.Double(FRAME_WIDTH/2,FRAME_HEIGHT/2,FRAME_WIDTH/2- Math.cos(minuteAngle)*100,FRAME_HEIGHT/2 - Math.sin(minuteAngle)*100);
            g2.setStroke(new BasicStroke(3));
            g2.draw(minutes);
                       
            hourAngle =Math.PI/2 + (Math.PI*2 *(hour/12.0)) + ((Math.PI*2/12) *(minute/60.0) );
            Line2D.Double hours = new Line2D.Double(FRAME_WIDTH/2,FRAME_HEIGHT/2,FRAME_WIDTH/2- Math.cos(hourAngle)*50,FRAME_HEIGHT/2- Math.sin(hourAngle)*50);
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(4));

            g2.draw(hours);
            
    }
    
    public void moveTo(int x, int y){
        xValue = x;
        yValue = y;
        circle = new Ellipse2D.Double(x, y, radValue, radValue);
        repaint();
    }
}
