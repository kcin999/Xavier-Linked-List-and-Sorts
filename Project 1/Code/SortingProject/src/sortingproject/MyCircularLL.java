/**
 * This class creates a simple linked list object.
 */
package sortingproject;

/**
 * @version Starter Code
 * @author Katie Timmerman
 */
class MyCircularLL {

    private Node tail;

    /**
     * creates an empty list
     */
    public MyCircularLL() {
        tail = null;
    }

    /**
     * Adds value to the start of the list
     *
     * @param value
     */
    public void add(int value) {
        if (this.tail == null) {
            Node addedNode = new Node(value);
            this.tail = addedNode;
            addedNode.next = addedNode;
        } else {
            Node addedNode = new Node(value, this.tail.next);
            this.tail.next = addedNode;
        }
    }

    /**
     * Adds value to the end of the list
     *
     * @param value
     */
    public void append(int value) {
        if (this.tail == null) {
            Node addedNode = new Node(value);
            this.tail = addedNode;
            addedNode.next = addedNode;
        } else {
            Node addedNode = new Node(value, this.tail.next);
            Node currentNode = this.tail.next;
            while (currentNode != this.tail) {
                currentNode = currentNode.next;
            }
            this.tail = addedNode;
            currentNode.next = this.tail;
        }
    }

    /**
     * removes the first occurrence of item from the list.
     *
     * @param item
     */
    public void remove(int item) {
        if (this.tail == null) {
            System.out.println("List is empty");
        } else {
            Node currentNode = this.tail.next;
            Node previousNode = this.tail;
            while (currentNode != this.tail) {
                if (currentNode.value == item) {
                    previousNode.next = currentNode.next;
                    return;
                } else {
                    currentNode = currentNode.next;
                    previousNode = previousNode.next;
                }
            }
            if (item == this.tail.value) {
                previousNode.next = this.tail.next;
                this.tail = previousNode;
            } else {
                System.out.println("Value not in list");
            }
        }
    }

    /**
     * Removes the first value in the list
     */
    public void remove() {
        if (this.tail == this.tail.next) {
            this.tail = null;
            return;
        }
        this.tail.next = this.tail.next.next;
    }

    /**
     * Empties the list
     */
    public void empty() {
        this.tail = null;
    }

    /**
     * method that returns the size of the LinkedList return size returns -1 if
     * list is empty
     */
    public int getSize() {
        if (this.tail == null) {
            return -1;
        }
        int size = 0;
        Node current = this.tail.next;
        while (current != this.tail) {
            current = current.next;
            size++;
        }
        return size + 1;
    }

    /**
     * Returns the value at the index of the node. If list is empty returns -1
     *
     * @param index
     * @return
     */
    public int valueAt(int index) {
        int value;
        if (this.tail == null) {
            return -1;
        }
        Node current = this.tail.next;
        int currentIndex = 0;

        while (currentIndex < index) {

            currentIndex++;
            current = current.next;
        }
        value = current.value;

        return value;
    }

    /**
     * Replaces the value at a the index inputted with the value inputted
     *
     * @param index
     * @param value
     */
    public void replace(int index, int value) {

        Node current = this.tail.next;
        Node previous = this.tail;
        int currentIndex = 0;
        while (currentIndex < index) {
            currentIndex++;
            current = current.next;
            previous = previous.next;
        }

        current.value = value;

    }

    /**
     * Returns the location of the first occurrence of the value in the list.
     * Returns 0 if it is the first item. return -1 if it isn't in the list
     *
     * @param value
     */
    public int indexOf(int value) {
        if (this.tail == null) {
            System.out.println("List is empty");
            return -1;
        } else {
            int itemIndex = -1;
            int listLength = -1;
            Node currentNode = this.tail.next;
            while (currentNode != this.tail) {
                listLength++;
                if (currentNode.value == value) {
                    itemIndex = listLength;
                }
                currentNode = currentNode.next;
            }
            if (this.tail.value == value) {
                //tail is on the end always
                return listLength + 1;
            }
            return itemIndex;
        }
    }

