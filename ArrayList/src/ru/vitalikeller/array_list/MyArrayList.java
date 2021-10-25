package ru.vitalikeller.array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 5;

    private E[] items;
    private int size;
    private int modCount = 0;

    public MyArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер должен быть >= 0. Переданный размер: " + size);
        }

        //noinspection unchecked
        items = (E[]) new Object[size];
    }

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
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
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = e;
        size++;
        modCount++;

        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        modCount++;
        size = 0;
    }

    @Override
    public E get(int index) {
        validateIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);

        E oldData = items[index];
        items[index] = element;

        modCount++;

        return oldData;
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

    public void ensureCapacity(int minCapacity) {
        if (items.length < minCapacity) {
            items = Arrays.copyOf(items, minCapacity);
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
        modCount++;

        return removedItem;
    }


    @Override
    public void add(int index, E element) {
        validateIndex(index, size);

        // кейс - добавить в конец
        if (index == size) {
            add(element);
            return;
        }

        // такой же блок есть в add, поэтому помещаю его после add(element)
        // нужен, чтобы (если место закончилось) добавить место-элемент в массив для нового E
        if (items.length == size) {
            increaseCapacity();
        }

        // кейс - добавить не в конец
        System.arraycopy(items, index, items, index + 1, size - index); // копируем с индекса включительно на +1 вперед https://javadevblog.com/kak-skopirovat-massiv-v-java.html

        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int modCountFirst = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась!");
            }

            if (modCount != modCountFirst) {
                throw new ConcurrentModificationException("Изменился список!");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new IllegalArgumentException("Передан пустой массив.");
        }

        if (a.length < items.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
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
    public boolean retainAll(Collection<?> c) {
        int currentSize = size;

        if (c == null) {
            throw new IllegalArgumentException("Передан пустой массив.");
        }

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(i);

                i--;
                modCount++;
            }
        }

        return currentSize != size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new IllegalArgumentException("Передан пустой массив.");
        }

        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        validateIndex(index, size);

        int incomeCollectionSize = c.size();

        if (incomeCollectionSize == 0) {
            return false;
        }

        ensureCapacity(size + incomeCollectionSize);

        System.arraycopy(items, index, items, index + incomeCollectionSize, size - index);
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.toArray(), 0, items, index, incomeCollectionSize);

        size += incomeCollectionSize;
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        int currentSize = size;

        for (int i = 0; i < size; i++) {
            if (c.contains(items[i])) {
                remove(i);

                i--;
                modCount++;
            }
        }

        return currentSize != size;
    }

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
