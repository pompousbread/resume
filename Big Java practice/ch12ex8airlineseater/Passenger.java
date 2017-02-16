/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch12ex8airlineseater;

/**
 *
 * @author Ryan Heinrich
 */
public class Passenger {
    //a passenger can have multiple seats.
    //the names should be changed to tickets.
    boolean firstClass;
    int groupNumber;
    String preference;//if an invalid is giving passenger is randomly seated.
    int seatRow;
    int seatColumn;
    
    public Passenger(boolean aClass, int aGroupNumber, String aPreference){
        firstClass = aClass;
        groupNumber = aGroupNumber;
        preference = aPreference;
    }
    
}
