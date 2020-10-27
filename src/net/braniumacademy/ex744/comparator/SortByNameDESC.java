package net.braniumacademy.ex744.comparator;

import net.braniumacademy.ex744.Employee;

import java.util.Comparator;

// sắp xếp nhân viên theo tên z-a
public class SortByNameDESC implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o2.getFullName().getFirst()
                .compareTo(o1.getFullName().getFirst());
    }
}
