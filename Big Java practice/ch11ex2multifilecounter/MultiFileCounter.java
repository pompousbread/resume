

package ch11ex2multifilecounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Ryan Heinrich
 * 
 * Alright, the online programs count line skips as chars. maybe"\n"
 * but with that logic it makes sense why the chars are 20 less than the 
 * online char readers.
 * if you don't count the first line it would be 20 lines.
 */
public class MultiFileCounter {
    private static int lines;
    private static int words;
    private static int chars;
    
    private int sum;
    private static boolean isValid = true;

    public MultiFileCounter(int tempchar,int tempwords,int templines)
        {
            chars =tempchar;
            words =tempwords;
            lines =templines;
            System.out.println("Enter a text file name so its contents can be counted.");
            Scanner reader = new Scanner(System.in);
            String reply = reader.next();
                FileReader txt = null;
                Scanner file = null;
                try
                {

                    //the root folder is the one the holds src.
                    //BruceLeeWiki.txt
                    //ChuckNorrisWiki.txt
                        txt = new FileReader(reply);
                        file = new Scanner(txt);
                        readText(txt);  
                }
                catch( IOException e )
                {
                    System.out.println("You entered an invalid file. Program terminating.");
                    isValid = false;
                }
                finally
                {
                        if( file != null )//use these to 
                        {
                                file.close();
                        }
                        if(txt != null)
                        {
                                try {
                                        txt.close();
                                }catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        }
                }
        }
  
    private void readText(FileReader file) throws IOException{
        //works fine. it is not counting the empty space chars in the char counter
        //but I its not necessary for a simple task like this.
            
                int i, j;
                StringBuilder sb = new StringBuilder();
                String currentLine = null;
                BufferedReader br = new BufferedReader( file );
                //puts line by line into the Stringbuilder.
                while( (currentLine = br.readLine()) != null ){
                    sb.append(currentLine);
                    System.out.println(currentLine);
                    lines++;
                    int chart;
                    chart = currentLine.length();
                    for (String retval: currentLine.split("\\s")){//splits each string that has a space in it into a new string.
                        //System.out.println("letter "+retval.length());
                        //chars += retval.length();
                        if(retval.length() == 0){
                            break;//dont increment word.
                        }
                        words++;
                    }
                    chars += chart;
                    ///////System.out.print("chars "+chart);
                }
                String data = sb.toString();
                //System.out.println( "Data: " + data);//puts all info into one variable.
                System.out.println("lines: "+lines);
                System.out.println("words: "+words);
                System.out.println("chars: "+chars +" Expect 2225");
                
        }
    
    public static void main(String[] args) {
        while(isValid == true){
            MultiFileCounter counter = new MultiFileCounter(chars, words,lines);
            //its doubling everything.
        }
    }
    
}
