package net.braniumacademy.ex741.sorting;

import net.braniumacademy.ex741.Cat;

import java.util.Comparator;

public class AgeSortingDESC implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        return o2.getAge() - o1.getAge();
    }
}
