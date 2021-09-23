package ru.vitalikeller.arrayList;

import java.util.*;

public class myArrayList<E> implements List<E> {
    private static final int DEFAULT_LENGTH = 5;

    private E[] items;
    private int length;
    private int modCount = 0;

    public myArrayList(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Размер должен быть >= 0. Переданный размер: " + length);
        }

        //noinspection unchecked
        items = (E[]) new Object[length];
    }

    public myArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder("[");

        for (int i = 0; i < length; i++) {
            resultString.append(items[i]);
            if (i != length - 1) {
                resultString.append(", ");
            }
        }

        resultString.append("]");
        return resultString.toString();
    }

    @Override
    public int size() {
        return length;
    }   // +

    @Override
    public boolean isEmpty() {
        return length == 0;
    }   // +

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    private void validateIndex(int index) {
        validateIndex(index, length - 1);
    }

    private static void validateIndex(int index, int maximumAllowableIndex) {
        if (index < 0 || index > maximumAllowableIndex) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона. (Передан индекс = " + index + ", минимальный = 0, максимальный = " + maximumAllowableIndex + ")");
        }
    }

    @Override
    public boolean add(E e) {
        if (length >= items.length) {
            increaseCapacity();
        }

        items[length] = e;
        length++;
        modCount++;

        return true;
    }   // +

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            items[i] = null;
        }

        modCount++;
        length = 0;
    }

    @Override
    public E get(int index) {
        validateIndex(index);

        return items[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    // --------------- управление размером
    private void increaseCapacity() {
        items = Arrays.copyOf(items, (items.length + 1) * 2);
    }   // +

    public void trimToSize() {
        if (items.length > length) {
            items = Arrays.copyOf(items, length);
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

        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }

        items[length - 1] = null;
        length--;
        modCount++;

        return removedItem;
    }   // +

    @Override
    public E set(int index, E element) {
        validateIndex(index);

        E oldData = items[index];
        items[index] = element;

        modCount++;

        return oldData;
    }

    @Override
    public void add(int index, E element) {
        validateIndex(index, length);

        if (index == length) {
            add(element);
            return;
        }

        if (items.length == length) { // такой же блок есть в add, поэтому сделать этот после add(element) // нужен, чтобы добавить +1 элемент, если место закончилось - для add нового
            increaseCapacity();
        }


        System.arraycopy(items, index, items, index + 1, length - index); // копируем с индекса включительно на +1 вперед https://javadevblog.com/kak-skopirovat-massiv-v-java.html

        items[index] = element;
        length++;
        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new myListIterator();
    }

    public class myListIterator implements Iterator<E> {
        private int CurrentIndex = -1;
        private final int modCountFirst = modCount;

        @Override
        public boolean hasNext() {
            return CurrentIndex + 1 < length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась!");
            }

            if (modCount != modCountFirst) {
                throw new ConcurrentModificationException("Изменился список!");
            }

            CurrentIndex++;
            return items[CurrentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new IllegalArgumentException("Передан пустой массив.");
        }

        if (a.length < items.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, length, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, length);

        if (a.length > length) {
            a[length] = null;
        }

        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object currentElement : c) {
            if (!contains(currentElement)) {    // если хоть один не нашелся - то False! :)
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(length, c);
    }

    // todo ----

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


    // ------------- todo просто заглушить методы, так ли надо сделать

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Метод не поддерживается");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Метод не поддерживается");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Метод не поддерживается");
    }
}
