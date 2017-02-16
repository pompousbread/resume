

package ch13proj2hanoipaint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Ryan Heinrich
 * 
 * the logic is here all I have to do is apply it to a paintComponent
 * 
 * okay, I could recusively figure out the moves. store them. then iterate through them
 * by clicking the button. 
 * or I could try to figure out how to do it on the fly. either way it's going to be tough.
 * 
 * okay so the logic works like a binary tree starting at the bottom left and working its way back up
 * ...if would look something like this if using 3 discs.
 * 
 *           3
 *         2   2
 *        1 1 1 1
 * 
 * the print order would be 
 *            4
 *          2   6
 *         1 3 5 7
 * 
 */
public class HanoiPaint extends JFrame{
    
    public static int discNumber;
    private static JButton buttonInput;
    public static ArrayList<Move> moveList;
    public static boolean called;
    public static int nextMove = 0;/*keeps track of how many clicks you have made. then loop through
    moveList and display those moves on the JComponent*/
    
    public HanoiPaint(){
        createLogInButton();//init the button.
        moveList = new ArrayList<>();
        called = false;
    }
    
    public static void solve(int n, String start, String auxiliary, String end, int moves) {
       //moves++;
       //System.out.println(moves);
       if (n == 1) {
           System.out.println("disk "+n+" goes from "+ start + " -> " + end +" "+moves);
           int location = 0;//should never be 0
           if("A".equals(end)){
               location = 1;
           }else if("B".equals(end)){
               location = 2;
           }else if("C".equals(end)){
               location = 3;
           }           
           moveList.add(new Move(n, location));
       } else {           
           solve(n - 1, start, end, auxiliary, 1);// corresponds to moving the largest disc at the bottom from the start peg to the end peg.
           System.out.println("disk "+n+" goes from "+ start + " -> " + end+" "+ moves);
           int location = 0;//should never be 0
           if("A".equals(end)){
               location = 1;
           }else if("B".equals(end)){
               location = 2;
           }else if("C".equals(end)){
               location = 3;
           }           
           moveList.add(new Move(n, location));
           solve(n - 1, auxiliary, start, end, 2);//the auxiliary peg becomes the start peg and the start peg becomes the auxiliary peg. 
       }
   }

   public static void main(String[] args) {
        JFrame frame = new HanoiPaint();
        
        frame.setSize(600, 400);
        frame.setTitle("Towers of Hanoi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       System.out.print("Enter number of discs: ");
       Scanner scanner = new Scanner(System.in);
       discNumber = scanner.nextInt();
       
       frame.add(buttonInput);
       TowerComponent component = new TowerComponent();
       frame.add(component);
       
       frame.setVisible(true);
       
       solve(discNumber, "A", "B", "C", 0);
       
       for(Move temp:moveList){
           System.out.println(temp.getPosition()+" : "+ temp.getDisk());
       }
   }
   
   private void createLogInButton(){
        buttonInput = new JButton("Move Disk");
        buttonInput.setSize(120, 20);
        buttonInput.setLocation(600/2-60,400-75);
        
        class AddInterestListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
               nextMove++;
               called = true;
               repaint();
               if(nextMove > moveList.size()){
                   System.exit(0);
               }
            }
        }
        ActionListener listener = new AddInterestListener();
        buttonInput.addActionListener(listener);
    }
    
}
