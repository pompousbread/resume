/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch10proj1mazeai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ryan Heinrich
 */
public class Maze {
    
        private char[][] ver = new char[9][9];
        
        public static final int POINT_ENTRANCE = 0;
        public static final int POINT_SPACE = 1;
        public static final int POINT_WALL = 2;
        public static final int POINT_EXIT = 3;
        
            
        public Maze()
        {       
                FileReader txt = null;
                Scanner file = null;
                try
                {

                        txt = new FileReader("DemoMaze.txt");
                        System.out.println("got here.");
                        file = new Scanner(txt);
                        readmaze(txt);  
                }
                catch( IOException e )
                {
                }
                finally
                {
                        if( file != null )
                        {
                                file.close();
                        }
                        if( txt != null )
                        {
                                try {
                                        txt.close();
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        }
                }
        }
        
        private void readmaze(FileReader file) throws IOException
        {
            
                int i, j;
                StringBuilder sb = new StringBuilder();
                String currentLine = null;
                BufferedReader br = new BufferedReader( file );
                //puts line by line into the Stringbuilder.
                //I have not idea what a string builder is.
                //I'm assumeing that it is basically the same thing as a string.
                while( ( currentLine = br.readLine() ) != null )
                {
                        sb.append( currentLine );
                }
                String data = sb.toString();
                System.out.println( "Data: " + data);//puts all info into one variable.
                System.out.println(data.length());
                
                //I could replace i with a counter for how many times append is called in the while loop.
                for(i = 0; i < 9; i++){
                    //I could replace J with each current lines length.
                        for(j = 0; j < 9; j++){
                                ver[j][i] = data.charAt(i*9+j);  
                                System.out.print(ver[j][i]);
                                //I switched these to give x and y the proper positions.
                        }
                        System.out.println();
                        
                }
                
        }
        
        //beginning
        public position findb()
        {
                int i, j;
                
                position entrance, fail;
                
                
                //need to replace all of these magic numbers.
                for(i = 0; i < 9; i++)
                {
                        for(j = 0; j < 9; j++)
                        {
                                if(ver[i][j] == 'o')
                                {
                                        entrance = new position(i, j);
                                        return entrance;
                                }
                                
                        }
                        
                }
                System.err.println("No entrance found!");
                System.exit(1);
                return(fail = new position());
        }
        
        //end
        public position finde()
        {
                int i, j;
                
                position exit, fail;
                
                
                //ahhhhh magic numbers!!
                for(i = 0; i < 9; i++)
                {
                        for(j = 0; j < 9; j++)
                        {
                                if(ver[i][j] == 'x')
                                {
                                        exit = new position(i, j);
                                        return exit;
                                }
                                
                        }
                        
                }
                System.err.println("No exit found!");
                System.exit(1);
                return(fail = new position());
        }
        
        public int CheckPos(int x, int y)
        {
                if(x < 0 || x > 8 || y < 0 || y > 8)
                {
                        System.err.println("Out of bounds!");
                        return -1; 
                }

        
                if(ver[x][y] == 'o')
                        return POINT_ENTRANCE;//0
                else if (ver[x][y] == ' ')
                        return POINT_SPACE;//1
                else if (ver[x][y] == '*')
                        return POINT_WALL;//2
                else if (ver[x][y] == 'x')
                        return POINT_EXIT;//3
                else
                        System.err.println("Not valid Character!!");
                        return -2;
        }
}
