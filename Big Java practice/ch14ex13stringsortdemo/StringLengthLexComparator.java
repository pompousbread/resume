

package ch14ex13stringsortdemo;

import java.util.Comparator;

/**
 *
 * @author Ryan Heinrich
 * 1. sort Strings by length.
 * 2. sort by length then lexicographically.
 * 
 */
public class StringLengthLexComparator implements Comparator<Student>{




    @Override
    public int compare(Student o1, Student o2) {
        String name1 =  o1.getName();
	String name2 =  o2.getName();
        
        if(name1.length() == name2.length()){//if equals put in order.
            return name1.compareToIgnoreCase(name2);
        }else{
            return name1.length() - name2.length();//else order by length.
        }
        
        

	// ascending order (descending order would be: name2.compareTo(name1))
	//return name1.compareTo(name2);
        //return name1.compareToIgnoreCase(name2);
    }
}
