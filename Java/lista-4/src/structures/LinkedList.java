package structures;

public class LinkedList implements Set, Cloneable{
    private class Node {
        private Pair pair;
        private Node next;

        public Node(Pair p, Node n) {
            this.pair = p;
            this.next = n;
        }

        public Node(Pair p) {
            this.pair = p;
            this.next = null;
        }
    }

    private Node list;

    @Override
    public Pair search(String k) {
        if (list == null) {
            return null;
        }

        Pair currPair;
        Node nextNode = list;

        while (nextNode != null) {
            currPair = nextNode.pair;
            nextNode = nextNode.next;
            if (currPair.key.equals(k)) {
                return currPair;
            }
        }
        return null;
    }

    @Override
    public void insert(Pair p) {
        Pair pairDouble = this.search(p.key);

        if (this.list == null) {
            this.list = new Node(p);
        }
        else if(pairDouble != null) {
            pairDouble.setValue(p.getValue());
        }
        else {
            Node nextNode = this.list;

            while (nextNode.next != null) {
                nextNode = nextNode.next;
            }
            nextNode.next = new Node(p);
        }
    }

    @Override
    public void remove(String k) {
        Node currNode = list;
        Node nextNode = currNode.next;

        if (currNode.pair.key.equals(k)) {
            list = nextNode;
        }
        else {
            while (nextNode != null) {
                if (nextNode.pair.key.equals(k)) {
                    currNode.next = nextNode.next;
                }
                currNode = nextNode;
                nextNode = currNode.next;
            }
        }
    }

    @Override
    public void removeAll() {
        list = null;
    }

    @Override
    public int count() {
        if (list == null) {
            return 0;
        }
        Node currNode = list;
        Node nextNode = currNode.next;
        int i = 1;

        while (nextNode != null) {
            i += 1;
            currNode = nextNode;
            nextNode = currNode.next;
        }
        return i;
    }

}
