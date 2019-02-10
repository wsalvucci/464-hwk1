import java.util.Arrays;

public class ImprovedSort {

    private static void insertionSort(int[] arr, int n) {
        for (int i=1; i<n; i++) {
        	int x = arr[i];
        	int j = i-1;
        	for (j = i-1; j >=0 && arr[j] > x; j--)
        		arr[j+1] = arr[j];
        	arr[j+1] = x;
        }
    }
    
    private static void submerge(int[] arr, int low, int mid, int high) {
    	int n = high-low+1;
    	int b[] = new int[n];
    	int left = low, right = mid+1, bIdx = 0;
    	while (left <= mid && right <= high)
    		b[bIdx++] = (arr[left] <= arr[right]) ? arr[left++] : arr[right++];
    	while (left <= mid)
    		b[bIdx++] = arr[left++];
    	while (right <= high)
    		b[bIdx++] = arr[right++];
    	for (int k=0; k < n; k++)
    		arr[low+k] = b[k];
    }

    private static void merge(int[] arr, int low, int high) {
        if (low < high) {
        	int mid = (low+high) / 2;
        	merge(arr, low, mid);
        	merge(arr, mid+1, high);
        	submerge(arr, low, mid, high);
        }
    }

    private static void impSort(int[] arr) {
        /*
         * If the array isn't a power of 2, split the array to have one array the power of two
         * and run merge sort on the power of two array and insertion sort on the  smaller array.
         * 
         * If the power of two array is <=32, still run insertion sort
    	*/
    	int[] copyArr = arr;
    	boolean power2;
    	if ((copyArr.length != 0) && ((copyArr.length & (copyArr.length - 1)) == 0))
    		power2 = true;
    	else
    		power2 = false;
    	if (power2) {
    		if (copyArr.length <= 32)
    			insertionSort(copyArr, copyArr.length);
    		else
    			merge(copyArr, 0, copyArr.length-1);
    	} else {
    		System.out.println("Reducing to power of 2");
    		int endPoint = copyArr.length;
    		while (!((endPoint != 0) && ((endPoint & (endPoint - 1)) == 0)))
    			endPoint--;
    		
        	System.out.println("Running merge sort on array: ");
        	printArr(arr);
    		merge(copyArr, 0, endPoint-1);
    		
        	System.out.println("Running insertion sort on array: ");
        	printArr(arr);
    		insertionSort(copyArr, copyArr.length);
    	}
    	arr = copyArr;
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code -------- no need to touch
    public static void main(String[] args) {
        int[] testArr = new int[320];
        for (int i = 0; i < testArr.length; i++)
            testArr[i] = (int)(Math.random()*1000);
        System.out.println("Before Sorting:");
        printArr(testArr);

        impSort(testArr);
        //insertionSort(testArr, testArr.length);
        //merge(testArr, 0, testArr.length-1);

        System.out.println("After sorting:");
        printArr(testArr);
    }
}
