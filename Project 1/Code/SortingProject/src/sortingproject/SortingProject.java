/**
 * This is the starter code for the sorting project in CSCI 220. Note that there
 * are multiple files.
 *
 * This class is your driver class. It will control the flow of your program.
 * It will also contain your actual sorts.
 */
package sortingproject;

import java.util.ArrayList;

/**
 * @version Starter Code
 * @author Katie Timmerman
 */
public class SortingProject {

    //You should change this size as you are testing your program
    final static int LIST_SIZE = 5;

    /**
     * This is the method where your program begins running. Once this method is
     * done your program is done.
     */
    public static void main(String[] args) {
        testingMyCircularLL();
        testingAllSortingAlgorithms();

    }

    /**
     * This method tests the functionality of MyCircularLL
     */
    private static void testingMyCircularLL() {
        MyCircularLL list = new MyCircularLL();

        list.add(12);
        list.add(10);
        list.append(14);

        System.out.println("The list should be [10 12 14]: " + list);

        list.remove();
        System.out.println("The list should be [12 14]: " + list);

        list.add(8);
        System.out.println("The list should be [8 12 14]: " + list);

        System.out.println("The index of 8 should be 0: " + list.indexOf(8));
        System.out.println("The index of 14 should be 2: " + list.indexOf(14));
        System.out.println("The index of 9 should be -1: " + list.indexOf(9));

        list.remove(12);
        System.out.println("The list should be [8 14]: " + list);
        System.out.println("The index of 12 should be -1: " + list.indexOf(12));

        list.empty();
        System.out.println("The list should be []: " + list);
        System.out.println("The index of 8 should be -1: " + list.indexOf(8));

        list.add(6);
        list.append(9);
        list.add(4);
        list.add(3);
        list.add(2);
        list.append(10);

        System.out.println("The list should be [2 3 4 6 9 10]: " + list);
        list.remove(2);
        System.out.println("The list should be [3 4 6 9 10]: " + list);
        list.remove(10);
        System.out.println("The list should be [3 4 6 9]: " + list);

        System.out.println("The index of 2 should be -1: " + list.indexOf(2));
        System.out.println("The index of 10 should be -1: " + list.indexOf(10));
        System.out.println("The index of 3 should be 0: " + list.indexOf(3));
        System.out.println("The index of 9 should be 3: " + list.indexOf(9));
        System.out.println("The index of 9 should be 3: " + list.indexOf(9));

    }

    /**
     * Method tests all the sorting algorithms You can comment out sections to
     * only test sorts in certain categories
     */
    private static void testingAllSortingAlgorithms() {
        System.out.println("Testing Sorting Group 1: Radix Sort");
        testSort(1);
        System.out.println("\n\n\nTesting Sorting Group 2: Merge Sort");
        testSort(2);
        System.out.println("\n\n\nTesting Sorting Group 3: Quick Sort");
        testSort(3);

    }

    /**
     * This runs a sorting algorithm on an array, MyLinkedList, and a dynamic
     * array. It creates lists using createUnsorted<List>(). It then prints
     * lists. It will sort the lists using the specified group & re-print lists.
     *
     * @param group a number between 1 and 3 inclusive that represents the
     * sorting algorithm
     */
    private static void testSort(int group) {
        //create lists
        int[] listArray = createUnsortedListArray();
        MyCircularLL listLL = createUnsortedListLL();
        ArrayList<Integer> listDynamicArray = createUnsortedListDynamicArray();

        //print lists
        System.out.println("Prior to Sorting:");
        printArray(listArray);
        System.out.println(listLL);
        System.out.println(listDynamicArray);

        //sort lists using group
        if (group == 1) {
            sortListGroupOne(listArray);
            sortListGroupOne(listLL);
            sortListGroupOne(listDynamicArray);
        } else if (group == 2) {
            sortListGroupTwo(listArray);
            sortListGroupTwo(listLL);
            sortListGroupTwo(listDynamicArray);
        } else if (group == 3) {
            listArray = sortListGroupThree(listArray);
            sortListGroupThree(listLL);
            sortListGroupThree(listDynamicArray);
        } else {
            System.err.println("Error: no group " + group + " exists.");
        }

        //re-print Lists
        System.out.println("\nAfter Sorting:");
        printArray(listArray);
        System.out.println(listLL);
        System.out.println(listDynamicArray);

    }

