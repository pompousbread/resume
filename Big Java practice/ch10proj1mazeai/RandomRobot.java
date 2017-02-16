
package ch10proj1mazeai;

import java.util.Random;
public class RandomRobot extends Robot {

        
        public RandomRobot(Maze m)
        {
                currentPosition = m.findb();
                
        }
        
        public void Move(Maze m)
        {
                Random rand = new Random();
                int Moves = 0;
                long signout = System.currentTimeMillis();

                while(m.CheckPos(currentPosition.getx(), currentPosition.gety()) != 3)
                {
                        boolean rightTrue = false;
                        boolean upTrue = false;
                        boolean leftTrue = false;
                        boolean downTrue = false;
                        String temp ="";
                        if(direction == 0){temp ="North";}
                        else if(direction == 1){temp ="East";}
                        else if(direction == 2){temp ="South";}
                        else if(direction == 3){temp ="West";}
                        
                        System.out.println(temp+  " : "+currentPosition.getx()+" "+ currentPosition.gety() );
                        int count = 0;
                            //1 or 3 so it can read free space or the win condition.
                            if (currentPosition.check(m, DIR_EAST) == 1  || currentPosition.check(m, DIR_EAST) == 3){//1 is free space.
                                count++;
                                rightTrue = true;
                                //currentPosition.setPosition(currentPosition.getx()+1, currentPosition.gety());
                                //direction = DIR_EAST;
                                //break;//breaks out of the switch.
                            }
                            if(currentPosition.check(m, DIR_NORTH) == 1 || currentPosition.check(m, DIR_NORTH) == 3){
                                count++;
                                upTrue = true;
                                //currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()-1);
                                //direction = DIR_NORTH;
                                //break;
                            }
                            if(currentPosition.check(m, DIR_WEST) == 1 || currentPosition.check(m, DIR_WEST) == 3){
                                count++;
                                leftTrue = true;
                                //currentPosition.setPosition(currentPosition.getx()-1, currentPosition.gety());
                                //direction = DIR_WEST;
                                //break;
                            }
                            if(currentPosition.check(m, DIR_SOUTH) == 1 || currentPosition.check(m, DIR_SOUTH) == 3){
                                count++;
                                downTrue = true;
                                //currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()+1);
                                //direction = DIR_SOUTH;
                                //break;
                            }
                        boolean makeAMove = false;
                        while(makeAMove == false){
                            int thatway = rand.nextInt(4);
                            System.out.println(thatway);
                            if(thatway == 0 && upTrue == true){//north
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()-1);
                                direction = DIR_NORTH;
                                makeAMove = true;
                                Moves++;
                            }else if(thatway == 1 && rightTrue == true){//east
                                currentPosition.setPosition(currentPosition.getx()+1, currentPosition.gety());
                                direction = DIR_EAST;
                                makeAMove = true;
                                Moves++;
                            }else if(thatway == 2 && downTrue == true){//south
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()+1);
                                direction = DIR_SOUTH;
                                makeAMove = true;
                                Moves++;
                            }else if(thatway == 3 && leftTrue == true){//west
                                currentPosition.setPosition(currentPosition.getx()-1, currentPosition.gety());
                                direction = DIR_WEST;
                                makeAMove = true;
                                Moves++;
                            }
                        }
                }
                System.out.println("Took Memory Robot " + ((System.currentTimeMillis() - signout) / 1000.0 + " Seconds!"));
                System.out.println("Took " + Moves + " Moves");
        }
}
