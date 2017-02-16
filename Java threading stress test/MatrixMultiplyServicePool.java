/*
 * Ryan Heinrich
 * Student ID: 2355923
 * CSCI 4401 - s16
 * Prof. Roussev
 */
package csci4401.service;

import java.io.Serializable;

/**
 * Simple implementaion of a service pool for matrix multiplication workers.
 * Most of the work is done by the worker (computation) and the message queue implementation (synchronization).
 */
public class MatrixMultiplyServicePool implements ServicePool {

    int poolMin, poolMax, poolSize=0;
    MsgQ resultQ = new BasicMsgQ();//this is the instance of the queue.
    MatrixMultiplyWorkerFactory factory = new MatrixMultiplyWorkerFactory();
    //ArrayList <MatrixMultiplyWorker> workerPool = new ArrayList();
    
    public static final Object poolMonitor = new Object();//used to regulate the amount of threads working.
    public static boolean poolMonitorState = false;

    /**
     * @param poolMin   minimum pool size (not used)
     * @param poolMax   maximum pool size (not used)
     */
    public MatrixMultiplyServicePool(int poolMin, int poolMax) {
        this.poolMin = poolMin;
        this.poolMax = poolMax;
    }
    
    
    public void waitForPoolThread() {
        poolMonitorState = true;
        while (poolMonitorState) {
          synchronized (poolMonitor) {
            try {
              poolMonitor.wait(); // wait until notified
            } catch (Exception e) {}
          }
        }
    }
    
    public static void unlockPoolWaiter() {
        synchronized (poolMonitor) {
          poolMonitorState = false;
          poolMonitor.notify(); // unlock again
        }
    }
    
    public void addDetRequest(Serializable request){
        factory.newDeterminantWorker(request, resultQ).start();//creates a new instance of a MMW.
    }
    
    /**
     * Trivial implementation: every request triggers the creation of a new thread, which at the end of the computation dies (it is NOT reused).
     * The request is matrix multiplication parameter.
     */
    public void addRequest(Serializable request) {
        //the request is serialized parameters.
        //newServiceWorker extends Thread. Explaining the .start();
        factory.newServiceWorker(request, resultQ).start();//creates a new instance of a MMW.                
    }

    /**
     * Pops the first response from the queue. BLOCKING.
     */
    public Serializable getResponse() throws InterruptedException {
        return resultQ.pop();
        //return resultQ.asyncPop();
    }
}