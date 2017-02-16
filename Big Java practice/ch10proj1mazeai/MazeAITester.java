/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch10proj1mazeai;

/**
 *
 * @author Ryan Heinrich
 */
public class MazeAITester {

    public static void main(String[] args) {
        
                Maze Findme = new Maze();
                //RightHandMoveRobot mem = new RightHandMoveRobot(Findme);
                //mem.Move(Findme);
                MemoryRobot Hand = new MemoryRobot(Findme);
                Hand.Move(Findme);
                //RandomRobot ran = new RandomRobot(Findme);
                //ran.Move(Findme);
                System.out.println("\n\nOH GOD WE MADE IT OUT OF THE LOOPS");
    }
    
}
