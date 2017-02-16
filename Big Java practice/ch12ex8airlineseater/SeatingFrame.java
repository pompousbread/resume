
package ch12ex8airlineseater;

import static ch12ex8airlineseater.AirlineSeater.plane;
import static ch12ex8airlineseater.AirlineSeater.tempPass;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Ryan Heinrich
 * 
 * if you have more than one in your group your preference doesn't matter.
 * 
 * I did not make anything to say if a section is full of the plane is full
 * but that shouldn't be to hard. all of the difficult stuff is already worked out.
 * 
 */
public class SeatingFrame extends JFrame{
    public static ArrayList<Passenger> passengerList;
    
    private int whichFrame = 0;
        
    private JTextArea outDisplay;
    private JLabel promt;
    private int buttonIterator = 0;
    
    
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 700;

    public SeatingFrame(){
        //passengerList = new ArrayList<Passenger>();
        
        createFormat();       
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        seatThePlane();//seats the whole plan.
                    
    }
            
    
    public void createFormat(){

        setLayout(null);

        promt = new JLabel("United Airlines. Please choose from the options below.");
        promt.setHorizontalAlignment(SwingConstants.CENTER);
        promt.setSize(400, 60);
        promt.setLocation(FRAME_WIDTH/2-200, FRAME_HEIGHT/2-30-270);
        
        add(promt);
        addButton("add passengers");
        addButton("show seating");
        addButton("quit");
        
        outDisplay = new JTextArea(5, 20);
        outDisplay.setLocation(FRAME_WIDTH/2-150, FRAME_HEIGHT/2-50);
        outDisplay.setSize(300, 350);
        add(outDisplay);
        
    }
    
    private void addButton(final String label){
        JButton button = new JButton(label);
        button.setLocation(FRAME_WIDTH/2-80, FRAME_HEIGHT/2-25-225 +(70*buttonIterator));
        button.setSize(160, 50);
        add(button);
        buttonIterator++;
        class DigitButtonListener implements ActionListener{
            @Override
            //this will open a frame that will allow you to view your email.
            public void actionPerformed(ActionEvent e) {
                boolean firstClass = false;
                int groupNumnber = 0;
                
                if(label.equals("add passengers")){
                    JFrame frame = new FirstFrame();
        
                    frame.setTitle("FirstClass");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    dispose();
                    
                }else if(label.equals("show seating")){
                    String seating = "X's are occupied. O's are open.\n";
                    //20 rows
                    for(int i = 0; i < 20; i++){
                        for(int j = 0; j < 7; j++){
                            seating += plane[j][i];
                            if(j == 6){seating +="\n";}
                        }
                    }
                    /*for(int i = 0;i < 20;i++){
                        if(i < 5){
                            //capital o's not zeros
                            //it's not lining up perfectly but its close enough.
                            seating +="  OO OO  \n";
                        }else{
                            seating +="OOO OOO\n";
                        }             
                    }*/
                    for(int i = 0; i < 20; i++){
                        for(int j = 0; j < 7; j++){
                            System.out.print(plane[j][i]);
                            if(j == 6){seating +="\n";}
                        }
                    }
                    outDisplay.setText(seating);
                    
                        
                    
                }else if(label.equals("quit")){
                    System.exit(0);
                }
                //display.setText(display.getText() +label);
                
            }   
        }
        ActionListener listener = new DigitButtonListener();
        button.addActionListener(listener);
    }
    
    public void seatThePlane(){
        if(tempPass != null){
        Passenger temp = tempPass;
            if(temp.firstClass == true){
                if(temp.groupNumber == 2){
                    outerloop:
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 7; j++){
                            if(j==1 || j==4){
                                if(plane[j][i] =="O" && plane[j+1][i] =="O"){
                                    plane[j][i] ="X";
                                    plane[j+1][i] ="X";
                                    break outerloop;//break loop1 
                                }
                            }
                        }
                    }                 
                }else if(temp.groupNumber == 1){
                    if(temp.preference.equals("aisle")){
                        outerloop:
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 7; j++){
                                if(j== 2 || j ==4){
                                    if(plane[j][i] =="O"){
                                        plane[j][i] ="X";
                                        break outerloop;
                                    }
                                }
                                }
                            }
                        }else{//else window bitch
                            outerLoop:
                            for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 7; j++){
                                if(j== 1 || j ==5){
                                    if(plane[j][i] =="O"){
                                        plane[j][i] ="X";
                                        break outerLoop;
                                    }
                                }
                                }
                            }
                        }
                    }
                        
            }else{//else your are not first class.
                if(temp.groupNumber == 3){
                    outerloop:
                    for(int i = 5; i < 20; i++){
                        for(int j = 0; j < 7; j++){
                            if(j==0 || j==4){
                                if(plane[j][i] =="O" && plane[j+1][i] =="O" && plane[j+2][i] =="O"){
                                    plane[j][i] ="X";
                                    plane[j+1][i] ="X";
                                    plane[j+2][i] ="X";
                                    break outerloop;//break loop1 
                                }
                            }
                        }
                    }                 
                }else if(temp.groupNumber == 2){//preference doesn't matter with 2.(only 1)
                    outerloop:
                    for(int i = 5; i < 20; i++){
                        for(int j = 0; j < 7; j++){
                            if(j==0 || j==1 || j==4 || j==5){
                                if(plane[j][i] =="O" && plane[j+1][i] =="O"){
                                    plane[j][i] ="X";
                                    plane[j+1][i] ="X";
                                    break outerloop;//break loop1 
                                }
                            }
                        }
                    }
                }else if(temp.groupNumber == 1){
                    if(temp.preference.equals("aisle")){
                        outerloop:
                        for(int i = 5; i < 20; i++){
                            for(int j = 0; j < 7; j++){
                                if(j== 2 || j ==4){
                                    if(plane[j][i] =="O"){
                                        plane[j][i] ="X";
                                        break outerloop;
                                    }
                                }
                             }
                         }
                     }else if(temp.preference.equals("middle")){
                         outerLoop:
                         for(int i = 5; i < 20; i++){
                            for(int j = 0; j < 7; j++){
                                if(j== 1 || j ==5){
                                    if(plane[j][i] =="O"){
                                        plane[j][i] ="X";
                                        break outerLoop;
                                    }
                                }
                             }
                          }
                      }else{//else window bitch!
                         outerLoop:
                         for(int i = 2; i < 20; i++){
                            for(int j = 0; j < 7; j++){
                                if(j== 0 || j ==6){
                                    if(plane[j][i] =="O"){
                                        plane[j][i] ="X";
                                        break outerLoop;
                                    }
                                }
                             }
                          }  
                      }
                }
            
            }
        }
    }
    
    
}
