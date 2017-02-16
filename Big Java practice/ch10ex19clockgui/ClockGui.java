

package ch10ex19clockgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Ryan Heinrich
 */

public class ClockGui extends JFrame{

    private JTextField minField;
    private JTextField hourField;
    private JButton buttonInput;
    private JLabel minLabel;//balance label
    private JLabel hourLabel;
    
    public static final int FRAME_WIDTH = 400;
    public static final int FRAME_HEIGHT = 500;
    public static int minute = 0;
    public static int hour = 0;
    
    
    public ClockGui(){
        
        
        minLabel = new JLabel("Enter Minutes:");
        minLabel.setLocation(40, 20);
        minLabel.setSize(100, 10);
        hourLabel = new JLabel("Enter Hours:");
        hourLabel.setLocation(40, 40);
        hourLabel.setSize(100, 10);


 
        createTextField();
        createAddButton();
        createPanel();
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    
    private void createTextField(){
        final int FIELD_WIDTH = 10;
        
        minField = new JTextField(FIELD_WIDTH);
        minField.setLocation(200, 20);
        minField.setSize(80, 20);
        minField.setText("0");
        
        hourField = new JTextField(FIELD_WIDTH);
        hourField.setLocation(200, 40);
        hourField.setSize(80, 20);
        hourField.setText("0");
       
    }

    private void createAddButton(){
        buttonInput = new JButton("Calculate Time");
        buttonInput.setSize(140, 20);
        buttonInput.setLocation(FRAME_WIDTH/2-70,80);
        
        class AddInterestListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                //textArea.setText("");//clears old text
                
                String minValue = minField.getText();
                minute=  Integer.parseInt(minValue);
                
                String hourValue = hourField.getText();
                hour=  Integer.parseInt(hourValue);
                repaint();
                /*textArea.append("balance: "+ mySavings.getBalance()+"\n"
                +"rate: "+DEFAULT_RATE+"\n"
                +"years left: "+ mySavings.getYears());*/
                
                //maxLabel.setText(String.valueOf("The max is "+heights.getMaximum()+"."));
                //minLabel.setText(String.valueOf("The min is "+heights.getMinimum()+"."));
                //avgLabel.setText(String.valueOf("The average is "+heights.getAverage()+"."));

            }
        }
        ActionListener listener = new AddInterestListener();
        buttonInput.addActionListener(listener);
    }
    
    
    private void createPanel(){
        
        
        //setLayout(null);//Boom! this allows me to hardcode the location of everything on the frame.        

        add(minLabel);
        add(minField);
        
        add(hourLabel);
        add(hourField);

        add(buttonInput);


    }
    
}

