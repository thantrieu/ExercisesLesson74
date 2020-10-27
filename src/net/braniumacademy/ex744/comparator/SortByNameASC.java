package net.braniumacademy.ex744.comparator;

import net.braniumacademy.ex744.Employee;

import java.util.Comparator;

// sắp xếp danh sách nhân viên theo tên a-z
public class SortByNameASC implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getFullName().getFirst()
                .compareTo(o2.getFullName().getFirst());
    }
}
