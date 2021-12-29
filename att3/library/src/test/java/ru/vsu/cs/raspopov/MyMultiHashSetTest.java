package ru.vsu.cs.raspopov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyMultiHashSetTest {

    @Test
    public void addTest01() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        set.add(2, 2);
        set.add(5, 1);
        Assertions.assertEquals(set.add(5, 3), 1);
    }

    @Test
    public void addTest02() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        set.add(2, 2);
        set.add(5, 3);
        set.add(7, 1);
        set.add(4, 2);
        set.add(1, 4);
        set.add(8, 7);
        set.add(54, 8);
        set.add(34, 9);
        Assertions.assertEquals(set.add(34, 2), 9);
    }

    @Test
    public void addTest03() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i, i);
        }
        Assertions.assertEquals(set.add(6, 2), 6);
    }

    @Test
    public void addTest04() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i, i);
        }
        Assertions.assertEquals(set.add(123, 4), 0);
    }

    @Test
    public void testRemove01() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i, i);
        }
        set.remove(23, 23);
        Assertions.assertTrue(!set.contains(23));
    }

    @Test
    public void testRemove02() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i, i);
        }
        set.remove(23, 23);
        set.remove(1, 2);
        Assertions.assertTrue(!set.contains(1));
    }

    @Test
    public void testContains01() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i, i);
        }
        Assertions.assertTrue(set.contains(4));
    }

    @Test
    public void testContains02() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i, i);
        }
        set.remove(23, 25);
        set.remove(1, 1);
        Assertions.assertTrue(set.contains(14));
    }

    @Test
    public void testContains03() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 24; i++) {
            set.add(i, i);
        }
        set.remove(23, 25);
        set.remove(1, 25);
        Assertions.assertTrue(!set.contains(25));
    }

    @Test
    public void testContains04() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        set.add(1, 2);
        set.add(1111, 1);
        set.add(4242, 3);
        set.add(111111, 1);
        set.add(1000, 34);
        set.add(4000, 2);
        set.add(1111111, 0);
        set.add(111, 21);
        set.add(1000, 6);
        set.add(1111111, 7);
        set.remove(23, 21);
        set.remove(1, 22);
        Assertions.assertTrue(set.contains(1111111));
    }

    @Test
    public void testConaintsAll01() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        set.add(1, 2);
        set.add(1111, 1);
        set.add(4242, 3);
        set.add(111111, 1);
        set.add(1000, 34);
        set.add(4000, 2);
        Assertions.assertTrue(set.containsAll(new MyMultiHashSet<>(1, 1111, 4242, 111111, 1000, 4000)));
    }

    @Test
    public void testConaintsAll02() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        set.add(1, 2);
        set.add(1111, 1);
        set.add(4242, 3);
        set.add(111111, 1);
        set.add(1000, 34);
        set.add(4000, 2);
        Assertions.assertTrue(!set.containsAll(new MyMultiHashSet<>(1, 1111, 4242, 111111, 1000, 365)));
    }

    @Test
    public void resizeTest01() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 9999; i++) {
            set.add(i, 1);
        }
        Assertions.assertTrue(set.containsAll(new MyMultiHashSet<>(2, 4, 1, 6, 122, 451, 2321, 5521)));
    }

    @Test
    public void resizeTest02() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>();
        for (int i = 0; i < 10000; i++) {
            set.add(i, 1);
        }
        Assertions.assertTrue(!set.containsAll(new MyMultiHashSet<>(2, 4, 1, 6, 122, 451, 2321, 5521, 100000)));
    }

    @Test
    public void setCountTest01() {
        MyMultiHashSet<Integer> set = new MyMultiHashSet<>(2, 4, 1, 2, 4);
        set.setCount(2, 9);
        Assertions.assertEquals(set.count(2), 9);
    }
}
