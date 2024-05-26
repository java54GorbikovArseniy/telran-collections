package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    // O(1)
    @Override
    public boolean add(T obj) {
        Node<T> node = new Node<>(obj);
        addNode(size, node);
        return true;
    }

    // O(n)
    @Override
    public boolean remove(T pattern) {
        boolean res = true;
        int index = indexOf(pattern);
        if (index == -1) {
            res = false;
        } else {
            remove(index);
        }
        return res;
    }

    // O(n)
    @Override
    public boolean contains(T pattern) {
        return indexOf(pattern) > -1;
    }

    // O(1)
    @Override
    public int size() {
        return size;
    }

    // O(1)
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    // O(n)
    @Override
    public T get(int index) {
        List.checkIndex(index, size, true);
        Node<T> current = getNode(index);
        return current.data;
    }

    // O(n)
    @Override
    public void add(int index, T obj) {
        List.checkIndex(index, size, false);
        Node<T> node = new Node<>(obj);
        addNode(index, node);
    }

    //O(1)
    @Override
    public T remove(int index) {
        List.checkIndex(index, size, true);
        Node<T> nodeToRemove = getNode(index);
        T elem = nodeToRemove.data;

        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = nodeToRemove.next;
        } else {
            head = nodeToRemove.next;
        }

        if (nodeToRemove.next != null) {
            nodeToRemove.next.prev = nodeToRemove.prev;
        } else {
            tail = nodeToRemove.prev;
        }
        nodeToRemove = null;
        size--;
        return elem;
    }

    //O(n)
    @Override
    public int indexOf(T pattern) {
        int index = 0;
        Node<T> current = head;

        while (current != null && !Objects.equals(current.data, pattern)) {
            current = current.next;
            index++;
        }
        return current != null ? index : -1;
    }

    //O(n)
    @Override
    public int lastIndexOf(T pattern) {
        int index = size - 1;
        Node<T> current = tail;

        while (current != null && !Objects.equals(current.data, pattern)) {
            current = current.prev;
            index--;
        }
        return current != null ? index : -1;
    }

    //O(n)
    private Node<T> getNode(int index) {
        return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
    }

    //O(n)
    private Node<T> getNodeFromTail(int index) {
        Node<T> current = tail;
        for (int i = size - 1; i > index; i--) {
            current = current.prev;
        }
        return current;
    }

    //O(n)
    private Node<T> getNodeFromHead(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    //O(1)
    private void addNode(int index, Node<T> node) {
        if (index == 0) {
            addHead(node);
        } else if (index == size) {
            addTail(node);
        } else {
            addMiddle(node, index);
        }
        size++;
    }

    //O(1)
    private void addMiddle(Node<T> node, int index) {
        Node<T> nodeNext = getNode(index);
        Node<T> nodePrev = nodeNext.prev;
        nodeNext.prev = node;
        nodePrev.next = node;
        node.prev = nodePrev;
        node.next = nodeNext;
    }

    //O(1)
    private void addTail(Node<T> node) {
        //head cannot be null
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    //O(1)
    private void addHead(Node<T> node) {
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        Node<T> current = head;

        //O(1)
        @Override
        public boolean hasNext() {
            return current != null;
        }

        //O(1)
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}