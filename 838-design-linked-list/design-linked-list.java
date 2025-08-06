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
    public MyLinkedList() {
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
        Node newNode = new Node(val);
        newNode.next = head.next;
        head.next = newNode;
    }
    
    public void addAtTail(int val) {
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        Node newNode = new Node(val);
        temp.next = newNode;
    }
    
    public void addAtIndex(int index, int val) {
        int count = 0;
        Node temp = head.next;
        Node prev = head;
        Node newNode = new Node(val);
        while(temp != null){
            if(count == index){
                newNode.next = temp;
                prev.next = newNode;
                break;
            }
            prev = temp;
            temp = temp.next;
            count++;
        }

        if(count == index){
            prev.next = newNode;
        }
    }
    
    public void deleteAtIndex(int index) {
        Node temp = head.next;
        Node prev = head;
        int count = 0;
        while(temp != null){
            if(count == index){
                prev.next = temp.next;
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