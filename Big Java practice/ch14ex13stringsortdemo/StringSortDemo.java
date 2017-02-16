

package ch14ex13stringsortdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Ryan Heinrich
 * 
 */
public class StringSortDemo {
    
    

    public static void main(String[] args)
   {
      ArrayList<Student> list = new ArrayList<Student>();
      Scanner in = new Scanner(System.in);
      
      boolean done = false;      
      while (!done)
      {
         System.out.println("Enter a string to sort or enter a blank line to display the list");
         String input = in.nextLine();
         Student tempStudent = new Student(input);
         if (input.equals(""))
            done = true;
         else
            list.add(tempStudent);
      }
      //Collections.sort

      Collections.sort(list, new StringLengthLexComparator());
      System.out.println(list);
   }
    
    
    
}
