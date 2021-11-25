package structures;

import java.util.Iterator;

public class OrderedList<T extends Comparable<T>> implements OrderedSequence<T>, Iterable<T>{
    private class Node<S extends Comparable<S>> {
        private Node<S> next;
        private S data;

        public Node(S el) {
            data = el;
        }

    }

//    private class CustomIterator<N> implements Iterator<N> {
//        Node<T> node;
//
//        public CustomIterator(Node<T> node) {
//            this.node = node;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return node.next != null;
//        }
//
//        @Override
//        public N next() {
//            return node.next;
//        }
//    }

    private Node<T> head;

//    @Override
//    public Iterator<T> iterator() {
//        return new CustomIterator<T>(head);
//    }

    private Node<T> getLastNode() {
        Node<T> currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }
        return currNode;
    }

    @Override
    public void insert(T el) {
        getLastNode().next = new Node<T>(el);
    }

    @Override
    public void remove(T el) {

    }

    @Override
    public T min() {
        return null;
    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public T at(int pos) {
        return null;
    }

    @Override
    public boolean search(T el) {
        return false;
    }

    @Override
    public int index(T el) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }
}