    /**
     * Creates an array of LIST_SIZE that is filled with random integers between
     * 0 and 999.
     *
     * @return
     */
    private static int[] createUnsortedListArray() {
        int[] ary = new int[LIST_SIZE];
        for (int i = 0; i < LIST_SIZE; i++) {
            ary[i] = (int)(Math.random() * 1000);
        }
        return ary;
    }

    /**
     * Creates a MyCircularLL of LIST_SIZE that is filled with random integers
     * between 0 and 999.
     *
     * @return
     */
    private static MyCircularLL createUnsortedListLL() {
        MyCircularLL linkedList = new MyCircularLL();
        for (int i = 0; i < LIST_SIZE; i++) {
            int value = (int) (Math.random() * 1000);
            linkedList.add(value);
        }
        return linkedList;
    }

    /**
     * Creates an ArrayList of LIST_SIZE that is filled with random integers
     * between 0 and 999.
     *
     * @return
     */
    private static ArrayList<Integer> createUnsortedListDynamicArray() {
        ArrayList<Integer> dyAry = new ArrayList<Integer>();
        for (int i = 0; i < LIST_SIZE; i++) {
            int value = (int) (Math.random() * 1000);
            dyAry.add(value);
        }
        return dyAry;
    }

    /**
     * Calls the radix sort in order to make it recursive
     *
     * @param listArray
     */
    private static void sortListGroupOne(int[] listArray) {
        if (listArray.length == 0) {
            System.out.println("List is Empty");
            return;
        }
        int digitPlace = 0;
        radixSort(listArray, digitPlace);
    }

    /**
     * Sorts the list using an algorithm from group one.
     *
     * @param listLL
     */
    private static void sortListGroupOne(MyCircularLL listLL) {
        if (listLL == null) {
            System.out.println("List is empty");
        } else {
            listLL.radix(0);
        }
    }

    /**
     * Sorts the list using an algorithm from group one.
     *
     * @param listDynamicArray
     */
    private static void sortListGroupOne(ArrayList<Integer> listDynamicArray) {
        if (listDynamicArray.size() == 0) {
            System.out.println("List is Empty");
            return;
        }
        int digitPlace = 0;
        radixSort(listDynamicArray, digitPlace);
    }

    /**
     * Sorts the list using an algorithm from group two.
     *
     * @param listArray
     */
    private static void sortListGroupTwo(int[] listArray) {
        mergeSort(listArray, listArray.length);
    }

    /**
     * Sorts the list using an algorithm from group two.
     *
     * @param listLL
     */
    private static void sortListGroupTwo(MyCircularLL listLL) {
        listLL.mergeSort();
    }

    /**
     * Sorts the list using an algorithm from group two.
     *
     * @param listDynamicArray
     */
    private static void sortListGroupTwo(ArrayList<Integer> listDynamicArray) {
        if (listDynamicArray.isEmpty()) {
            return;
        }
        int left = 0;
        int right = listDynamicArray.size() - 1;
        sort(listDynamicArray, left, right);

    }

    /**
     * Sorts the list using an algorithm from group three.
     *
     * @param listArray
     */
    private static int[] sortListGroupThree(int[] listArray) {
        listArray = quickSortArr(listArray, 0, listArray.length - 1);
        return listArray;
    }

    /**
     * Sorts the list using an algorithm from group three.
     *
     * @param listLL
     */
    private static void sortListGroupThree(MyCircularLL listLL) {
        listLL.quickSort();
    }

    /**
     * Sorts the list using an algorithm from group three.
     *
     * @param listDynamicArray
     */
    private static void sortListGroupThree(ArrayList<Integer> listDynamicArray) {
        if (listDynamicArray.isEmpty()) {
            System.out.println("List is Empty");
            return;
        }
        int firstIndex = 0;
        int lastIndex = listDynamicArray.size() - 1;
        quickSort(listDynamicArray, firstIndex, lastIndex);
    }