    /**
     * Recursive function for the radix sort on the Linked List
     *
     * @param digitPlace
     */
    public void radix(int digitPlace) {

        if (this.isSorted() == true) {
            return;
        }

        //Initilizes count and bucketarray
        int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        MyCircularLL bucketArray[] = new MyCircularLL[10];

        //sets each element of the bucketarray to a circle List
        for (int i = 0; i < bucketArray.length; i++) {
            bucketArray[i] = new MyCircularLL();
        }
        //Sets the current Node
        Node currentNode = this.tail.next;
        //Defines what place to check based on digitPlace
        int digitCheck = (int) (Math.pow(10, digitPlace));
        //Adds the list to the bucketList
        while (this.tail != currentNode) {
            int spotToAdd = (currentNode.value / digitCheck) % 10;
            bucketArray[spotToAdd].append(currentNode.value);
            count[spotToAdd]++;
            currentNode = currentNode.next;
        }
        //adds the tail to the list
        int spotToAdd = (this.tail.value / digitCheck) % 10;
        bucketArray[spotToAdd].append(this.tail.value);
        count[spotToAdd]++;

        this.empty();
        //loops over each item of the bucketArray
        for (int i = 0; i < bucketArray.length; i++) {
            //makes sure the count of any given item does not equal 0
            if (count[i] != 0) {
                //adds each item to from the bucketArrya back to the listArray
                currentNode = bucketArray[i].tail.next;
                while (currentNode != bucketArray[i].tail) {
                    this.append(currentNode.value);
                    currentNode = currentNode.next;
                }
                this.append(bucketArray[i].tail.value);
            }
        }
        this.radix(digitPlace + 1);

    }

    /**
     * Returns the middle Node of the list started at parameter start and ending
     * at parameter end
     *
     * @param start
     * @param end
     * @return
     */
    private Node middle(Node start, Node end) {

        if (start.next == null) {
            return start;
        }
        //finds middle node
        Node firstTracker = start;
        Node secondTracker = start;

        /*Loop goes through the linked list until the second tracker reaches the end
          Second tracker moves at twice the speed of the first one so I can return the first
          tracker when the secondTracker reaches the end
          finds middle node*/
        firstTracker = start;
        secondTracker = start;
        while ((secondTracker.next != end.next && secondTracker.next.next != end.next)) {

            firstTracker = firstTracker.next;
            secondTracker = secondTracker.next.next;
        }
        return firstTracker;
    }

    /**
     * Returns the node at the given index in the list
     *
     * @param index
     * @return
     */
    private Node nodeAtIndex(int index) {
        Node currentNode = this.tail.next;
        int currentIndex = 0;
        //Simply loops over the list until currentIndex = @param index inputted
        while (currentIndex < index) {
            currentNode = currentNode.next;
            currentIndex++;
        }
        return currentNode;
    }

    /**
     * Checks to see if Linked LIst is sorted Returns True if list is sorted
     * Returns False if it is unsorted
     *
     * @return
     */
    public boolean isSorted() {
        //Checks to see if list is stored
        Node cur = this.tail.next.next;
        Node prev = this.tail.next;
        boolean sorted = true;

        while (cur != tail) {
            if (prev.value > cur.value) {
                sorted = false;
                break;
            }
            cur = cur.next;
            prev = prev.next;
        }
        if (prev.value >= tail.value) {
            sorted = false;
        }
        return sorted;
    }

    /**
     * This is the method called when a MyLinkList is passed to System.out It
     * determines what is printed
     *
     * @return
     */
    @Override
    public String toString() {
        if (tail == null) {
            return "[]";
        }
        String toPrint = "[ ";
        Node cur = tail.next;
        while (cur != tail) {
            toPrint += cur.value + " ";
            cur = cur.next;
        }
        toPrint += cur.value + "]";
        return toPrint;
    }

    /**
     * This is a Node class to be used in your linked list.
     */
    private static class Node {

        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }

