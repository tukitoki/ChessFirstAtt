package ru.vsu.cs.raspopov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyHashSetTest {

    @Test
    public void addTest01() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(2);
        set.add(5);
        Assertions.assertTrue(!set.add(5));
    }

    @Test
    public void addTest02() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(2);
        set.add(5);
        set.add(7);
        set.add(4);
        set.add(1);
        set.add(8);
        set.add(54);
        set.add(34);
        Assertions.assertTrue(!set.add(34));
    }

    @Test
    public void addTest03() {
        MyHashSet<Integer> set = new MyHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i);
        }
        Assertions.assertTrue(!set.add(6));
    }

    @Test
    public void addTest04() {
        MyHashSet<Integer> set = new MyHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i);
        }
        Assertions.assertTrue(set.add(123));
    }

    @Test
    public void testRemove01() {
        MyHashSet<Integer> set = new MyHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i);
        }
        set.remove(23);
        Assertions.assertTrue(!set.contains(23));
    }

    @Test
    public void testRemove02() {
        MyHashSet<Integer> set = new MyHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i);
        }
        set.remove(23);
        set.remove(1);
        Assertions.assertTrue(!set.contains(1));
    }

    @Test
    public void testContains01() {
        MyHashSet<Integer> set = new MyHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i);
        }
        Assertions.assertTrue(set.contains(4));
    }

    @Test
    public void testContains02() {
        MyHashSet<Integer> set = new MyHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i);
        }
        set.remove(23);
        set.remove(1);
        Assertions.assertTrue(set.contains(14));
    }

    @Test
    public void testContains03() {
        MyHashSet<Integer> set = new MyHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i);
        }
        set.remove(23);
        set.remove(1);
        Assertions.assertTrue(!set.contains(25));
    }

    @Test
    public void testContains04() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(1111);
        set.add(4242);
        set.add(111111);
        set.add(1000);
        set.add(4000);
        set.add(1111111);
        set.add(111);
        set.add(1000);
        set.add(1111111);
        set.remove(23);
        set.remove(1);
        Assertions.assertTrue(set.contains(1111111));
    }

    @Test
    public void testConaintsAll01() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(1111);
        set.add(4242);
        set.add(111111);
        set.add(1000);
        set.add(4000);
        Assertions.assertTrue(set.containsAll(new MyHashSet<>(1, 1111, 4242, 111111, 1000, 4000)));
    }

    @Test
    public void testConaintsAll02() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(1111);
        set.add(4242);
        set.add(111111);
        set.add(1000);
        set.add(4000);
        Assertions.assertTrue(!set.containsAll(new MyHashSet<>(1, 1111, 4242, 111111, 1000, 365)));
    }
}
