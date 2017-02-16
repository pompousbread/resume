/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch16ex5studentobjectmap;

/**
 *
 * @author McNasty
 * boom the hash code works!
 */
public class Student {
    private String firstName;
    private String lastName;
    private int studentID;
    
    public Student(String aFirst, String aLast, int aID){
        firstName = aFirst;
        lastName = aLast;
        studentID = aID;
    }
    
    @Override
    public boolean equals(Object otherObject){
        if(otherObject == null) return false;
        if(getClass() != otherObject.getClass()) return false;
        Student other = (Student) otherObject;
        return studentID == other.studentID && firstName.equals(other.firstName) && lastName.equals(other.lastName);
    }
    
    @Override
    public int hashCode(){
        int h1 = firstName.hashCode();
        int h2 = lastName.hashCode();
        int h3 = new Integer(studentID).hashCode();
        final int HASH_MULTIPLIER = 101;//prime numbers makes it more random.
        int h = HASH_MULTIPLIER * h1 + h2; 
        h = HASH_MULTIPLIER * h + h3;
        return h;
    }
    
    public String getFirst(){
        return firstName;
    } 
    public String getLast(){
        return lastName;
    }
    
    public int getID(){
        return studentID;
    }
    
}