        public Node(int value, Node next) {
            this.next = next;
            this.value = value;
        }
    }

    //Merge Sort  
    public void mergeSort() {
        //Makes sure the list is not empty
        if (this.tail == null) {
            System.out.println("Empty List");
            return;
        }
        MyCircularLL temp = sort(this);
        this.tail = temp.tail;
    }

    /**
     * Recursive method that breaks down the Circular Linked List and merges the
     * two halves
     *
     * @param list
     * @return
     */
    private MyCircularLL sort(MyCircularLL list) {
        //checks to see if it is empty
        if (list.tail == null) {
            return list;
        }
        Node left = list.tail.next;
        Node right = list.tail;

        //Base case check
        if (left != right) {
            Node middleNode = middle(left, right);
            //splits the list in half, centered at the middle Node
            MyCircularLL leftList = new MyCircularLL();
            MyCircularLL rightList = new MyCircularLL();
            while (left != middleNode.next) {
                leftList.append(left.value);
                left = left.next;
            }
            middleNode = middleNode.next;
            while (middleNode != right) {
                rightList.append(middleNode.value);
                middleNode = middleNode.next;
            }
            //adds the tail node since this would not get caught
            rightList.append(right.value);
            //recursive calls
            leftList = this.sort(leftList);
            rightList = this.sort(rightList);
            //calls merge list
            MyCircularLL merged = merge(leftList, rightList);
            return merged;
        } else {
            return list;
        }
    }

    /**
     * Method that merges the two half of the lists, left and right (both
     * parameters) together and returns the sorted list
     *
     * @param left
     * @param right
     * @return
     */
    private MyCircularLL merge(MyCircularLL left, MyCircularLL right) {
        MyCircularLL merged = new MyCircularLL();
        //merges the two halves into merged
        while (left.tail != null && right.tail != null) {
            if (left.tail.next.value < right.tail.next.value) {
                merged.append(left.tail.next.value);
                left.remove();
            } else {
                merged.append(right.tail.next.value);
                right.remove();
            }

        }
        //adds any remaining values
        while (left.tail != null) {
            merged.append(left.tail.next.value);
            left.remove();
        }
        while (right.tail != null) {
            merged.append(right.tail.next.value);
            right.remove();
        }
        return merged;
    }

    /**
     * Method that begins the quick sort of the linked list
     */
    public void quickSort() {
        quickSort(this.tail.next, this.tail);
    }

    /**
     * Recursive function that sorts the list based on pivot elements until the
     * list is sorted
     *
     * @param left
     * @param right
     */
    private void quickSort(Node left, Node right) {
        int leftIndex = this.indexOf(left.value);
        int rightIndex = this.indexOf(right.value);

        //Base Case
        if (leftIndex < rightIndex) {
            //Finds what the average of the list is
            double average = 0;
            Node currentNode = this.tail.next;
            while (currentNode != tail) {
                average = average + currentNode.value;
                currentNode = currentNode.next;
            }
            average = average + this.tail.value;
            average = average / this.getSize();
            double min = 0;
            int closestToAverageIndex = this.indexOf(left.value);
            //finds the closest value in the list to the average
            for (int i = this.indexOf(left.value); i < this.getSize(); i++) {
                double difference = this.valueAt(i) - average;
                if (i == this.indexOf(left.value)) {
                    min = difference;
                }
                if (Math.abs(difference) < min) {
                    min = difference;
                    closestToAverageIndex = i;
                }
            }
            //swaps the pivot element with the one closest to the average
            int newLeft = this.valueAt(closestToAverageIndex);
            this.replace(closestToAverageIndex, left.value);
            this.replace(this.indexOf(left.value), newLeft);

            //defines the right and left pointers as well as the pivot
            int pivotElement = this.valueAt(this.indexOf(left.value));
            int leftTracker = this.indexOf(left.next.value);
            int rightTracker = this.indexOf(right.value);

            while (leftTracker < rightTracker) {
                //checks if a value is less than or equal to the pivot element
                if (this.valueAt(leftTracker) >= pivotElement) {
                    //finds a value greater than the pivot
                    while (this.valueAt(rightTracker) > pivotElement && leftTracker < rightTracker) {
                        rightTracker--;
                    }
                    //swaps the elements that is higher and lower.
                    int temp = this.valueAt(leftTracker);
                    this.replace(leftTracker, this.valueAt(rightTracker));
                    this.replace(rightTracker, temp);
                    leftTracker++;
                    rightTracker--;
                } else {
                    leftTracker++;
                }
            }
            //finds where the element should go in the list
            int temprightTracker = rightTracker;
            while (this.valueAt(rightTracker) > pivotElement) {
                rightTracker--;
                if (rightTracker == -1) {
                    rightTracker = temprightTracker;
                    break;
                }
            }

            //swaps the pivot element to where it goes in the list
            this.replace(this.indexOf(pivotElement), this.valueAt(rightTracker));
            this.replace(rightTracker, pivotElement);

            //Stops infinite recursion and keeps the values different if the right tracker is in the same spot as the pivot
            if (rightTracker == this.indexOf(pivotElement)) {
                rightTracker++;
            }

            //recursive calls
            quickSort(left, this.nodeAtIndex(rightTracker - 1));
            quickSort(this.nodeAtIndex(rightTracker), right);

        }
    }

}
