class Node {
    int key;
    int value;
    Node next;
    Node prev;

    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
       if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        //delete the node
        delete(node);

        //add the node
        add(node);
        
        return node.value;
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)){
            Node node = new Node(key,value);
            add(node);
            map.put(key,node);
        } else {
            //Node node = map.get(key);
            delete(map.get(key));
            Node node = new Node(key,value);
            add(node);
            map.put(key,node);
        }

        if(map.size() > capacity){
            Node leastNode = head.next;
            delete(leastNode);
            map.remove(leastNode.key);
        }
    }

    private void add(Node node){
        //Node lastNode = tail.prev;
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    private void delete(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */