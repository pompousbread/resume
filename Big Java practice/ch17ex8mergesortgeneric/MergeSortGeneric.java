
package ch17ex8mergesortgeneric;

import java.util.Arrays;

/**
 *
 * @author Ryan Heinrich
 */
public class MergeSortGeneric {
    private String[] a;
    
    
    
    
    
    
    
    // Wrapper method for the real algorithm
    // T is the generic type which will be instantiated at runtime
    //  elementas are required to be comparable
    public static <T extends Comparable<T>> void sort(T[] a) {
	mergesort(a, 0, a.length-1);
    }
    
    // Recursive mergesort method, following the pseudocode
    private static <T extends Comparable<T>> void mergesort (T[] a, int i, int j) {
	if (j-i < 1) return;
	int mid = (i+j)/2;
	mergesort(a, i, mid);
	mergesort(a, mid+1, j);
	merge(a, i, mid, j);
    }

    // Merge method
    // Here we need to allocate a new array, but Java does not allow allocating arrays of a generic type
    // As a work-around we allocate an array of type Object[] the use type casting
    // This would usually generate a warning, which is suppressed
    @SuppressWarnings("unchecked") private static <T extends Comparable<T>> void  merge(T[] a, int p, int mid, int q) {

	Object[] tmp = new Object[q-p+1]; 
	int i = p;
	int j = mid+1;
	int k = 0;
	while (i <= mid && j <= q) {
	    if (a[i].compareTo(a[j])<=0)
		tmp[k] = a[i++];
	    else
		tmp[k] = a[j++];
	    k++;
	}
	if (i <= mid && j > q) {
	    while (i <= mid) 
		tmp[k++] = a[i++];
	} else {
	    while (j <= q)
		tmp[k++] = a[j++];
	}
	for (k = 0; k < tmp.length; k++) {
	    a[k+p] = (T)(tmp[k]); // this is the line that would generate the warning 
	}/*
    
    public MergeSortGeneric(String[] anArray){
        a = anArray;
    }
    
    public void sort(){
        if(a.length <= 1)return;
        String[] first = new String[a.length / 2];
        String[] second = new String[a.length - first.length];
        
        //this pulls elements out of "a" and splits them recursivly into smaller arrays.
        System.arraycopy(a, 0, first, 0, first.length);//first half
        System.arraycopy(a, first.length, second, 0, second.length);//second half.
        
        MergeSortGeneric firstSorter = new MergeSortGeneric(first);//di
        MergeSortGeneric secondSorter = new MergeSortGeneric(second);
        
        firstSorter.sort();
        secondSorter.sort();
        
        merge(first, second);
    }
    
    private void merge(String[] first, String[] second){
        int iFirst = 0;
        int iSecond = 0;
        int j = 0;
        
        while(iFirst < first.length && iSecond < second.length){
            //if(first[iFirst] < second[iSecond]){//okay this is the most important part.
            //case sensitivity does matter for compareTo.
            if(first[iFirst].compareTo(second[iSecond]) <= -1){//less than
                a[j] = first[iFirst];
                iFirst++;
            }else{//else greater than.
                a[j] = second[iSecond];
                iSecond++; 
            }
            j++;
        }
        
        System.arraycopy(first, iFirst, a, j, first.length - iFirst);
        
        System.arraycopy(second, iSecond, a, j, second.length - iSecond);
                */
    }
    
    public static void main(String[] args) {
        RandomString test = new RandomString(6);
        String[] words = new String[15];
        for(int i = 0; i < 15; i++){
            words[i] = test.nextString();
        }
        //int[] a = ArrayUtil.randomIntArray(20, 100);
        Integer[] a = new Integer[5];
        a[0] = new Integer(2);
        a[1] = new Integer(1);
        a[2] = new Integer(4);
        a[3] = new Integer(3);
	a[4] = new Integer(-1);
           
	// T will be instantiated to Integer as a result of this call
        MergeSortGeneric.sort(words);
        
	// Print the result after the sorting
        for(int i = 0; i < words.length; i++)
            System.out.print(words[i].toString()+" ");
            //System.out.println(a[i].toString());
        
        System.out.println();
        /*
        System.out.println(Arrays.toString(words));
        MergeSortGeneric sorter = new MergeSortGeneric(words);
        sorter.sort();
        System.out.println(Arrays.toString(words));
        
        System.out.println("Test 2: ");
        String[] test2 = {"Steve", "John", "Beth", "Walt", "Henry", "Daniel", "Issac", "Emily", "Wolf", "Carey"};
        System.out.println(Arrays.toString(test2)+"\n");
        MergeSortGeneric sorter2 = new MergeSortGeneric(test2);
        sorter2.sort();
        System.out.println(Arrays.toString(test2));
                */
    }
    
}
