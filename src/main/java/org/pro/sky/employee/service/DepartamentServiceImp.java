package org.pro.sky.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.employe25.employeers.Employer.exceptions.DepartmentSearchException;
import pro.sky.employe25.employeers.Employer.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartamentServiceImp implements DepartamentService {
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public DepartamentServiceImp(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
@Override
    public Employee getEmployeeWithMinSalary(int departmentId) {

    return employeeService.getAll().stream()
            .filter(employee -> employee.getDepartament().getId() == departmentId)
            .min((e1, e2) -> Float.compare(e1.getSalary(), e2.getSalary()))
            .orElseThrow(() -> new DepartmentSearchException("Департамент не найден"));
        }
 @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {

    return    employeeService.getAll().stream()

            .filter(employee -> employee.getDepartament().getId() == departmentId)
            .max((e1, e2) -> Float.compare(e1.getSalary(), e2.getSalary()))
            .orElseThrow(() -> new DepartmentSearchException("Департамент не найден"));

    }
    @Override
    public Map<String, List<Employee>> getAll(Integer departmentId) {

        return employeeService.getAll().stream()
                .filter(employee -> departmentId != null ? employee.getDepartament().getId() == departmentId:true)
                .collect(Collectors.groupingBy(employee -> employee.getDepartament().getName(),
                        Collectors.mapping(e -> e, Collectors.toList())));
    }

}
