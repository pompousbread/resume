

package ch12ex8airlineseater;

//Ryan Heinrich

import javax.swing.JFrame;


public class AirlineSeater {
    
    public static Passenger tempPass;
    //7spaces including the aisle.
    public static String plane[][] = new String[7][20];

    public static void main(String[] args) {
        //i is rows, j is columns 
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 7; j++){
                if((i < 5) && (j==1 || j==2 || j==4 || j==5)){
                    plane[j][i] = "O";
                }/*else{
                    plane[j][i] = "0";
                }*/
                else if(i >= 5 && (j==0 || j==1 || j==2 || j==4 || j==5|| j==6)){
                    plane[j][i]= "O";
                }else{
                    plane[j][i]= " ";
                }
            }
        }
        
        
        
        
        
        
        JFrame frame = new SeatingFrame();
        
        frame.setTitle("look at your Schdule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);    
    }
        
}
