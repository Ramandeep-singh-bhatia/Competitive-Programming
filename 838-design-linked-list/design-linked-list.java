class Node{
    int val;
    Node next;
    public Node(int val){
        this.val = val;
        this.next = null;
    }
    public Node(){
        this.val= 0;
        this.next = null;
    }
}

class MyLinkedList {
    Node head;
    int size;
    public MyLinkedList() {
        size = 0;
        head = new Node();
    }
    
    public int get(int index) {
        if(index < 0 || index >= size)
            return -1;
        Node temp = head.next;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.val;

    }
    
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size)
            return;
        Node prev = head;
        Node newNode = new Node(val);
        for(int i = 0; i < index; i++){
            prev = prev.next;
        }

        newNode.next = prev.next;
        prev.next = newNode;
        size++;
        
    }
    
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size)
            return;
        Node prev = head;
        
        for(int i = 0; i < index; i++){
            prev = prev.next;
        }

        prev.next = prev.next.next;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */