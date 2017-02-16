/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch14ex13stringsortdemo;

/**
 *
 * @author Ryan Heinrich
 */
public class Student /*implements Comparable<Student> */{
    private String name;
    
    public Student(String aName){
        name = aName;
    }
    
    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        //return String.format("{name=%s}", name);
        return String.format("%s", name);
    }


}
