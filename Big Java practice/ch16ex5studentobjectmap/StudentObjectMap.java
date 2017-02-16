/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch16ex5studentobjectmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author McNasty
 * 
 *
 * Okay so this is almost the same. The student will have 3 parameters and the grade will 
 * Still be the second part of the Map.
 * id's are going to be 4 digit ints.
 * 
 * 
 * okay sort by last, first , then ID number.
 * modify and removals are done by the  unique id number.
 * 
 * I'm not sure why they think I need 2 maps but whatever.
 * Well I guess I need another set to sort.
 * 
 * 
 * boom baby here is the plan. Use Grades to get most of the info. 
 * when removing and modifying use the studentID.
 * 
 */
public class StudentObjectMap {

    private static Map<Student, String> studentsGrades;
    private static Map<Integer, Student> studentID;
    
    public StudentObjectMap(){
        studentsGrades = new HashMap<>();
        studentID = new HashMap<>();
    }
    
    public void add(Student aStudent, String grade){
        studentsGrades.put(aStudent, grade);
        studentID.put(aStudent.getID(), aStudent);
    }

    //removes the student from both lists.
    public void remove(int aID){
        if(studentID.containsKey(aID)){
            Student temp = studentID.get(aID);
            studentsGrades.remove(temp);//remove from 1
            studentID.remove(aID);//remove from 2
            System.out.println("got it");
        }
    }
    
    //modifies the students grade.
    public void modify(int aID, String grade){
        if(studentID.containsKey(aID)){
            Student temp = studentID.get(aID);
            studentsGrades.put(temp, grade);
        }
        //String temp = studentsGrades.get(key);
        //studentsGrades.put(key, data);
    }
    
    public void print(){
        Set<Student> keySet = studentsGrades.keySet();
        
        List<Student> studentNames = new ArrayList<>(keySet);
        
        Collections.sort(studentNames, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                if(o1.getLast().equalsIgnoreCase(o2.getLast())){
                    if(o1.getFirst().equalsIgnoreCase(o2.getFirst())){
                        if(o1.getID() < o2.getID()){
                            return 0;
                        }else{
                            return o1.getID() - o2.getID();
                        }
                    }else{
                        return o1.getFirst().compareToIgnoreCase(o2.getFirst());
                    }
                }else{
                    return o1.getLast().compareToIgnoreCase(o2.getLast());
                }
                
            }
        });

        for(Student key: studentNames){
            
            String grade = studentsGrades.get(key);
            System.out.println("Name: "+key.getFirst()+" "+key.getLast()+" ID: "+key.getID()+" -> "+grade);
        }
    
    }
    
    public static void main(String[] args) {
        StudentObjectMap map = new StudentObjectMap();
        
        map.add(new Student("Steve","Jobs", 4455), "B+");
        map.add(new Student("Adam", "Smith", 3721), "C");
        map.add(new Student("Anna", "Madden", 9965), "A");

        
        Scanner in = new Scanner(System.in);
        System.out.println("Enter one of the commands: end, add, remove, modify, or print");
        
        boolean done = false;
        while(!done){
            String temp = in.next();
            if(temp.equals("end")){
                break;
            }else if(temp.equals("add")){
                System.out.println("Enter a firstName");
                String first = in.next();
                System.out.println("last name");
                String last = in.next();
                System.out.println("ID");
                int id = in.nextInt();
                System.out.println(",and grade.");
                String data = in.next();
                Student studentTemp = new Student(first, last, id);
                System.out.println("value: "+studentID.get(id).hashCode()+" temp: "+ studentTemp.hashCode());
                if(studentsGrades.containsKey(studentTemp)){////////////////
                    System.out.println("That name has already been entered.");
                }else{
                    map.add(studentTemp, data);
                }
                
            
            }else if(temp.equals("remove")){
                System.out.println("Enter a student Id to be removed");
                int aID = in.nextInt();
                if(studentID.containsKey(aID)){
                    map.remove(aID);
                }else{
                    System.out.println("There is no matching key.");
                }
                map.print();
            
            }else if(temp.equals("modify")){
                System.out.println("Enter a student ID and a modified grade:");
                int aID = in.nextInt();
                String grade = in.next();
                if(studentID.containsKey(aID)){
                    map.modify(aID, grade);
                }
                else{
                    System.out.println("Key does not exist.");
                }
            
            }else if(temp.equals("print")){
                map.print();
            }
            
        } 
        
        Set<Student> keySet = studentsGrades.keySet();
        for(Student key: keySet){
            String grade = studentsGrades.get(key);
            System.out.println(key+" -> "+grade);
        }
        
        
    }
    
}
