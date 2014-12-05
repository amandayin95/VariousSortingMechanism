import java.util.ArrayList;
import java.util.Arrays;

/**
 * a class for several sorting algorithms
 * 
 * @author CS60CK
 *
 */
public class Sort {
  
  
  /**
   * minsort - O(N**2) sorting (also: selection sort)
   * @param A, an array of ints, to be sorted in place
   */
  public static void minsort(int[] A) {
	int lengthOfArray = A.length;
	if (lengthOfArray== 0);// if empty array, then minHelperFindMax will freak out
	else{
	int max = minHelperFindMax(A);
    int[] result = new int[lengthOfArray];
    
    for (int i = 0; i<lengthOfArray; i++){
    	int min = A[0]; //suppose min is A[0] 
    	int minIndex = 0;//suppose minIndex = 0
    	for (int m =0; m<lengthOfArray; m++){
  		  if (A[m]<min){
  			  min = A[m];
  			  minIndex = m;
  		  }
    	}
    	A[minIndex] = max+1;
     	result[i] = min;
     	if (i == lengthOfArray-1){
     		for (int n =0; n <lengthOfArray; n++){
     			A[n]= result[n];
     		}
     	}
     } 
	}
  }
  /*
   * minHelper, finds the Minimum of a Array
   * 
   * NOT USING IT!
   */
//  public static int minHelperFindMin(int[] A){
//	  int lengthOfArray = A.length;
//	  int min = A[0]; //suppose min is A[0] 
//	  int minIndex = 0;//suppose minIndex = 0
//	  for (int i =0; i<lengthOfArray; i++){
//		  if (A[i]<min){
//			  min = A[i];
//			  minIndex = i;
//		  }
//	  }
//	  int[] removeList = new int[lengthOfArray-1];
//	  
//	  return min;
//  }
  
  /*
   * helper function for minsort
   * helps to find the max of the array
   * 
   */
	public static int minHelperFindMax(int[] A) {
		int lengthOfArray = A.length;
		int max = A[0];
		for (int i = 0; i < lengthOfArray; i++) {
			if (A[i] > max) {
				max = A[i];
			}
		}
		return max;
	}
  
  
  /**
   * bucketsort - O(N) sorting, sort of...
   * @param A, an array of ints, to be sorted in place
   */
	public static void bucketsort(int[] A) {

		int lengthOfArray = A.length;
		if (lengthOfArray == 0)
			;// if empty array, then bucketHelperFindMinMax will freak out
		else {
			int[] MinMax = bucketHelperFindMinMax(A); // helper to find [min, max]
			int MinOfArray = MinMax[0];// first element is min
			int MaxOfArray = MinMax[1];// second element is max
			int lengthOfBucket = MaxOfArray - MinOfArray + 1; //length of bucket
			int[] bucket = new int[lengthOfBucket]; //create buckets!
			for (int i = 0; i < lengthOfArray; i++) {
				bucket[A[i] - MinOfArray]++;
			}
			//now the bucket is completely constructed!!!
			
			//use the bucket!
			
			int refIndexA = 0;
			for (int m = 0; m < lengthOfBucket; m++) {
				int times = bucket[m];
				if (times == 0)
					; //deals with 0 times of occurrence
				else {
					for (int refTimes = 1; refTimes <= times; refTimes++) {
						refIndexA += 1;
						A[refIndexA - 1] = m + MinOfArray;
					}
				}
			}
		}
	}

/*
 * help find both min and max!
 * take in an array, (non-empty) 
 * return an array of two elements, the first one is the min, second one max
 */
  public static int[] bucketHelperFindMinMax(int[] A) {
	  	int[] result = new int[2];
		int lengthOfArray = A.length;
		int max = A[0];
		int min = A[0];
		for (int i = 0; i < lengthOfArray; i++) {
			if (A[i] > max) {
				max = A[i];// find min
			}
			if (A[i]<min){
				min = A[i];// find max
			}
		}
		result[0] = min;
		result[1] = max;
		return result;
	}
  /**
   * this Heap class implements a binary min-heap
   * it uses Java's ArrayList<Integer> as the storage for
   *    its data, named this.heapdata
   *   
   * Here's the official ArrayList documentation:
   *   docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
   *   
   * Here are all of the ArrayList methods I used:
   *  - heapdata.size() ~ the # of nodes currently in the heap
   *  - heapdata.set( i, val ) ~ sets element i to have a value of val
   *  - heapdata.get( i ) ~ returns the value at index i
   *  
   * Here are two methods used in the provided code below (not in percolate):
   *  - heapdata.remove( i ) ~ removes the element at index i
   *  - heapdata.add( val ) ~ adds one element to the end of the heap
   *                          it has value val
   *                          after this call, heapdata.size() has increased by 1
   *                          
   * At all times (except just before calling percolate_up and percolate_down),
   * the heap property should be true:
   *   Every node is less than (or equal to) both its children
   */
  static class Heap
  {
    /**
     * this.heapdata is the storage for our heap
     * 
     * indexing reminders:
     * 
     * for an element at index i:
     *    its parent is at index (i-1)/2
     *    its leftchild is at index 2*i+1
     *    its rightchild is at index 2*i+2
     */
    public ArrayList<Integer> heapdata;
    
