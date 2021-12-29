package ru.vsu.cs.raspopov;

public interface MultiHashSetInterface<T> {

    public boolean add(T element);

    public int add(T element, int occurrences);

    public boolean remove(Object element);

    public int remove(Object element, int occurrences);

    public void clear();

    public boolean contains(Object o);

    public int count(Object element);

    public int setCount(T element, int count);

    public int size();



}
