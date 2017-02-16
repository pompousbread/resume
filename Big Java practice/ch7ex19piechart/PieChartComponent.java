/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch7ex19piechart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

/**
 * @author Ryan Heinrich
 */
public class PieChartComponent extends JComponent{
    public void paintComponent(Graphics g){
    Graphics2D g2 = (Graphics2D) g;
        //the cirlce stays the same. the lines within it move.
        Ellipse2D.Double chart = new Ellipse2D.Double(250-300/2, 250-300/2, 300 , 300);
        g2.draw(chart);
        
        PieChart circleChart = new PieChart();
        circleChart.draw(g2);
    }
    
    
}
