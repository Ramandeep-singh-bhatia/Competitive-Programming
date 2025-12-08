class MaxStack {
    class Node {
        int val;
        Node prev;
        Node next;

        Node(int val){
            this.val = val;
        }
    }

    class DoubleLinkedList {
        Node head;
        Node tail;

        DoubleLinkedList(){
            head = new Node(Integer.MIN_VALUE);
            tail = new Node(Integer.MIN_VALUE);
            head.next = tail;
            tail.prev = head;
        }

        Node add(int val){
            Node node = new Node(val);
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            return node;
        }

        void remove(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private DoubleLinkedList dll;
    private TreeMap<Integer, List<Node>> map;
    public MaxStack() {
        dll = new DoubleLinkedList();
        map = new TreeMap<>();
    }
    
    public void push(int x) {
        Node node = dll.add(x);
        map.putIfAbsent(x, new ArrayList<>());
        map.get(x).add(node);
    }
    
    public int pop() {
        Node node = dll.tail.prev;
        dll.remove(node);
        int val = node.val;
        List<Node> nodes = map.get(val);
        nodes.remove(nodes.size() - 1);
        if(nodes.isEmpty())
            map.remove(val);

        return val;
    }
    
    public int top() {
        return dll.tail.prev.val;
    }
    
    public int peekMax() {
        return map.lastKey();
    }
    
    public int popMax() {
        int maxVal = map.lastKey();
        List<Node> nodes = map.get(maxVal);
        Node nodeToRemove = nodes.remove(nodes.size() - 1);
        if(nodes.isEmpty()){
            map.remove(maxVal);
        }

        dll.remove(nodeToRemove);

        return maxVal;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */