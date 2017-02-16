

package ch10proj1mazeai;

/**
 *
 * @author Ryan Heinrich
 */
import java.util.ArrayList;


public  class MemoryRobot extends Robot {
    

    //Its make a list of positions that have been visited.
    //If it comes to a dead it will back track until it reaches a postion that is not in memory.
    
    
    public MemoryRobot(Maze m)
        {
                currentPosition = m.findb();
                futurePosition = m.findb();
        }
        
        @Override
        public void Move(Maze m)
        {
                int x = 1;
                long signout = System.currentTimeMillis();
                memory.add(new position(currentPosition.getx(), currentPosition.gety()));
                lastresort.add(new position(currentPosition.getx(), currentPosition.gety()));
                while(m.CheckPos(currentPosition.getx(), currentPosition.gety()) != 3)
                { 
                        System.out.println(currentPosition.getx() + " " + currentPosition.gety()); // this is a location debugger
                        lastPosition = lastresort.get(lastresort.size()-1);//getting last enttry.
                        if(m.CheckPos(currentPosition.getx(), currentPosition.gety()+1) == 1 || m.CheckPos(currentPosition.getx(), currentPosition.gety()+1) == 3)
                        {
                                futurePosition.setPosition(currentPosition.getx(), currentPosition.gety()+1);
                                if(checkstack(memory, futurePosition) == true && checkstack(lastresort, futurePosition) == true)
                                {
                                    Robot josh = new Robot();
                                        memory.add(new position(currentPosition.getx(), currentPosition.gety())); 
                                        currentPosition.modify(0, 1);
                                        x = 0;
                                }
                        }
                        if(m.CheckPos(currentPosition.getx()+1, currentPosition.gety()) == 1 || m.CheckPos(currentPosition.getx()+1, currentPosition.gety()) == 3)
                        {
                                futurePosition.setPosition(currentPosition.getx()+1, currentPosition.gety());
                                if(checkstack(memory, futurePosition) == true && checkstack(lastresort, futurePosition) == true)

                                {
                                        memory.add(new position(currentPosition.getx(), currentPosition.gety())); 
                                        currentPosition.modify(1, 0);
                                        x = 0;
                                }
                        }
                        if(m.CheckPos(currentPosition.getx(), currentPosition.gety()-1) == 1 || m.CheckPos(currentPosition.getx(), currentPosition.gety()-1) == 3)
                        {
                                futurePosition.setPosition(currentPosition.getx(), currentPosition.gety()-1);
                                if(checkstack(memory, futurePosition) == true && checkstack(lastresort, futurePosition) == true)

                                {
                                        memory.add(new position(currentPosition.getx(), currentPosition.gety())); 
                                        currentPosition.modify(0, -1);
                                        x = 0;
                                }
                                
                        }
                        if(m.CheckPos(currentPosition.getx()-1, currentPosition.gety()) == 1 || m.CheckPos(currentPosition.getx()-1, currentPosition.gety()) == 3)
                        {
                                futurePosition.setPosition(currentPosition.getx()-1, currentPosition.gety());
                                if(checkstack(memory, futurePosition) == true && checkstack(lastresort, futurePosition) == true)

                                {
                                        memory.add(new position(currentPosition.getx(), currentPosition.gety())); 
                                        currentPosition.modify(-1, 0);
                                        x = 0;
                                }
                        }
                        if(x == 1)
                        {
                                lastresort.add(new position(currentPosition.getx(), currentPosition.gety()));
                                memory.remove(memory.size()-1);
                                currentPosition.setPosition(memory.get(memory.size()-1).getx(), memory.get(memory.size()-1).gety());
                        }
                        x = 1;
                        
                
                }
                System.out.println("\nMemory Robot has exited the maze");
                System.out.println("Took Memory Robot " + ((System.currentTimeMillis() - signout) / 1000 + " Seconds!")); 

        }
        
        private Boolean checkstack(ArrayList<position> j, position p)
        {
                //the the future pos is equal to anything in the stack. dont add it again...
                for(int i = 0; i < j.size()-1; i++)
                {
                        if(j.get(i).getx() == p.getx() && j.get(i).gety() == p.gety())
                        {
                                return false;
                        }
                }
                
                return true;
        }
        
        
        
        private ArrayList<position> memory = new ArrayList<position>();
        private ArrayList<position> lastresort = new ArrayList<position>();
    
    
        
}