    /**
     * constructor for our Heap class
     */
    public Heap() {
      this.heapdata = new ArrayList<Integer>(); // construct our heapdata
    }
    
    /**
     * our toString method, delegating to the ArrayList toString method
     * @return the string representation of this.heapdata
     */
    public String toString() {
      return "" + this.heapdata; // Uses ArrayList's toString method
    }
    
    /**
     * this method also delegates to the ArrayList size method
     * @return the number of nodes currently in the Heap
     */
    public int size() {
      return this.heapdata.size(); // Uses ArrayList's size method
    }
    
    /**
     * percolate_up should adjust the last element of the heap
     *    by swapping it with its parent, grandparent, ...
     *    until the heap property is satisfied
     */
    private void percolate_up() {
      int lasti = this.heapdata.size()-1;
      for (lasti = this.heapdata.size()-1; (lasti-1)/2>=0; lasti = (lasti -1)/2){
    	 int child =  this.heapdata.get(lasti);
    	 int parent = this.heapdata.get((lasti-1)/2);
    	  if (child<parent){
    		  this.heapdata.set(lasti, parent);
    		  this.heapdata.set((lasti-1)/2, child);
    	  }
    	  else{
    		  break;
    	  }
      }
    }
    
    /**
     * percolate_down should adjust the first element of the heap
     *    by swapping it with its smaller child, grandchild, ...
     *    until the heap property is satisfied
     */
    private void percolate_down() {
    	int size = this.heapdata.size();
    	int i = 0;
    	while ((2*i+1)<size){
    		int parent = this.heapdata.get(i);
    		
    		
    		if (2*i+2<size){//have both child
    			int leftChild = this.heapdata.get(2*i+1);
    			int rightChild = this.heapdata.get(2*i+2);
    			
    			if (leftChild<=rightChild){//left child smaller
    				if (parent > leftChild){// parent bigger
    					this.heapdata.set(i, leftChild);
    					this.heapdata.set(2*i+1,parent);
    					i = 2*i +1;
    				}
    				else{
    					break;
    				}
    			}
    			else if (leftChild>rightChild){//right child smaller
    				if (parent > rightChild){// parent bigger
    					this.heapdata.set(i, rightChild);
    					this.heapdata.set(2*i+2,parent);
    					i = 2*i +2;
    				}
    				else{
    					break;
    				}
    			}
    		}
    		else if (2*i+2>=size){
    			int leftChild = this.heapdata.get(2*i+1);
    			if (parent > leftChild){// parent bigger
					this.heapdata.set(i, leftChild);
					this.heapdata.set(2*i+1,parent);
    			}
    			else{
    				break;
    			}
    		}
    		
    		
    		
    	}
    }
    
    /**
     * inserts an element to the heap and maintains the heap property
     * @param val, the value to be inserted to the heap
     */
    public void heap_insert(int val) {
      this.heapdata.add(val); // adds to the back of the heapdata
      this.percolate_up();  // fixes the heap up!
    }
    
    /**
     * removes and returns the minimum value in the heap
     *   it makes sure the heap property is maintained, too
     * @return the smallest value that was in the heap
     */
    public int heap_remove_min() {
      if (this.size() <= 0)  {
        System.out.println("Error: can not remove from an empty heap.");
        return -1;
      }
      // get the min, remove the last element, and then fix things up...
      int min = heapdata.get(0);
      int end = heapdata.remove(heapdata.size()-1); // size is now one smaller
      // percolate if there are elements still in the heap!
      if (heapdata.size() > 0) {
        this.heapdata.set(0, end);
        this.percolate_down(); 
      }
      // now, we return that min
      return min;
    }
  }

  
  /**
   * heapsort sorts by creating a min-heap and then removing
   *     consecutive minimum elements until it's done!
   * @param A, the array to be sorted
   */
  public static void heapsort(int[] A) {
    // create our heap, H
    Heap H = new Sort.Heap();
    // put all of the elements into the heap
    for (int n:A) {
      H.heap_insert(n);
      //System.out.println("Add " + n + " to get " + H);
    }
    // take the min values out of the heap
    for (int i=0 ; i<A.length ; ++i) {
      A[i] = H.heap_remove_min();
      //System.out.println("Remove first " + A[i] + " to leave " + H);
    }
    //System.out.println("A is now " + Arrays.toString(A));
    return;
  }

