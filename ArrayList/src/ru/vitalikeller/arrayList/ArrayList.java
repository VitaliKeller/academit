package ru.vitalikeller.arrayList;

import java.util.*;

public abstract class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;

    public ArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер должен быть >= 0. Переданный размер: " + size);
        }

        items = (E[]) new Object[size];
    }

    public ArrayList() {
        items = (E[]) new Object[5];
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
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >=0 и <=" + (size - 1) + ". Было передано значение = " + index);
        }
    }

    @Override
    public boolean add(E e) {
        if (items.length < size) {
            // todo Добавление массива
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
        items = Arrays.copyOf(items, items.length * 2 + 1);
    }

    public void trimToSize() {
        if (size < items.length) {
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
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }

        items[size - 1] = null;
        size--;

        return removedItem;
    }

    // ----- todo --------------------------

    @Override
    public Iterator<E> iterator() {
        return null;
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

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

}
