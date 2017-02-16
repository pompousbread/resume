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

/**
 *
 * @author Ryan Heinrich
 */
public class SecondFrame extends JFrame {
    
    private boolean firstClass;
    private int groupSize;
            
    public SecondFrame(boolean afirstClass){
        setSize(250,300);
        setLayout(new FlowLayout());
        firstClass = afirstClass;
        JLabel promt = new JLabel("How many are in your group?");
        add(promt);
        if(firstClass == true){
            addButton("1");
            addButton("2");
        }else{
            addButton("1");
            addButton("2");
            addButton("3");
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
                    if(label.equals("1")){
                        groupSize = 1;
                    }else if(label.equals("2")){
                        groupSize = 2;
                    }else if(label.equals("3")){
                        groupSize = 3;
                    }
                    JFrame frame = new ThirdFrame(firstClass, groupSize);
        
                    frame.setTitle("Amount in Group");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    dispose();
                }
            }
            ActionListener listener = new DigitButtonListener();
            button.addActionListener(listener);
    }
}
