package org.pro.sky.employeers.controller;

import org.pro.sky.employeers.exceptions.EmployeeAlreadyAddedException;
import org.pro.sky.employeers.exceptions.EmployeeNotFoundException;
import org.pro.sky.employeers.exceptions.EmployeeStorageIsFullException;
import org.pro.sky.employeers.exceptions.IncorrectNameException;
import org.pro.sky.employeers.model.Employee;
import org.pro.sky.employeers.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)

    public String handleException(EmployeeNotFoundException e) {
        return String.format("ошибка: %s,  причина: %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String handleException(EmployeeAlreadyAddedException e) {
        return String.format("ошибка: %s,  причина: %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String handleException(EmployeeStorageIsFullException e) {
        return String.format("ошибка: %s,  причина: %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IncorrectNameException.class)
    public String handleException(IncorrectNameException e) {
        return String.format("ошибка: %s,  причина: %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    private final EmployeeServiceImpl employeeService;


    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping()
    public String hello() {
        return "Добро пожаловать в списки типов!";
    }

    @GetMapping(path = "/add")
    public Employee addEmployer(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("salary") float salary,
                                @RequestParam("departmentId") int departmentId) {
        return employeeService.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping(path = "/find")
    public Employee findEmployer(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployer(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping(path = "/findAll")
    public List<Employee> getEmployers() {
        return employeeService.getAll();
    }



}