package listenviertetestat;

import java.util.NoSuchElementException;

public class CircularLinkedList {
	
	 private Node head;
	    private int size;

	    private static class Node {
	        private int value;
	        private Node prev;
	        private Node next;

	        public Node(int value) {
	            this.value = value;
	        }
	    }
	    
	      

	    public boolean isEmpty() {
	        return size == 0;
	    }

	    public int getSize() {
	        return size;
	    }

	    public void insert(int offset, int value) {
	        if (offset < 0 || offset > size) {
	            throw new IndexOutOfBoundsException("Offset out of bounds");
	        }
	        if (isEmpty()) {
	            head = new Node(value);
	            head.prev = head;
	            head.next = head;
	        } else {
	            Node newNode = new Node(value);
	            if (offset == 0) {
	                newNode.next = head;
	                newNode.prev = head.prev;
	                head.prev.next = newNode;
	                head.prev = newNode;
	                head = newNode;
	            } else {
	                Node current = head;
	                for (int i = 0; i < offset - 1; i++) {
	                    current = current.next;
	                }
	                newNode.next = current.next;
	                newNode.prev = current;
	                current.next.prev = newNode;
	                current.next = newNode;
	            }
	        }
	        size++;
	    }

	    public int remove(int offset) {
	        if (isEmpty()) {
	            throw new NoSuchElementException("List is empty");
	        }
	        if (offset < 0 || offset >= size) {
	            throw new IndexOutOfBoundsException("Offset out of bounds");
	        }
	        int removedValue;
	        if (size == 1) {
	            removedValue = head.value;
	            head = null;
	        } else {
	            Node removedNode;
	            if (offset == 0) {
	                removedNode = head;
	                head = head.next;
	            } else {
	                Node current = head;
	                for (int i = 0; i < offset - 1; i++) {
	                    current = current.next;
	                }
	                removedNode = current.next;
	                current.next = current.next.next;
	                current.next.prev = current;
	            }
	            removedValue = removedNode.value;
	        }
	        size--;
	        return removedValue;
	    }

	    public int popFront() {
	        if (isEmpty()) {
	            throw new NoSuchElementException("List is empty");
	        }
	        int poppedValue = head.value;
	        if (size == 1) {
	            head = null;
	        } else {
	            head.prev.next = head.next;
	            head.next.prev = head.prev;
	            head = head.next;
	        }
	        size--;
	        return poppedValue;
	    }

	    public int popBack() {
	        if (isEmpty()) {
	            throw new NoSuchElementException("List is empty");
	        }
	        int poppedValue;
	        if (size == 1) {
	            poppedValue = head.value;
	            head = null;
	        } else {
	            poppedValue = head.prev.value;
	            head.prev.prev.next = head;
	            head.prev = head.prev.prev;
	        }
	        size--;
	        return poppedValue;
	    }

	    public void pushFront(int element) {
	        insert(0, element);
	    }

	    public void pushBack(int element) {
	        insert(size, element);
	    }

	    public int calculateSum() {
	        int sum = 0;
	        Node current = head;
	        for (int i = 0; i < size; i++) {
	            sum += current.value;
	            current = current.next;
	        }
	        return sum;
	    }

	    public int getMin() {
	        if (isEmpty()) {
	            throw new NoSuchElementException("List is empty");
	        }
	        int min = head.value;
	        Node current = head.next;
	        while (current != head) {
	            if (current.value < min) {
	                min = current.value;
	            }
	            current = current.next;
	        }
	        return min;
	    }

	    public float calculateAverage() {
	        if (isEmpty()) {
	            throw new NoSuchElementException("List is empty");
	        }
	        int sum = calculateSum();
	        return (float) sum / size;
	    }

	    public static void main(String[] args) {
	        CircularLinkedList list = new CircularLinkedList();
	        
	        list.pushFront(5);
	        list.pushFront(10);
	        list.pushBack(15);
	        list.pushBack(20);
	        list.insert(2, 25);
	        
	        System.out.println("Size: " + list.getSize());
	        
	        System.out.println("Sum: " + list.calculateSum());
	        System.out.println("Min: " + list.getMin());
	        System.out.println("Average: " + list.calculateAverage());
	        
	        System.out.println("Removed element: " + list.remove(1));
	        
	        while (!list.isEmpty()) {
	            System.out.println("Popped element: " + list.popFront());
	        }
	    }

}
