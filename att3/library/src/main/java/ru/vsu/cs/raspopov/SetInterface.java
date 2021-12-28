package ru.vsu.cs.raspopov;

import java.util.Iterator;
import java.util.Objects;

public interface SetInterface<T> {

    boolean add(T element);

    void clear();

    Object clone();

    boolean contains(Object o);

    boolean isEmpty();

    boolean remove(Object o);

    public Iterator<T> iterator();

    public int size();


}
