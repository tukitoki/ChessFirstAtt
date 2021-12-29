package ru.vsu.cs.raspopov;

import java.io.*;
import java.util.*;

public class MyMultiHashSet<T> implements Iterable<T>, MultiHashSetInterface<T>, Serializable {

    private Node<T>[] nodes;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTORY = 0.75f;
    private int currentCapacity;
    private float loadFactory;
    private int threshold;

    protected class Node <T> implements Cloneable, Serializable {

        private int hash;
        private final T value;
        private Node<T> next;
        private int occurrences;

        public Node(int hash, T value, Node<T> next, int occurrences) {
            this.hash = hash;
            this.value = value;
            this.next = next;
            this.occurrences = occurrences;
        }
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
            this.occurrences = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<T> node = (Node<T>) o;
            return hash == node.hash && Objects.equals(value, node.value) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, value, next);
        }

        @Override
        public Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
    }


    private Node<T> newNode(int hash, T value) {
        return new Node<T>(hash, value, null, 0);
    }
    private Node<T> newNode(Node<T> node) {
        return new Node<T>(node.hash, node.value, null, node.occurrences);
    }
    private Node<T> newNode() {
        return new Node<T>(null, null);
    }

    private int index(int hash) {
        return hash & (currentCapacity - 1);
    }

    public MyMultiHashSet(Object... args) {
        nodes = new Node[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
        size = 0;
        loadFactory = DEFAULT_LOAD_FACTORY;
        threshold = (int) (loadFactory * currentCapacity);
        for (Object obj : args) {
            add((T) obj, 1);
        }
    }

    public MyMultiHashSet() {
        nodes = new Node[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
        size = 0;
        loadFactory = DEFAULT_LOAD_FACTORY;
        threshold = (int) (loadFactory * currentCapacity);
    }

    public MyMultiHashSet(int initialCapacity) {
        nodes = new Node[initialCapacity];
        currentCapacity = initialCapacity;
        size = 0;
        loadFactory = DEFAULT_LOAD_FACTORY;
        threshold = (int) (loadFactory * currentCapacity);
    }

    public MyMultiHashSet(int initialCapacity, float loadFactory) {
        nodes = new Node[initialCapacity];
        currentCapacity = initialCapacity;
        size = 0;
        this.loadFactory = loadFactory;
        threshold = (int) (loadFactory * currentCapacity);
    }

    private void resize() {
        if (nodes == null || nodes.length == 0) {
            nodes = new Node[DEFAULT_CAPACITY];
            currentCapacity = DEFAULT_CAPACITY;
            size = 0;
            loadFactory = DEFAULT_LOAD_FACTORY;
            return;
        }
        Node<T> newNodes[] = nodes;
        if (threshold < size) {
            size = 0;
            if (currentCapacity > 5000) {
                nodes = new Node[currentCapacity + currentCapacity / 6];
                currentCapacity = currentCapacity + currentCapacity / 6;
            } else {
                nodes = new Node[currentCapacity * 2];
                currentCapacity = currentCapacity * 2;
            }
            threshold = (int) (loadFactory * currentCapacity);
            for (Node<T> obj : newNodes) {
                if (obj == null) {
                    continue;
                }
                add(obj.value, obj.occurrences);
                if (obj.next != null) {
                    Node<T> next = obj.next;
                    while (next != null) {
                        add(next.value, obj.occurrences);
                        next = next.next;
                    }
                }
            }
        }
    }

    @Override
    public boolean add(T element) {
        add(element, 1);
        return true;
    }

    public final int add(T element, int occurrences) {
        Node<T> addedElement = newNode(element.hashCode(), element);
        if (nodes == null || currentCapacity == 0 || size > threshold) {
            resize();
        }
        int index = index(addedElement.hash);
        if (nodes[index] == null) {
            nodes[index] = addedElement;
            nodes[index].occurrences = occurrences;
            size++;
            return 0;
        } else {
            if (nodes[index].hash == addedElement.hash) {
                if (nodes[index].value.equals(addedElement.value)) {
                    nodes[index].occurrences += occurrences;
                    return nodes[index].occurrences - occurrences;
                }
            }
            Node<T> next = nodes[index];
            while (next.next != null) {
                if (next.next.hash == addedElement.hash) {
                    if (next.next.value.equals(addedElement.value)) {
                        next.next.occurrences += occurrences;
                        return next.next.occurrences - occurrences;
                    }
                }
                next = next.next;
            }
            next.next = newNode(addedElement);
            next.next.occurrences += occurrences;
        }
        size++;
        return 0;
    }

    @Override
    public boolean remove(Object element) {
        if (remove(element, 1) != 0) {
            return true;
        }
        return false;
    }

    public final int remove(Object o, int occurrences) {
        Node<T> deletedElement = newNode(o.hashCode(), (T) o);
        int index = index(deletedElement.hash);
        if (nodes[index] == null) {
            return 0;
        }
        if (nodes[index].hash == deletedElement.hash) {
            if (nodes[index].value.equals(deletedElement.value)) {
                nodes[index].occurrences -= occurrences;
                int occ = nodes[index].occurrences + occurrences;
                if (nodes[index].occurrences <= 0) {
                    size--;
                    nodes[index] = nodes[index].next;
                }
                return occ;
            }
        }
        if (nodes[index].next != null) {
            Node<T> next = nodes[index];
            while (next.next != null) {
                if (next.next.hash == deletedElement.hash) {
                    if (next.next.value.equals(deletedElement.value)) {
                        int occ = next.occurrences;
                        next.next.occurrences -= occurrences;
                        if (next.next.occurrences <= 0) {
                            size--;
                            next.next = next.next.next;
                        }
                        return occ;
                    }
                }
                next = next.next;
            }
        }
        return 0;
    }

    public void clear() {
        nodes = new Node[currentCapacity];
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> node = newNode(o.hashCode(), (T) o);
        int index = index(node.hash);
        if (nodes[index] != null) {
            if (nodes[index].hash == node.hash) {
                if (nodes[index].value.equals(o)) {
                    return true;
                }
            }
            if (nodes[index].next == null) {
                return false;
            }
            Node<T> next = nodes[index].next;
            while (next != null) {
                if (next.hash == node.hash) {
                    if (next.value.equals(o)) {
                        return true;
                    }
                }
                next = next.next;
            }
        }
        return false;
    }

    @Override
    public int count(Object element) {
        Node<T> node = newNode(element.hashCode(), (T) element);
        int index = index(node.hash);
        if (nodes[index] == null) {
            return 0;
        }
        if (nodes[index].hash == node.hash) {
            if (nodes[index].value.equals(element)) {
                return nodes[index].occurrences;
            }
        }
        if (nodes[index].next != null) {
            Node<T> next = nodes[index].next;
            while (next != null) {
                if (next.hash == node.hash) {
                    if (next.value.equals(element)) {
                        return next.occurrences;
                    }
                }
                next = next.next;
            }
        }
        return 0;
    }

    @Override
    public final int setCount(T element, int count) {
        Node<T> findElement = newNode(element.hashCode(), element);
        int index = index(findElement.hash);
        if (nodes[index] == null) {
            return 0;
        }
        if (nodes[index].hash == findElement.hash) {
            if (nodes[index].value.equals(findElement.value)) {
                int occ = nodes[index].occurrences;
                nodes[index].occurrences = count;
                if (count <= 0) {
                    nodes[index] = nodes[index].next;
                }
                return occ;
            }
            Node<T> node = nodes[index];
            while (node.next != null) {
                if (node.next.hash == findElement.hash) {
                    if (node.next.value.equals(findElement.value)) {
                        int occ = node.next.occurrences;
                        node.next.occurrences = count;
                        if (count <= 0) {
                            node.next = node.next.next;
                        }
                        return occ;
                    }
                }
                node = node.next;
            }
        }
        return 0;
    }


    @Override
    public final MyMultiHashSet<T> clone() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream ous = new ObjectOutputStream(baos)) {
            ous.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (MyMultiHashSet<T>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyMultiHashSetIterator();
    }

    private class MyMultiHashSetIterator implements Iterator<T> {

        private Node<T>[] nodes;
        private Node<T> next;
        int currSize = 0;
        boolean check;

        public MyMultiHashSetIterator() {
            nodes = MyMultiHashSet.this.nodes;
            next = newNode();
        }

        @Override
        public boolean hasNext() {
            if (check) {
                return next != null;
            } else if (currentCapacity > currSize) {
                if (nodes[currSize] == null) {
                    currSize++;
                    return hasNext();
                }
                return nodes[currSize] != null;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T value;
                if (check) {
                    value = next.value;
                    next = next.next;
                    if (next == null) {
                        check = false;
                        currSize++;
                    }
                } else {
                    value = nodes[currSize].value;
                    next = nodes[currSize].next;
                    if (next == null) {
                        currSize++;
                    } else {
                        check = true;
                    }
                }
                return value;
            }
            throw new NoSuchElementException();
        }
    }

    public int size() {
        return size;
    }

    public boolean containsAll(MyMultiHashSet<T> set) {
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
        MyMultiHashSet<?> that = (MyMultiHashSet<?>) o;
        return size == that.size && currentCapacity == that.currentCapacity
                && Float.compare(that.loadFactory, loadFactory) == 0
                && threshold == that.threshold
                && Arrays.equals(nodes, that.nodes);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        Iterator<T> i = iterator();
        while (i.hasNext()) {
            T object = i.next();
            if (object != null)
                hash += object.hashCode();
        }
        return hash;
    }
}
