/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

/*class Solution {
    Map<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if(head == null)
            return null;
        Node oldNode = head;
        Node newNode = new Node(oldNode.val);
        this.map.put(oldNode, newNode);
        while(oldNode != null){
            newNode.random = getClonedNode(oldNode.random);
            newNode.next = getClonedNode(oldNode.next);
            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return this.map.get(head);
    }

    public Node getClonedNode(Node node){
        if(node != null){
            if(!this.map.containsKey(node))
                this.map.put(node, new Node(node.val));
            return this.map.get(node);
        }
        return null;
    }
}*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null)
            return null;
        Node node = head;
        while(node != null){
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
            node = newNode.next;
        }

        node = head;
        //For each original node, we set its copy's random pointer. If the original node's random points to node X, then the copy's random should point to X's copy, which is X.next in our interweaved list.
        while(node != null){
            node.next.random = (node.random != null) ? node.random.next : null;
            node = node.next.next;
        }

        Node oldNodeList = head;
        Node newNodeList = head.next;
        Node newNode_head = head.next;
        while(oldNodeList != null){
            oldNodeList.next = oldNodeList.next.next;
            newNodeList.next = (newNodeList.next != null) ? newNodeList.next.next : null;

            oldNodeList = oldNodeList.next;
            newNodeList = newNodeList.next;
        }

        return newNode_head;
    }
}