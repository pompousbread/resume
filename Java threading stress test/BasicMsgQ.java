/*
 * Ryan Heinrich
 * Student ID: 2355923
 * CSCI 4401 - s16
 * Prof. Roussev
 */

package csci4401.service;

import static csci4401.service.BalancedMMServicePool.threadLimit;
import static csci4401.service.MatrixMultiplyServicePool.unlockPoolWaiter;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>[TODO]</b> Basic queue implementation.
 * Implement missing functionality in this class. 
 */
public class BasicMsgQ implements MsgQ {
    public final Object monitor = new Object();
    public boolean monitorState = false;
    public LinkedList <Serializable> taskQueue = new LinkedList<>();
    
    /*
     * Each BasicMsgQ has a monitor that makes the thread wait until it is notified
     * from a call to UnlockWaiter to run again. The synconization ensures that the 
     * threads will not interfer with each other while sharing data.
     */
    public void waitForThread() {
        monitorState = true;
        while (monitorState) {
          synchronized (monitor) {
            try {
              monitor.wait(); // wait until notified
            } catch (Exception e) {}
          }
        }
    }
    
    public void unlockWaiter() {
        synchronized (monitor) {
          monitorState = false;
          //monitor.notifyAll(); // unlock again
          monitor.notify(); // unlock again
        }
    }
    
    /**
     * <b>TODO:</b> Implement this method as per the interface specification.
     * A thread has just finished so once it's message is added to the Queue it 
     * can unlock the thread waiting to retrieve data from the queue.
     */
    
    public void append(Serializable message){
        taskQueue.addLast(message);
        unlockWaiter();//unblocks for taskQueue
        unlockPoolWaiter();//unblocks for threadPool
    }

    /**
     * <b>TODO:</b> Implement this method as per the interface specification.
     * Once data has entered the queue the main thread can be unblocked and 
     * retrieve it's data from the Queue.
     * @throws java.lang.InterruptedException
     */
    public Serializable pop() throws InterruptedException {
        Serializable temp = null;
            if(taskQueue.isEmpty()){
                waitForThread();//blocks for taskQueue.
            }
            temp = taskQueue.removeFirst();
            threadLimit++;
            
            return temp;
    }

    /**
     * <b>TODO:</b> Implement this method as per the interface specification.
     * this one is first and should be easy.
     * sleep until taskQueue is filled.
     */
    public Serializable asyncPop() {//can move on to another task before it finishes.
        Serializable temp = null;
        boolean finished = false;
        while(!finished){
            if(taskQueue.isEmpty()){
                try {
                    Thread.sleep(1000);//1 second
                } catch (InterruptedException ex) {
                    Logger.getLogger(BasicMsgQ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                temp = taskQueue.removeFirst();
                finished = true;
            }   
        }
        return temp;
    }

}
