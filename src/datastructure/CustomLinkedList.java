package datastructure;

import util.MiscUtility;

public class CustomLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public CustomLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
        size++;
    }

    public Node<E> getFirst() {
        return first;
    }

    public void remove(Node<E> node) {
        if (size == 1) {
            MiscUtility.assertion(first == node && last == node,
                    String.format("The element to be removed [%s] is not in the list [%s]", node, this));
            first = null;
            last = null;
        } else if (first == node) {
            first = node.next;
            first.previous = null;
            node.next = null;
        } else if (last == node) {
            last = node.previous;
            node.previous = null;
            last.next = null;
        } else {
            Node<E> prev = node.previous;
            Node<E> nex = node.next;
            prev.next = nex;
            nex.previous = prev;
            node.previous = null;
            node.next = null;
        }
        size--;
    }

    public static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;

        public Node(E element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        public E getElement() {
            return element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrevious() {
            return previous;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<E> curr = first;
        boolean done = false;
        builder.append('[');
        while (curr != null) {
            if (done) {
                builder.append(",");
            } else {
                done = true;
            }
            builder.append(curr);
            curr = curr.next;
        }
        builder.append("]");
        return "CustomLinkedList{" +
                builder.toString() +
                '}';
    }
}