    /**
     * prints out the contents of the array using System.out
     *
     * @param ary
     */
    private static void printArray(int[] ary) {
        System.out.print("[ ");
        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + " ");
        }
        System.out.println("]");
    }

    /**
     * Radix Sort for the Array List
     *
     * @param listArray
     * @param digitPlace
     */
    private static void radixSort(ArrayList<Integer> listArray, int digitPlace) {
        //checks to see if list is already sorted
        boolean sorted = true;
        for (int i = 0; i < listArray.size() - 1; i++) {
            if (listArray.get(i + 1) < listArray.get(i)) {
                sorted = false;
                break;
            }
        }
        //Base Case: Leaves the recurvise loop if sorted
        if (sorted) {
            return;
        }
        //Sets what digit we are checking
        int digitCheck = (int) (Math.pow(10, digitPlace));

        //Creates the bucket array where data is created in. 
        //Creates the second array as the orginalArray.length so that no matter what, if all of the values were the same, all values will be taken care of
        int[][] bucketArray = new int[10][listArray.size()];
        //Creates an array with the count of each occurence
        int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        //assigns each element of the list Array to it's location of the bucketArray
        for (int i = 0; i < listArray.size(); i++) {

            //Finds what spot to add
            int spotToAdd = (listArray.get(i) / digitCheck) % 10;

            //assigns the listArray item to the bucketArray
            bucketArray[spotToAdd][count[spotToAdd]] = (listArray.get(i));

            //increments the spot in the countArray to add too.
            count[spotToAdd]++;
        }
        //stores the spot of the listArray that the item needs to be inserted
        int indexToReinsert = 0;
        //loops over each item of the bucketArray
        for (int i = 0; i < bucketArray.length; i++) {
            //makes sure the count of any given item does not equal 0
            if (count[i] != 0) {
                //adds each item to from the bucketArray back to the listArray
                for (int j = 0; 0 < count[i]; j++) {
                    listArray.set(indexToReinsert, bucketArray[i][j]);
                    indexToReinsert++;
                    count[i]--;
                }
            }
        }
        //recursive call
        radixSort(listArray, digitPlace + 1);
    }

    /**
     * Sorts the list using a radix sort from group one.
     *
     * @param listArray
     */
    private static void radixSort(int[] listArray, int digitPlace) {
        //checks to see if list is already sorted
        boolean sorted = true;
        for (int i = 0; i < listArray.length - 1; i++) {
            if (listArray[i + 1] < listArray[i]) {
                sorted = false;
                break;
            }
        }
        //Base Case: Leaves the recurvise loop if sorted
        if (sorted) {
            return;
        }
        //Sets what digit we are checking
        int digitCheck = (int) (Math.pow(10, digitPlace));

        //Creates the bucket array where data is created in. 
        //Creates the second array as the orginalArray.length so that no matter what, if all of the values were the same, all values will be taken care of
        int[][] bucketArray = new int[10][listArray.length];
        //Creates an array with the count of each occurence
        int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        //assigns each element of the list Array to it's location of the bucketArray
        for (int i = 0; i < listArray.length; i++) {
            //Finds what spot to add
            int spotToAdd = (listArray[i] / digitCheck) % 10;
            //assigns the listArray item to the bucketArray
            bucketArray[spotToAdd][count[spotToAdd]] = (listArray[i]);
            //increments the spot in the countArray to add too.
            count[spotToAdd]++;
        }
        //stores the spot of the listArray that the item needs to be inserted
        int indexToReinsert = 0;
        //loops over each item of the bucketArray
        for (int i = 0; i < bucketArray.length; i++) {
            //makes sure the count of any given item does not equal 0
            if (count[i] != 0) {
                //adds each item to from the bucketArray back to the listArray
                for (int j = 0; 0 < count[i]; j++) {
                    listArray[indexToReinsert] = bucketArray[i][j];
                    indexToReinsert++;
                    count[i]--;
                }
            }
        }
        //recursive call
        radixSort(listArray, digitPlace + 1);

    }

    /**
     * Function that preforms the quickSort on the dynamicArray Is recursive
     *
     * @param dynamicArray
     * @param firstIndex
     * @param highIndex
     */
    private static void quickSort(ArrayList<Integer> dynamicArray, int firstIndex, int highIndex) {

        //Base case
        if (firstIndex < highIndex) {
            //finds the average of this list to set the pivot element faster
            double average = 0;
            for (int i = firstIndex; i < dynamicArray.size(); i++) {
                average = average + dynamicArray.get(i);
            }
            average = average / dynamicArray.size();
            double min = 0;
            int closestToAverageIndex = firstIndex;
            //finds the element closest to the average and stores the index in closeestToAverageIndex
            for (int i = firstIndex; i < dynamicArray.size(); i++) {
                double difference = dynamicArray.get(i) - average;
                if (i == firstIndex) {
                    min = difference;
                }
                if (Math.abs(difference) < min) {
                    min = difference;
                    closestToAverageIndex = i;
                }
            }
            //swaps the first element with the closest so that the pivot isn't as extreme
            int newFirst = dynamicArray.get(closestToAverageIndex);
            dynamicArray.set(closestToAverageIndex, dynamicArray.get(firstIndex));
            dynamicArray.set(firstIndex, newFirst);

            //sets up the pivot, and indexTrackers
            int pivotElement = dynamicArray.get(firstIndex);
            int lowerTracker = firstIndex + 1;
            int upperTracker = highIndex;

            while (lowerTracker < upperTracker) {
                //finds where lower tracker is greater than pivot
                if (dynamicArray.get(lowerTracker) >= pivotElement) {
                    //iterates until upper tracker is less than pivot or until the upper and the lower meet
                    while (dynamicArray.get(upperTracker) > pivotElement && lowerTracker < upperTracker) {
                        upperTracker--;
                    }
                    //swaps the two elements, one higher and one lower than the pivot element once they are found
                    int temp = dynamicArray.get(lowerTracker);
                    dynamicArray.set(lowerTracker, dynamicArray.get(upperTracker));
                    dynamicArray.set(upperTracker, temp);
                    lowerTracker++;
                    upperTracker--;
                } else {
                    lowerTracker++;
                }
            }
            //sets a temporary upperTracker
            int tempUpperTracker = upperTracker;

            //moves upper tracker until it is less than the pivot
            while (dynamicArray.get(upperTracker) > pivotElement) {
                upperTracker--;
                //Condition that doesn't let the tracker go outside of the list if no item is found that is greater than the pivot element.
                if (upperTracker == -1) {
                    upperTracker = tempUpperTracker;
                    break;
                }
            }
            //swaps the pivot element
            dynamicArray.set(firstIndex, dynamicArray.get(upperTracker));
            dynamicArray.set(upperTracker, pivotElement);

            //Stops and infinite recursion by increasing the upper tracker by one
            if (upperTracker == firstIndex) {
                upperTracker++;
            }
            //recursive calls on the halfs
            quickSort(dynamicArray, firstIndex, upperTracker - 1);
            quickSort(dynamicArray, upperTracker, highIndex);

        }
    }

    /**
     * Recursive Function that sorts the list down into it's individual parts
     *
     * @param listDynamicArray
     * @param left
     * @param right
     */
    private static void sort(ArrayList<Integer> listDynamicArray, int left, int right) {
        //Checks to see if indexes are allowed.
        //If the left and right are equal, then the list would be sorted as the size would be one
        if (left < right) {
            int middle = (left + right) / 2;

            //Runs the sort method again so that each half breaks down again
            sort(listDynamicArray, left, middle);
            sort(listDynamicArray, middle + 1, right); //is middle +1 so that the middle element is not counted in each half.

            //Will run the merge function on recursively on the halfs given.
            merge(listDynamicArray, left, middle, right);
        }
    }

    /**
     * Tears the list apart and then re-merges it in the listDynamic Array
     *
     * @param listDynamicArray
     * @param left
     * @param middle
     * @param right
     */
    private static void merge(ArrayList<Integer> listDynamicArray, int left, int middle, int right) {
        //Sets the size of each array to split
        int sizeOne = middle - left + 1; //+1 to make the front array one larger than the other one just in case there are an odd rumber.
        int sizeTwo = right - middle;

        //temporary arraylists
        ArrayList<Integer> listOne = new ArrayList<>();
        ArrayList<Integer> listTwo = new ArrayList<>();

        //Sets listTwo to the first half of the arrayList
        for (int i = 0; i < sizeOne; ++i) {
            listOne.add(listDynamicArray.get(left + i));
        }
        //Sets listTwo to the second half of the arrayList
        for (int i = 0; i < sizeTwo; ++i) {
            listTwo.add(listDynamicArray.get(i + middle + 1));
        }

        //Sets the index to insert
        int indexOfOne = 0;
        int indexOfTwo = 0;

        //Sets the spot to add to the left index
        int indexToAdd = left;

        //Checks to see if the loop is still within the listOne and listTwo indexes
        while (indexOfOne < sizeOne && indexOfTwo < sizeTwo) {
            //Comparison of the first element of listOne and listTwo
            if (listOne.get(indexOfOne) <= listTwo.get(indexOfTwo)) {
                //replaces the listDynamic spot at the indexToAdd from listOne
                listDynamicArray.set(indexToAdd, listOne.get(indexOfOne));
                indexOfOne++;
            } else {
                //replaces the listDynamic spot at the indexToAdd from listTwo
                listDynamicArray.set(indexToAdd, listTwo.get(indexOfTwo));
                indexOfTwo++;
            }
            indexToAdd++;
        }
        //Loop above could be stopping early so these are here to add any possible remaining items from both temportary lists
        while (indexOfOne < sizeOne) {
            listDynamicArray.set(indexToAdd, listOne.get(indexOfOne));
            indexOfOne++;
            indexToAdd++;
        }
        while (indexOfTwo < sizeTwo) {
            listDynamicArray.set(indexToAdd, listTwo.get(indexOfTwo));
            indexOfTwo++;
            indexToAdd++;
        }
    }

    /**
     * The method takes the pivot value when it calls on the findPivot method
     * which gets the average of the array and creates a value. Using that value
     * it then sorts the list into its proper array lower or upper. Continually
     * updating the pivot value and checking the list. Returns array.
     *
     * @param arr
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static int[] quickSortArr(int arr[], int lowerBound, int upperBound) {

        if (upperBound - lowerBound < 1) {
            return arr;
        }
        int pivot = findPivot(arr, lowerBound, upperBound);
        int[] newArray = new int[arr.length];
        /**
         * for loop for the new array.
         */
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        int nextPlaceInLower = lowerBound;
        int nextPlaceInHigher = upperBound;
        /**
         * for loop that compares the value of the pivot to the value in the
         * index and puts it in the correct array.
         */
        for (int i = lowerBound; i <= upperBound; i++) {
            if (arr[i] <= pivot) {
                newArray[nextPlaceInLower] = arr[i];
                nextPlaceInLower++;
            } else {
                newArray[nextPlaceInHigher] = arr[i];
                nextPlaceInHigher--;
            }
        }
        int mid = nextPlaceInHigher;
        if (mid - lowerBound >= 1) {
            newArray = quickSortArr(newArray, lowerBound, mid);
        }
        if (upperBound - mid >= 1) {
            newArray = quickSortArr(newArray, mid + 1, upperBound);
        }
        return newArray;
    }

    /**
     * Finds the pivot value by making a seperate value from the array and uses
     * that to compare the values.
     *
     * @param arr
     * @param lowerBound
     * @param upperBound
     * @return
     */
    private static int findPivot(int[] arr, int lowerBound, int upperBound) {
        int sum = 0;
        for (int i = lowerBound; i <= upperBound; i++) {
            sum = sum + arr[i];
        }
        return sum / (upperBound - lowerBound + 1);
    }

    /**
     * In this method it is creating the arrays that the values will be stored
     * in. Spliting the values into the correct array based on the size. Based
     * off of the base case.
     *
     * @param left_arr
     * @param right_arr
     * @param arr
     * @param left_size
     * @param right_size
     */
    public static void merge(int[] left_arr, int[] right_arr, int[] arr, int left_size, int right_size) {

        int i = 0, left = 0, right = 0;
        while (left < left_size && right < right_size) {

            if (left_arr[left] < right_arr[right]) {
                arr[i++] = left_arr[left++];
            } else {
                arr[i++] = right_arr[right++];
            }
        }
        while (left < left_size) {
            arr[i++] = left_arr[left++];
        }
        while (right < right_size) {
            arr[i++] = right_arr[right++];
        }
    }

    /**
     * This is the main method this checks the values and calls the arr that has
     * been and has yet to be sorted. the values
     *
     * @param arr
     * @param len
     */
    public static void mergeSort(int[] arr, int len) {
        if (len < 2) {
            return;
        }

        int mid = len / 2;
        int[] left_arr = new int[mid];
        int[] right_arr = new int[len - mid];

        int k = 0;
        for (int i = 0; i < len; ++i) {
            if (i < mid) {
                left_arr[i] = arr[i];
            } else {
                right_arr[k] = arr[i];
                k = k + 1;
            }
        }
        mergeSort(left_arr, mid);
        mergeSort(right_arr, len - mid);
        merge(left_arr, right_arr, arr, mid, len - mid);
    }
}
