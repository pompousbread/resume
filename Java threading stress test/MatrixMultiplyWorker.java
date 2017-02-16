/*
 * Ryan Heinrich
 * Student ID: 2355923
 * CSCI 4401 - s16
 * Prof. Roussev
 */

package csci4401.service;


/**
 * <b>[TODO]</b> Matrix multiplier worker.
 * Implement missing functionality in this class.
 */
public class MatrixMultiplyWorker extends AbstractServiceWorker {

    private double[][] a, b, c;
    private int LOOPS;
    private int MAX;
    private MsgQ result;

    public MatrixMultiplyWorker(MatrixMultiplyParameters parameters, MsgQ resultQ) {
        super(parameters, resultQ);
        MAX = parameters.matrixSize;
        LOOPS = parameters.iterations;
        result = resultQ;
    }

    /**
     * Initializes the matrixes based on the size parameter.
     * <b>TODO:</b> Implement this method.
     */
    private void initMatrixes() {
        a = new double[MAX][MAX];
        b = new double[MAX][MAX];
        c = new double[MAX][MAX];

        for(int i=0; i<MAX; i++) {
            for(int j=0; j<MAX; j++) {
                a[i][j] = (i+1)*(j+1);
                b[i][j] = (i+2)*(j+2);
            }
        }   
    }

    /**
     * Performs one iterarion of matrix multiplication.
     * <b>TODO:</b> Implement this method.
     */
    private void doMatrixMultiplication() {
        for(int i=0; i<MAX; i++)
            for(int j=0; j<MAX; j++)
                for(int k=0; k<MAX; k++)
                        c[i][j] += a[i][k]*b[k][j];
    }

    /**
     * Invokes the initialization of source matrices and the execution of the required number of iterations.
     * The result is a <pre>Long</pre> object, which contains the execution time in milliseconds for all computations, <b>without</b> the initialization.
     * <b>TODO:</b> Implement this method.
     */
    public void run(){
        initMatrixes();
        long start = System.currentTimeMillis();
        for(int l=0; l<LOOPS; l++) {
            doMatrixMultiplication();
        }
        long end = System.currentTimeMillis();
        result.append((end-start));
    }

}
