/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch10proj1mazeai;

/**
 * @author Ryan Heinrich
 */


//just look up: http://avian.netne.net/blog/?p=93
//this can teach me how the right hand rule works.

public class RightHandMoveRobot extends Robot {
        
        public RightHandMoveRobot(Maze m)
        {
                currentPosition = m.findb();
                System.out.println(m.findb().getx()+" "+ m.findb().gety());
                
        }
     
        
        //this code should work for any maze 9 by 9 maze until I fix the read method.
        public void Move(Maze m){
                //first find starting direction.
            //fuck all this stupid shit for now
                /*int startX = m.findb().getx() - m.finde().getx();
                int startY = m.findb().gety() - m.finde().gety();
                if(startX == 1){
                    direction = DIR_EAST;
                }
                else if(startX == -1){
                    direction = DIR_WEST;
                }
                else if(startY == 1){
                    direction = DIR_SOUTH;
                }
                else if(startY == -1){
                    direction = DIR_NORTH;
                }
                if (currentPosition.getx() == 0) //this sets the initial direction for the robot
                         direction = DIR_SOUTH;
                else if (currentPosition.getx() == 8)
                        direction = DIR_NORTH;
                else if (currentPosition.gety() == 0)
                        direction = DIR_EAST;
                else if (currentPosition.gety() == 8)
                        direction = DIR_WEST;
                */
                direction = DIR_NORTH;
                //while not at the end of map
                while (m.CheckPos(currentPosition.getx(), currentPosition.gety()) != 3){
                    switch (direction){
                        case (DIR_NORTH):{
                            System.out.println("North: "+currentPosition.getx()+" "+ currentPosition.gety() );
                            //1 or 3 so it can read free space or the win condition.
                            if (currentPosition.check(m, DIR_EAST) == 1  || currentPosition.check(m, DIR_EAST) == 3){//1 is free space.
                                currentPosition.setPosition(currentPosition.getx()+1, currentPosition.gety());
                                direction = DIR_EAST;
                                break;//breaks out of the switch.
                            }
                            else if(currentPosition.check(m, DIR_NORTH) == 1 || currentPosition.check(m, DIR_NORTH) == 3){
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()-1);
                                direction = DIR_NORTH;
                                break;
                            }
                            else if(currentPosition.check(m, DIR_WEST) == 1 || currentPosition.check(m, DIR_WEST) == 3){
                                currentPosition.setPosition(currentPosition.getx()-1, currentPosition.gety());
                                direction = DIR_WEST;
                                break;
                            }
                            else if(currentPosition.check(m, DIR_SOUTH) == 1 || currentPosition.check(m, DIR_SOUTH) == 3){
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()+1);
                                direction = DIR_SOUTH;
                                break;
                            }
                        }
                        case (DIR_SOUTH):{
                            System.out.println("South: "+currentPosition.getx()+" "+ currentPosition.gety());
                            if(currentPosition.check(m, DIR_WEST) == 1 || currentPosition.check(m, DIR_WEST) == 3){
                                currentPosition.setPosition(currentPosition.getx()-1, currentPosition.gety());
                                direction = DIR_WEST;
                                break;
                            }else if(currentPosition.check(m, DIR_SOUTH) == 1 || currentPosition.check(m, DIR_SOUTH) == 3){
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()+1);
                                direction = DIR_SOUTH;
                                break;
                            }else if (currentPosition.check(m, DIR_EAST) == 1 || currentPosition.check(m, DIR_EAST) == 3){//1 is free space.
                                currentPosition.setPosition(currentPosition.getx()+1, currentPosition.gety());
                                direction = DIR_EAST;
                                break;//breaks out of the switch.
                            }else if(currentPosition.check(m, DIR_NORTH) == 1 || currentPosition.check(m, DIR_NORTH) == 3){
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()-1);
                                direction = DIR_NORTH;
                                break;
                            }
                                
                        }
                        case (DIR_EAST):{
                            System.out.println("East: "+currentPosition.getx()+" "+ currentPosition.gety());
                            if(currentPosition.check(m, DIR_SOUTH) == 1 || currentPosition.check(m, DIR_SOUTH) == 3){
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()+1);
                                direction = DIR_SOUTH;
                                break;
                            }else if (currentPosition.check(m, DIR_EAST) == 1 || currentPosition.check(m, DIR_EAST) == 3){//1 is free space.
                                currentPosition.setPosition(currentPosition.getx()+1, currentPosition.gety());
                                direction = DIR_EAST;
                                break;//breaks out of the switch.
                            }else if(currentPosition.check(m, DIR_NORTH) == 1 || currentPosition.check(m, DIR_NORTH) == 3){
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()-1);
                                direction = DIR_NORTH;
                                break;
                            }else if(currentPosition.check(m, DIR_WEST) == 1 || currentPosition.check(m, DIR_WEST) == 3){
                                currentPosition.setPosition(currentPosition.getx()-1, currentPosition.gety());
                                direction = DIR_WEST;
                                break;
                            }
                        }
                        case (DIR_WEST):{
                            System.out.println("West: "+currentPosition.getx()+" "+ currentPosition.gety());
                            if(currentPosition.check(m, DIR_NORTH) == 1 || currentPosition.check(m, DIR_NORTH) == 3){
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()-1);
                                direction = DIR_NORTH;
                                break;
                            }
                            else if(currentPosition.check(m, DIR_WEST) == 1 || currentPosition.check(m, DIR_WEST) == 3){
                                currentPosition.setPosition(currentPosition.getx()-1, currentPosition.gety());
                                direction = DIR_WEST;
                                break;
                            }else if(currentPosition.check(m, DIR_SOUTH) == 1 || currentPosition.check(m, DIR_SOUTH) == 3){
                                currentPosition.setPosition(currentPosition.getx(), currentPosition.gety()+1);
                                direction = DIR_SOUTH;
                                break;
                            }else if (currentPosition.check(m, DIR_EAST) == 1 || currentPosition.check(m, DIR_EAST) == 3){//1 is free space.
                                currentPosition.setPosition(currentPosition.getx()+1, currentPosition.gety());
                                direction = DIR_EAST;
                                break;//breaks out of the switch.
                            }
                        }
                   }
             }
        }
        
}