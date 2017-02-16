/*
 * Ryan Heinrich
 * Student ID: 2355923
 * CSCI 4401 - s16
 * Prof. Roussev
 */
package csci4401.service;


public class MatrixDeterminantWorker extends AbstractServiceWorker{
    private double[][] a;
    private int LOOPS;
    private int MAX;//matrix size.
    private MsgQ result;

    public MatrixDeterminantWorker(MatrixMultiplyParameters parameters, MsgQ resultQ) {
        super(parameters, resultQ);
        MAX = parameters.matrixSize;
        LOOPS = parameters.iterations;
        result = resultQ;
    }
    
    /**
     * Initializes the matrixes based on the size parameter.
     * populate the main diagonal by incrementing from 1 and fill the 
     * rest of the matrix with 0's.
     * <b>TODO:</b> Implement this method.
     */
    private void initMatrixes() {
        a = new double[MAX][MAX];
        int counter = 1;
        
         for(int i = 0; i < MAX; i++){
             for(int j = 0; j < MAX; j++){
                 if(i == j){
                    a[i][i] = counter;
                    counter++;
                }else{
                    a[i][j] = 0;
                }
             }
         } 
          
    }
    //tester for accuracy of determinant solver.
    private void determinantInitTester(){
        /*
        a = new double[2][2];//2 by 2 // 27
        a[0][0] = 2;a[1][0] = -3;
        a[0][1] = 1;a[1][1] = 12;
        */
        /*
        a = new double[3][3];//3 by 3 // -32
        //x, y across then down.
        a[0][0] = 1;a[1][0] = -2;a[2][0] = 4;
        a[0][1] = -5;a[1][1] = 2;a[2][1] = 0;
        a[0][2] = 1;a[1][2] = 0;a[2][2] = 3;
        */
        /*
         a = new double[5][5];//5 by 5// -100
         for(int i = 0; i < 5; i++){
             for(int j = 0; j < 5 ; j++){
                 a[i][j] = 0;
             }
         }
         a[0][0] = 5;a[1][0] = 2;a[4][0] = -2;
         a[1][1] = 1;a[2][1] = 4;a[3][1] = 3;a[4][1] = 2;
         a[2][2] = 2;a[3][2] = 6;a[4][2] = 3;
         a[2][3] = 3;a[3][3] = 4;a[4][3] = 1;
         a[4][4] = 2;
    */    
    }
    /*
        implements cofactor expansion for getting the determinant. This finds the 
        determinant of a matrix by breaking the larger matrix into smaller matrices
        and finding there determinant.
    */
    public double determinant(int N){
        double det = 0;
        if(N == 1){
            det = a[0][0];
        }
        else if (N == 2){// 2 by 2 matrix. optimazation.
            det = a[0][0] * a[1][1] - a[1][0] * a[0][1];
        }
        else{
            det = 0;
            for(int j1 = 0; j1 < N; j1++){
                //generates temporary sub arrays.
                double[][] temp = new double[N-1][];
                for(int k = 0; k < (N-1); k++){
                    temp[k] = new double[N-1];
                }
                for(int i = 1; i < N; i++){
                    int j2 = 0;
                    for(int j = 0; j < N; j++){
                        if(j == j1){
                            continue;
                        }
                        temp[i-1][j2] = a[i][j];
                        j2++;
                    }
                }
                //sums together the products of all the submatrices determinants.
                det += Math.pow(-1.0 , 1.0 + j1 + 1.0)* a[0][j1] * determinant(N-1);
            }
        }
        return det;
    }
    
    public void run(){
        /*
        used to test determinant accuracy.
        double testResult = determinant(a, MAX);//result should be -32
        System.out.println("test: "+testResult);
        result.append(testResult);
        */
        initMatrixes();
        long start = System.currentTimeMillis();
        for(int l=0; l<LOOPS; l++) {
            determinant( MAX);
        }
        long end = System.currentTimeMillis();
        result.append((end-start));   
    }
}
