package ru.vitalikeller.arrayList;

import java.util.*;

public class myArrayList<E> implements List<E> {
    private static final int DEFAULT_SIZE = 5;

    private E[] items;
    private int size;
    private int modCount;

    public myArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер должен быть >= 0. Переданный размер: " + size);
        }

        items = (E[]) new Object[size];
    }

    public myArrayList() {
        items = (E[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            resultString.append(items[i]);
            if (i != size - 1) {
                resultString.append(", ");
            }
        }

        resultString.append("]");
        return resultString.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    private void validateIndex(int index) {
        validateIndex(index, size - 1);
    }

    private static void validateIndex(int index, int maximumAllowableIndex) {
        if (index < 0 || index > maximumAllowableIndex) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона. (Передан индекс = " + index + ", минимальный = 0, максимальный = " + maximumAllowableIndex + ")");
        }
    }


    @Override
    public boolean add(E e) {
        if (items.length == size) {
            increaseCapacity();
        }

        items[size] = e;
        size++;

        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
    }

    @Override
    public E get(int index) {
        validateIndex(index);

        return items[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    // --------------- управление размером
    private void increaseCapacity() {
        items = Arrays.copyOf(items, (items.length + 1) * 2);
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);

        E removedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;

        return removedItem;
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);

        E oldData = items[index];
        items[index] = element;

        return oldData;
    }

    @Override
    public void add(int index, E element) {
        validateIndex(index, size);

        if (index == size) {
            add(element);
            return;
        }

        if (items.length == size) { // такой же блок есть в add, поэтому сделать этот после add(element)! // нужен, чтобы добавить +1 элемент, если место закончилось - для add нового
            increaseCapacity();
        }


        System.arraycopy(items, index, items, index + 1, size - index); // копируем с индекса включительно на +1 вперед https://javadevblog.com/kak-skopirovat-massiv-v-java.html

        items[index] = element;
        size++;
    }

    // ----- todo --------------------------
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Метод не поддерживается");
    }

    public class MyIterator implements Iterator<E> {
        private int CurrentIndex = -1;

        @Override
        public boolean hasNext() {
            return CurrentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась!");
            }

            CurrentIndex++;
            return items[CurrentIndex];
        }
    }


    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    // ------------- not

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
