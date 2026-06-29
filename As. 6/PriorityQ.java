//priority queue using a linked list
//items in non-decreasing order

public class PriorityQ<T extends Comparable<? super T>> {    
    //linked list
    private class Node{
        T data; //node value
        Node next; //reference next node

        public Node(T data){ //constructor
            this.data = data;
            this.next = null;
        }
    }

    private Node head; //first node in queue
    
    //empty queue
    public PriorityQ(){
        head = null;

    }

    //enqueue, add value to queue
    public void enqueue(T value){
        Node newNode = new Node(value);

        if (head == null || value.compareTo(head.data) <= 0){
            newNode.next = head;
            head = newNode;
            return;
        }
        Node current = head; //start at the head

        while(current.next != null && value.compareTo(current.next.data) > 0){
            current = current.next;
        }
        //new position
        newNode.next = current.next;
        current.next = newNode;
    }

    //remove and return front value in the queue
    public T dequeue(){
        if (head == null){
            return null;
        }
        T value = head.data;
        head = head.next;

        return value;
    }
    //remove everything in the queue
    public void clear(){
        head = null;
    }

    //test class to make sure it works for all data types that implement comparable interface
    public static void main(String[] args){
        PriorityQ<Integer> q = new PriorityQ<>();

        //make sure it works with different comparable types

        //int test
        q.enqueue(1);
        q.enqueue(5);
        q.enqueue(4);
        System.out.println("Int test: ");
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        //String test
        PriorityQ<String> words = new PriorityQ<>();
        words.enqueue("SUU");
        words.enqueue("spring");
        words.enqueue("vacation");
        System.out.println("String test: ");
        System.out.println(words.dequeue());
        System.out.println(words.dequeue());

        //clear queue
        q.clear(); 

    }

}