

package ch13proj2hanoipaint;

/**
 *
 * @author Ryan Heinrich
 */
public class Move {
    private int position;
    private int disk;
    
    public Move(int aDisk, int aPosition){
        position = aPosition;
        disk = aDisk;
    }
    
    public int getPosition(){
        return position;
    }
    
    public int getDisk(){
        return disk;
    }
    
}
