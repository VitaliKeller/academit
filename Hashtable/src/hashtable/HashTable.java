package hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashTable;   // размер таблицы
    private int size;   // кол-во элементов
    private int modCount;
    private static final int DEFAULT_LENGTH = 10;

    public HashTable() {
        //noinspection unchecked
        hashTable = new ArrayList[DEFAULT_LENGTH];
    }

    // конструктор с размерностью, если верно понял
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
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
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
    public void clear() {
        if (size == 0) {
            return;
        }

        //noinspection unchecked
        hashTable = new ArrayList[DEFAULT_LENGTH];

        modCount++;
        size = 0;
    }
}
