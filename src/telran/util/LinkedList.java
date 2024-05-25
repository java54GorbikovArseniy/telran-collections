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

    @Override
    public boolean add(T obj) {
        Node<T> node = new Node<>(obj);
        addNode(size, node);
        return true;
    }

    @Override
    public boolean remove(T pattern) {
        boolean res = true;
        if (indexOf(pattern) == -1) {
            res = false;
        } else {
            remove(indexOf(pattern));
        }
        return res;
    }

    @Override
    public boolean contains(T pattern) {
        return indexOf(pattern) > -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public T get(int index) {
        List.checkIndex(index, size, true);
        Node<T> current = getNode(index);
        return current.data;
    }

    @Override
    public void add(int index, T obj) {
        List.checkIndex(index, size, false);
        Node<T> node = new Node<>(obj);
        addNode(index, node);
    }

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

        size--;
        return elem;
    }

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

    private Node<T> getNode(int index) {
        return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
    }

    private Node<T> getNodeFromTail(int index) {
        Node<T> current = tail;
        for (int i = size - 1; i > index; i--) {
            current = current.prev;
        }
        return current;
    }

    private Node<T> getNodeFromHead(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

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

    private void addMiddle(Node<T> node, int index) {
        Node<T> nodeNext = getNode(index);
        Node<T> nodePrev = nodeNext.prev;
        nodeNext.prev = node;
        nodePrev.next = node;
        node.prev = nodePrev;
        node.next = nodeNext;
    }

    private void addTail(Node<T> node) {
        //head cannot be null
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

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
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(index++);
        }
    }
}