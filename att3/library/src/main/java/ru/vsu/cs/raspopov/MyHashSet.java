package ru.vsu.cs.raspopov;


import java.io.*;
import java.util.*;

public class MyHashSet <T> implements SetInterface<T>, Iterable<T>, Cloneable, Serializable {

    private MyMultiHashSet<T> mySet;

    public MyHashSet() {
        mySet = new MyMultiHashSet<>();
    }

    public MyHashSet(int initialCapacity) {
        mySet = new MyMultiHashSet<>(initialCapacity);
    }

    public MyHashSet(int initialCapacity, float loadFactory) {
        mySet = new MyMultiHashSet<>(initialCapacity, loadFactory);
    }

    @Override
    public boolean add(T element) {
        if (!mySet.contains(element)) {
            mySet.add(element, 1);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        mySet.clear();
    }

    @Override
    public MyHashSet<T> clone() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream ous = new ObjectOutputStream(baos)) {
            ous.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (MyHashSet<T>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean contains(Object o) {
        if (mySet.contains(o)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return mySet.isEmpty();
    }

    @Override
    public boolean remove(Object o) {
        if (mySet.remove(o, 1) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return mySet.iterator();
    }

    @Override
    public int size() {
        return mySet.size();
    }

    public boolean containsAll(MyHashSet<T> set) {
        for (Object element : set) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashSet<?> myHashSet = (MyHashSet<?>) o;
        return Objects.equals(mySet, myHashSet.mySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mySet);
    }
}
