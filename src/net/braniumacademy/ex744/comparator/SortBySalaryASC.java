package net.braniumacademy.ex744.comparator;


import net.braniumacademy.ex744.Employee;

import java.util.Comparator;

// sắp xếp theo mức lương tăng dần
public class SortBySalaryASC implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.getSalary() > o2.getSalary()) {
            return 1;
        }
        if (o1.getSalary() < o2.getSalary()) {
            return -1;
        }
        return 0;
    }
}