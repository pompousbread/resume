/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch12ex8airlineseater;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Ryan Heinrich
 */
public class FirstFrame extends JFrame{
    private boolean firstClass = false;

    FirstFrame(){
        setSize(250,300);
        setLayout(new FlowLayout());
        JLabel promt = new JLabel("Would you like to sit First Class.");
        add(promt);
        addButton("yes");
        addButton("no");
    
    }


    private void addButton(final String label){
            JButton button = new JButton(label);
            button.setSize(160, 50);
            add(button);
            class DigitButtonListener implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e){
                    if(label.equals("yes")){
                        firstClass = true; 
                    }else if(label.equals("no")){
                        firstClass = false;
                    }
                    JFrame frame = new SecondFrame(firstClass);
        
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