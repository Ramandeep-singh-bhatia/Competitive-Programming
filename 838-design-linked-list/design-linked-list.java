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
        Node temp = head.next;
        int count = 0;
        while(temp != null){
            if(count == index){
                return temp.val;
            }
            temp = temp.next;
            count++;
        }
        
        return -1;
    }
    
    public void addAtHead(int val) {
        /*Node newNode = new Node(val);
        newNode.next = head.next;
        head.next = newNode;*/
        addAtIndex(0, val);
    }
    
    public void addAtTail(int val) {
        /*Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        Node newNode = new Node(val);
        temp.next = newNode;*/
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
        Node temp = head.next;
        Node prev = head;
        int count = 0;
        while(temp != null){
            if(count == index){
                prev.next = temp.next;
                size--;
                break;
            }

            prev = temp;
            temp = temp.next;
            count++;
        }
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