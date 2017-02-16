/*
 * Ryan Heinrich
 * Student ID: 2355923
 * CSCI 4401 - s16
 * Prof. Roussev
 */

package csci4401.service;

import java.io.Serializable;

/**
 * A more advanced implementaion of a service pool for matrix multiplication workers, 
 * which matches the number of outstanding jobs to the number of available hardware-supported threads.
 */
public class BalancedMMServicePool extends MatrixMultiplyServicePool {
    public static int threadLimit =  3;

    public BalancedMMServicePool(int poolMin, int poolMax) {
        super(poolMin, poolMax);
    }
    /**
     * Notifies the service pool that a worker has completed the computation with the given result.
     * If there are any outstanding requests, the first in line should be serviced.
     */
    
    public void addRequest(Serializable request) {
        if(threadLimit <= 0){//must wait to execute.
            this.waitForPoolThread();//blocks thread from executing.
            threadLimit--;
            super.addRequest(request);
            System.out.println("done waiting.");
        }else{//have not reach limit
            threadLimit--;
            System.out.println("done waiting.");
            super.addRequest(request);
        }
        
    }
}
