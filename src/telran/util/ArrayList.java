package telran.util;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private int size;
    private T[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    /**
     * adds object at end of array, that is, at index == size
     */
    @Override
    public boolean add(T obj) {
        ensureCapacity();
        array[size++] = obj;
        return true;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            allocate();
        }
    }

    private void allocate() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    @Override
    public boolean remove(T pattern) {
        int index = indexOf(pattern);
        boolean res = false;
        if (index > -1 && index < size) {
            res = true;
            remove(index);
        }
        return res;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
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
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public void add(int index, T obj) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }

    @Override
    public int indexOf(T pattern) {
        int index = -1;
        for(int i=0; i<size && index==-1; i++) {
            if ((pattern == null && array[i] == null) || (pattern != null && pattern.equals(array[i]))) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(T pattern) {
        // DONE
        int index = -1;
        for(int i=size-1; i>=0 && index==-1; i--) {
            if ((pattern == null && array[i] == null) || (pattern != null && pattern.equals(array[i]))) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("Iterator out of bounds");
            }
            return array[index++];
        }
    }
}
