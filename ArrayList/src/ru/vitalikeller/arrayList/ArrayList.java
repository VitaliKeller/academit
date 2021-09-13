package ru.vitalikeller.arrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;

    public ArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер должен быть >= 0");
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

    // --------------- управление размером
    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
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

    @Override   // -+
    public boolean remove(Object o) {
        if (indexOf(o) == -1) {
            return false;
        }

        return true;
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

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }
}
