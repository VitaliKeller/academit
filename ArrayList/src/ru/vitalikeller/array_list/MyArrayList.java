package ru.vitalikeller.array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 5;

    private E[] elements;       // Массив для элементов
    private int size;           // Кол-во элементов (отличается от вместимости capacity!)
    private int modCount;       // Счетчик модификаций

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость должна быть >= 0. Передано: " + capacity);
        }

        //noinspection unchecked
        elements = (E[]) new Object[capacity];
    }

    public MyArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]);

            stringBuilder.append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);    // Можно, т.к. вставил проверку на size==0

        stringBuilder.append("]");

        return stringBuilder.toString();
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

    private static void validateCollectionForNull(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передан null");
        }
    }

    @Override
    public boolean add(E element) {
        add(size, element);

        return true;
    }

    @Override
    public void add(int index, E element) {
        validateIndex(index, size);

        if (size >= elements.length) {
            increaseCapacity();
        }

        if (index != size) {
            // Это кейс "добавить не в конец":
            // копируем с индекса включительно на +1 вперед https://javadevblog.com/kak-skopirovat-massiv-v-java.html
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(elements, 0, size, null);
        modCount++;
        size = 0;
    }

    @Override
    public E get(int index) {
        validateIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    // --------------- управление размером
    private void increaseCapacity() {
        if (size == 0) {
            //noinspection unchecked
            elements = (E[]) new Object[DEFAULT_CAPACITY];  // просто делаем новый с вместимостью по умолчанию

            return;
        }

        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    public void trimToSize() {
        if (elements.length > size) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (elements.length < minCapacity) {
            elements = Arrays.copyOf(elements, minCapacity);
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

        E removedElement = elements[index];

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        elements[size - 1] = null;
        size--;
        modCount++;

        return removedElement;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась!");
            }

            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("Изменился список!");
            }

            currentIndex++;
            return elements[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        validateCollectionForNull(c);

        for (Object element : c) {
            if (!contains(element)) {    // Если хоть один не нашелся - то False! :)
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        validateCollectionForNull(c);

        int initialSize = size;

        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                remove(i);

                i--;
            }
        }

        if (initialSize != size) {
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // проверка на null - в методе с индексом
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        validateCollectionForNull(c);

        validateIndex(index, size);

        if (c.size() == 0) {
            return false;
        }

        int incomeCollectionSize = c.size();

        ensureCapacity(size + incomeCollectionSize);

        System.arraycopy(elements, index, elements, index + incomeCollectionSize, size - index);

        int i = index;
        for (E element : c) {
            elements[i] = element;

            i++;
        }

        size += incomeCollectionSize;
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        validateCollectionForNull(c);

        if (c.isEmpty()) {
            return false;
        }

        int initialSize = size;

        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                remove(i);

                i--;
            }
        }

        if (initialSize != size) {
            modCount++;

            return true;
        }

        return false;
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
