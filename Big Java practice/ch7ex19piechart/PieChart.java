

package ch7ex19piechart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * @author Ryan Heinrich
 */
public class PieChart {
    public static ArrayList<Double> values = new ArrayList<Double>();
    static double sum = 0;
    
    public PieChart(){
    }
    
    //okay they are all drawing based on the the starting point and not off of each other.
    public void draw(Graphics2D g2){
        //starting line.
        double fractionSum = 0;
        double pi = Math.PI;
        Line2D line1 = new Line2D.Double(250, 250, 250+(150*Math.cos(2*pi)), 250+( 150*Math.sin(2*pi)));
        g2.draw(line1);
        
        for(int i = 0; i < values.size();i++){
             Double a = values.get(i);
             
             double fraction = (a / sum) * (2 * pi);//finds percent then converts to radians.
             fractionSum += fraction;//they have to add off of each other to not overlap.
                Line2D line = new Line2D.Double(250, 250, 250+(150*Math.cos(fractionSum)), 250+( 150*Math.sin(fractionSum)));
                g2.draw(line);
                //System.out.println("looping.");
         }
  }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Draw Some Shapes.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Values for a pie chart. Enter a negative number when you are done.");
        int i = 0;
        Double temp = 1.0;
        while(temp > 0){
            System.out.print(i+" " );
             temp = in.nextDouble();
             if(temp < 0){
                 break;//break loop.
             }
             sum += temp;
             values.add(temp);
            i++;
        }
        
        PieChartComponent component = new PieChartComponent();
        frame.add(component);
        
        frame.setVisible(true);
    }
    
}
