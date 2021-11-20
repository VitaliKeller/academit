package ru.vitalikeller.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashTable;   // массив - скелет
    private int size;                   // кол-во элементов
    private int modCount;               // модификации
    private static final int DEFAULT_ARRAY_LENGTH = 10; // дефолтное кол-во позвонков :)

    public HashTable() {
        //noinspection unchecked
        hashTable = new ArrayList[DEFAULT_ARRAY_LENGTH];
    }

    public HashTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Передан размер <=0");
        }

        //noinspection unchecked
        hashTable = new ArrayList[length];
        size = 0;
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
        return hashTable[getIndex(o)] != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        final private int initialModCount = modCount;
        private int inArrayCurrentIndex = 0;    // счетчик - номер коллекции в массиве. Начнем с нулевой
        private int inListIndex = -1;           // счетчик - индекс элемента в коллекции (лежащей в элементе массива)
        private int inTableGlobalIndex = -1;    // счетчик - индекс элемента в size

        @Override
        public boolean hasNext() {
            return inTableGlobalIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась!");
            }

            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("Изменился список!");
            }

            while (hasNext()) {
                if (hashTable[inArrayCurrentIndex] == null) {     // переходим на следующий элемент массива
                    inArrayCurrentIndex++;
                } else {
                    inListIndex++;    // плюсуем номер элемента

                    if (inListIndex == hashTable[inArrayCurrentIndex].size()) {   // если закончилось ребро скелета
                        inArrayCurrentIndex++;      // следующий позвонок
                        inListIndex = -1;           // индекс для начала перебора коллекци-ребра
                    } else {
                        inTableGlobalIndex++;       // индекс элемента в хэш-таблице

                        return hashTable[inArrayCurrentIndex].get(inListIndex); // и - наконец-то сам элемент.! ))
                    }
                }
            }

            return null;        // алилуйя!!! неужели заработает?
        }
    }

    @Override
    public Object[] toArray() {
        Object[] hashTableArray = new Object[size];
        int i = 0;

        for (Object element : this) {
            hashTableArray[i] = element;

            i++;
        }

        return hashTableArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % hashTable.length);
    }

    @Override
    public boolean add(T element) {
        int index = getIndex(element);

        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }

        hashTable[index].add(element);
        modCount++;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object element) {
        int index = getIndex(element);

        if (hashTable[index] == null) {
            return false;
        }

        if (hashTable[index].remove(element)) {
            modCount++;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {   // Проверяет вхождение ВСЕХ элементов
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean edited = false;

        for (T element : c) {
            if (add(element)) {
                edited = true;
            }
        }

        return edited;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean answer = false;

        for (Object element : collection) {
            if (remove(element)) {
                answer = true;
            }
        }

        return answer;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;

        for (ArrayList<T> arrayElement : hashTable) {
            if (arrayElement != null) {
                int initialListSize = arrayElement.size();

                if (arrayElement.retainAll(c)) {
                    modified = true;
                    size -= initialListSize - arrayElement.size();
                }
            }
        }

        if (modified) {
            modCount++;
        }

        return modified;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        //noinspection unchecked
        hashTable = new ArrayList[DEFAULT_ARRAY_LENGTH];

        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        for (ArrayList<T> hashTableElement : hashTable) {
            stringBuilder.append(hashTableElement);
            stringBuilder.append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);    // Можно, т.к. вставил проверку на size==0
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}