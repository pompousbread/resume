/*
 * Ryan Heinrich
 * Student ID: 2355923
 * CSCI 4401 - s16
 * Prof. Roussev
 */

package csci4401.service;

import static csci4401.service.BalancedMMServicePool.threadLimit;


/**
 * Example test code; lacks timing. Use as needed.
 * running three threads made it faster.
 */
public class TestService {

    public static void main(String[] argv) throws Exception {
        int processTotal = Runtime.getRuntime().availableProcessors();
        System.out.println("Total Processors "+processTotal);
        System.out.println("Threads in use "+threadLimit);
        sequentialTest();
        parallelTest();
        
        //determSequentialTest();
        //determParallelTest();
    }
    /**
     * Launches the workers one after the other.
     */
    static private void sequentialTest() throws Exception {
        //MatrixMultiplyServicePool servicePool = new MatrixMultiplyServicePool(5, 10);//(min, max) NOT USED!
        MatrixMultiplyServicePool servicePool = new BalancedMMServicePool(5, 10);//(min, max) NOT USED!

        int max = 8;
        long sum = 0;//holds sum of execution times.
        long temp = 0;//temporarily stores the time.
        long start = System.currentTimeMillis();
        for(int i=0; i<max; i++) {
            servicePool.addRequest(new MatrixMultiplyParameters(200, 100));//(size, iterations)//200, 100
            temp = (long)servicePool.getResponse();
            System.out.println(temp);
            sum += temp;
        }
        long end = System.currentTimeMillis();
        double d = sum;
        double seconds = (end-start) / 1000.0;
        System.out.println("sequentialTest: Time = "+seconds+" seconds.");
    }

    /**
     * Launches the workers in parallel.
     * okay all of these need to start at the same time and finish around the same time.
     */
    static private void parallelTest() throws Exception {
        //MatrixMultiplyServicePool servicePool = new MatrixMultiplyServicePool(5, 10);//(min, max) NOT USED!
        MatrixMultiplyServicePool servicePool = new BalancedMMServicePool(5, 10);//(min, max) NOT USED!

        int max = 8;
        long topTime = 0;//tracks the largest time.
        long temp = 0;//temporarily stores the time.
        long start = System.currentTimeMillis();
        
        for(int i=0; i<max; i++) {
            servicePool.addRequest(new MatrixMultiplyParameters(200, 100));
        }
        for(int i=0; i<max; i++) {
            temp = (long)servicePool.getResponse();
            if(topTime == 0){
                topTime = temp;
            }else if(topTime < temp){
                topTime = temp;
            }
            System.out.println(topTime);
        }
        long end = System.currentTimeMillis();
        double seconds = (end-start) / 1000.0;
        System.out.println("paralllelTest: "+ seconds + " seconds.");
    }
    /**
     * Launches Determinant workers one after the other.
     * I changed the request so it would create a MatrixDeterminantWorker.
     */
    static private void determSequentialTest() throws Exception {
        MatrixMultiplyServicePool servicePool = new MatrixMultiplyServicePool(5, 10);//(min, max) NOT USED!

        int max = 8;
        long sum = 0;//holds sum of execution times.
        long temp = 0;//temporarily stores the time.
        for(int i=0; i<max; i++) {
            servicePool.addDetRequest(new MatrixMultiplyParameters(10, 5));//(size, iterations)//200, 100
            temp = (long)servicePool.getResponse();
            System.out.println(temp);
            sum += temp;
        }
        double d = sum;
        double seconds = d / 1000.0;
        System.out.println("sequentialTest: Time = "+seconds+" seconds.");
    }

    /**
     * Launches Determinant workers in parallel.
     * okay all of these need to start at the same time and finish around the same time.
     */
    static private void determParallelTest() throws Exception {
        MatrixMultiplyServicePool servicePool = new MatrixMultiplyServicePool(5, 10);//(min, max) NOT USED!

        int max = 8;
        long topTime = 0;//tracks the largest time.
        long temp = 0;//temporarily stores the time.
        for(int i=0; i<max; i++) {
            servicePool.addDetRequest(new MatrixMultiplyParameters(10, 5));
        }
        for(int i=0; i<max; i++) {
            temp = (long)servicePool.getResponse();
            if(topTime == 0){
                topTime = temp;
            }else if(topTime < temp){
                topTime = temp;
            }
            System.out.println(topTime);
        }
        double seconds = topTime / 1000.0;
        System.out.println("paralllelTest: "+ seconds + " seconds.");

    }
}
