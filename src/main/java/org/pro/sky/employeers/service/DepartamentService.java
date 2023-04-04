package org.pro.sky.employeers.service;

import org.pro.sky.employeers.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartamentService {
    Employee getEmployeeWithMinSalary(int departmentId);

    Employee getEmployeeWithMaxSalary(int departmentId);

    Map<String, List<Employee>> getAll(Integer departmentId);
}
