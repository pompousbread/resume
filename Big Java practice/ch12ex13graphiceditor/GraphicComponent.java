
package ch12ex13graphiceditor;


import static ch12ex13graphiceditor.SideTab.blue;
import static ch12ex13graphiceditor.SideTab.circleOn;
import static ch12ex13graphiceditor.SideTab.green;
import static ch12ex13graphiceditor.SideTab.image;
import static ch12ex13graphiceditor.SideTab.lineOn;
import static ch12ex13graphiceditor.SideTab.rectOn;
import static ch12ex13graphiceditor.SideTab.red;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author Ryan Heinrich
 */
public class GraphicComponent extends JComponent {
    private Ellipse2D.Double circle;
    private Line2D.Double line;
    private Rectangle rect;
    
    private ArrayList<Rectangle> rectList;
    private ArrayList<Ellipse2D.Double> circleList;
    private final ArrayList<Line2D.Double> lineList;
    
    public static Graphics2D g2;

    
    private int xValue;
    private int yValue;
    private int radValue = 20;
    private boolean point1On = false;//tells which point of the line to place.
    
    //private Point2D.Double point1;//temp point used to start Line2D
    private int xOld;
    private int yOld;
    
    public GraphicComponent(){
        rectList = new ArrayList<>();
        circleList = new ArrayList<>();
        lineList = new ArrayList<>();
    }
    
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    public void paintComponent(Graphics g){
        g2 = (Graphics2D) g;

        
        g2.setColor(Color.MAGENTA);
        
            if(red == true){
                g2.setColor(Color.RED);
            }else if(blue == true){
                g2.setColor(Color.BLUE);
            }else if(green == true){
                g2.setColor(Color.GREEN);
            }  
       if(circleOn == true){
            circle = new Ellipse2D.Double(xValue-(radValue/2),yValue-(radValue/2),radValue,radValue);        
            g2.fill(circle);
            g2.setColor(Color.BLACK);
            circleList.add(circle);
            
        }else if(rectOn == true){
            rect = new Rectangle(xValue-(radValue/2),yValue-(radValue/2),radValue,radValue);        
            g2.fill(rect);
            g2.setColor(Color.BLACK);
           
            rectList.add(rect);          
        
        }else if(lineOn == true){
            //Point2D.Double point1 = null;
            if(point1On == false){
                xOld = xValue;
                yOld = yValue;
                point1On = true;
            }else{
                Point2D.Double point1 = new Point2D.Double(xOld, yOld);
                Point2D.Double point2 = new Point2D.Double(xValue, yValue);
                line = new Line2D.Double(point1, point2);
                int randStroke = randInt(1,12);
                g2.setStroke(new BasicStroke(randStroke));
                lineList.add(line);
                
                point1On = false;
            }
        
        }
       
       //g2.drawImage(testerImage, 0, 0, null);
       //keeps drawing all the shapes.
       for (Ellipse2D.Double tempCircle : circleList) {
                g2.draw(tempCircle);
            }
        for (Rectangle tempRect : rectList) {
            g2.draw(tempRect);
        }
        for (Line2D.Double tempLine : lineList) {
            g2.draw(tempLine);
        }
        if(image != null){
            g2.drawImage(image, 0, 0, null);
        }

        
    }
    
    public static BufferedImage componentToImage(JComponent component, Rectangle region) throws IOException
{
    BufferedImage img = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
    Graphics g = img.getGraphics();
    g.setColor(component.getForeground());
    g.setFont(component.getFont());
    component.paintAll(g);
    if (region == null)
    {
        region = new Rectangle(0, 0, img.getWidth(), img.getHeight());
    }
    return img.getSubimage(region.x, region.y, region.width, region.height);
}
    public void moveTo(int x, int y){
        radValue = randInt(50, 100);
        xValue = x;
        yValue = y;
        repaint();
    }
}
