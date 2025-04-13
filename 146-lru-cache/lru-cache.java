/*
    The below implementation is for LRU chache which is least recently used cache. We need to implement 3 things. 
    1. Get is to get the value for the given key
    2. Put is to put the key value pair 
    3. If the capacity is full, we have to delete the least recently used key.

    Since we have to remove the least recently used key, we will have to maintain a queue to keep track of the recently used key. In the queue we can have the most recently used at the very end and the least recently used at the begning and whenever we reach the capacity, we delete the top most element from the queue. 
    To implement the queue we can use ArrayList, but everytime we add a new key, value pair or even get a key, it will be the most recently used and should be moved to the end of the queue. In case of an ArrayList lets say the element at the begning of the queue is the one that we get, not it becomes the most recent used and has to be moved to the very end. If we take the first element and add it to the very end, we will have to move all the other elements position making this O(n). To get this done in O(1) we will need a data structure that can remove the element and change the position in constant time. We can actually use linked list for this. 
    Lets say we have a linked list A -> B -> C -> D -> E and we try to remove C. All we have to do is point B.next to D and then we can say E.next to C to move to the end. But to remove C, we need the reference of the previous element in the linked list, so we need doubly linked list.  
    When we a put the key, value pair, we can create a new node and add it to the list which will go at the end and will be the most recent used. To be able to add the node at the end of the list, we will also need reference to the end of the list. The linked list is to maintain the queue, but to store the key, value pair we can use a hash map. 
    When we have to get the key, we can get the key from the hash map but it will now become the recently used and will be moved to the very end. We can get the value for the key from the hash map in O(1) but in the linked list if we have to find the node we will have to traverse to the node. To get the node in O(1) we can keep track of the node in the has map. Instead of storing the value for the key in hash map we store the node reference. So every time we hae a put, we create the new node, store the node in the hahs map for the key. Next time when we have to get the key, we can get the node associated with the key and can reach the node in the linked list in O(1) time. 
    We will have a node which will keep track of the key, value as well as prev and next becoz its a double inked list. This will be a node int he linked list.
    For LRU cache, We will need a capacity to store the capacity of the data strucutre. A map tp keep track of the key and associated node in the list. A head and a tail so that we can remove the least recently used from the top and also be able to add the most recently used at the end of the queue in O(1) time.
    For get, We check if the key exist in the map. If yes we go to the respective node, delete the node and then add it to the end of the list
    For put, we check if the node exist or not. If it does not exist, we create a new node, add it to the end of the list and also add it to the map with the key. If it already exist, we remove the node from the list, create a new node as the value associated with the key could be different and then add it at the end of the list. We can also update the map with new node.
    To add the node to the end of the list we can diretly go to the end using tail
         tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    To remove the node, we can simply change the pointer as below
        node.prev.next = node.next;
        node.next.prev = node.prev;
*/

class Node{
    int key;
    int value;
    Node prev;
    Node next;
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity){
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
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value){
        if(!map.containsKey(key)){
            Node newNode = new Node(key, value);
            add(newNode);
            map.put(key, newNode);
        } else {
            remove(map.get(key));
            Node node = new Node(key,value);
            add(node);
            map.put(key,node);
        }

        if(map.size() > capacity){
            Node leastNode = head.next;
            remove(leastNode);
            map.remove(leastNode.key);
        }
    }

    private void add(Node node){
        tail.prev.next = node;
        node.next = tail;
        node.prev = tail.prev;

        tail.prev = node;
    }

    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    

}




/*class Node {
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
}*/

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */