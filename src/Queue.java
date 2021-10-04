@SuppressWarnings("ALL")
public class Queue<T> {
    private QNode head;
    private int size;

    public boolean empty() {
        return (head == null);
    }
    public int getSize(){
        return size;
    }
    // remove item in queue
    public T dequeue() {
        if(empty()) return null;
        QNode temp = head;
        head = head.next;
        size--;
        return (T) temp.data;
    }

    // add item to queue
    public void enqueue(T data) {
        QNode newNode = new QNode();
        newNode.data = data;

        if(empty()){
            head = newNode;
            size++;
            return;
        }
        QNode current = head;
        while (current.next != null) {
            current = current.next; // we'll loop until current.next is null
        }
        current.next = newNode;
        size++;
    }
    public T peek(){
        if(empty()) return null;
        return (T) (head.data);
    }
    // For printing Linked List
    public void printLinkedList() {
        System.out.println("Printing LinkedList (head --> last) ");
        QNode current = head;
        while (current != null) {
            current = current.next;
        }
        System.out.println();
    }
}
