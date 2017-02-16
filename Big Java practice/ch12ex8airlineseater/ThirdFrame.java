/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch12ex8airlineseater;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static ch12ex8airlineseater.AirlineSeater.tempPass;
import static ch12ex8airlineseater.SeatingFrame.passengerList;

/**
 *
 * @author Ryan Heinrich
 */
public class ThirdFrame extends JFrame {
    private boolean firstClass;
    private int groupSize;
    private String seat;
    
    public ThirdFrame(boolean afirstClass,int aGroupSize){
        setSize(250,300);
        setLayout(new FlowLayout());
        firstClass = afirstClass;
        groupSize = aGroupSize;
        JLabel promt = new JLabel("How many are in your group?");
        add(promt);
        if(firstClass == true){
            addButton("aisle");
            addButton("window");
        }else{
            addButton("aisle");
            addButton("middle");
            addButton("window");
        }
        
    }
    
    private void addButton(final String label){
            JButton button = new JButton(label);
            button.setSize(160, 50);
            add(button);
            
            class DigitButtonListener implements ActionListener{
                @Override
                //this will open a frame that will allow you to view your email.
                public void actionPerformed(ActionEvent e){
                    if(label.equals("aisle")){
                        seat = "aisle";
                    }else if(label.equals("middle")){
                        seat = "middle";
                    }else if(label.equals("window")){
                        seat = "window";
                    }
                    
                    tempPass = new Passenger(firstClass, groupSize, seat);
                    JFrame frame = new SeatingFrame();
        
                    frame.setTitle("Amount in Group");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    passengerList.add(tempPass);
                    dispose();
                }
            }
            ActionListener listener = new DigitButtonListener();
            button.addActionListener(listener);
    }
}