  /**
   * main, used for less formal tests than the JUnit ones...
   * @param args, the command-line inputs, not used here
   */
  public static void main(String[] args) {
    int[] A = { 6, 2, 9, 3, 4, 8, 0, 1, 7, 5 };
    //int[] A = { 9, 9, 0, 9, 9, 0, 0, 0, 9, 0, 7 };
    //System.out.println("A is " + Arrays.toString(A));
    //bucketsort( A );
    //heapsort( A );
    minsort(A);
   // System.out.println("A is " + Arrays.toString(A));
  }
  
  
  
  /**
   * extra credit #2 - quicksort
   *   sorts in place - and without add'l arrays!
   * @param A, an array of ints
   */
  public static void quicksort(int[] A) {
	  int length = A.length;
	  quicksortHelp(A,0,length-1);
	  }
    

  
	public static void quicksortHelp(int[] A, int low, int high) {
		if (low > high)// deals with out of bound errors
			;

		else if (high == low)// base case #1
			;
		else if (high - low == 1) { // base case #2
			if (A[high] >= A[low])
				; // right order
			else { // switch
				int big = 0;
				int small = 0;
				big += A[low];
				small += A[high];
				A[low] = small;
				A[high] = big;
			}
		}
		// ////////////////other cases!//////////////////
		else {
			int pivot = 0; // the pivot
			pivot += A[low];
			int countLessThan = 0; // this int helps us know where to put the
									// pivot
			for (int i = low; i <= high; i++) {
				if (A[i] < pivot) {
					countLessThan++;
				}
			}

			int originalmiddle = A[low + countLessThan];
			A[low + countLessThan] = pivot;// set the pivot to its place
			A[low] = originalmiddle;// switch whatever is there with pivot

			int lessThanIndex = 0;
			int ref1 = 0;
			int ref2 = 0;

			for (int m = low; m <= high; m++) {
				if (A[m] < pivot) {// if A[m] less than pivot,
					ref1 += A[low + lessThanIndex];// remember the number
					ref2 += A[m];// remember
					A[low + lessThanIndex] = ref2;// switch
					A[m] = ref1;// switch
					ref1 = 0;// reset refs
					ref2 = 0;// reset refs
					lessThanIndex++;
					// index of the "less than" part increments
				}

				// otherwise do nothing, BECAUSE
				// those bigger than the pivot will all be switched to the end
				// of the array
			}

			// now recurse with the less than part and the more than part
			quicksortHelp(A, low, low + lessThanIndex);
			quicksortHelp(A, low + lessThanIndex + 1, high);
		}
	}
	
	
	
	
/*
 * Stoogesort works because even if in the worst case the last 1/3 is the smallest, 
 * it will get put to the last half of the first2/3;
 * then int the new first, the first half and the last half will compare;
 *
 * in short, for [A,B,C], each of the part gets compared with the other two parts
 * 
 * Runtime: polynomial
 * 
 */
  
  
  /*
   * recurse Stooge Sort
   * 
   * Never use new int[], 
   * (int) start and end denotes the staring and ending index to be stooge sorted
   * 
   */
  public static void StoogeSortHelp(int[] A, int start, int end){
	  int length = end - start+1;
	  int lengthOfArray = A.length;
		if (lengthOfArray== 0);// if empty array, then minHelperFindMax will freak out
		System.out.println("start? "+start);
		System.out.println("end? " + end);
		if(end<=start);//base case#1
		else if(end - start == 1){// base case #2
			if(A[end]>= A[start]);//right order
			else{//switch!
				int big = A[start];
				int small = A[end];
				A[start]= small;
				A[end]= big;
			}
		}
		else{//longer arrays!
			int mod = length%3;
			if(mod == 0){// if array can be equally divided to three parts
				
				StoogeSortHelp(A, start, 2*length/3-1);
				StoogeSortHelp(A,length/3, end);
				StoogeSortHelp(A,start,2*length/3-1);	
				
			}
			else if (mod!=0){// if not
				int shortPart = length/3;
				System.out.println(Arrays.toString(A));
				System.out.println("shortpart "+shortPart);
				System.out.println("start "+start);
				StoogeSortHelp(A,start,2*shortPart);// first part contains a short one and a long one
				StoogeSortHelp(A,shortPart,end);//the rest
				StoogeSortHelp(A,start,2*shortPart);
				
			}
			
		}
  }
  
	public static void StoogeSort(int[] A) {
		StoogeSortHelp(A, 0, A.length - 1);
	}
}



