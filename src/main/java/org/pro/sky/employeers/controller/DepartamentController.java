package org.pro.sky.employeers.controller;

import org.pro.sky.employeers.exceptions.DepartmentSearchException;
import org.pro.sky.employeers.model.Employee;
import org.pro.sky.employeers.service.DepartamentService;
import org.pro.sky.employeers.service.DepartamentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

    @RestController
    @RequestMapping("/departments")
    public class DepartamentController {
        private final DepartamentService departamentService;

        @Autowired
        public DepartamentController(DepartamentServiceImp departamentService) {
            this.departamentService = departamentService;
        }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DepartmentSearchException.class)

    public String handleException(DepartmentSearchException e) {
        return String.format("ошибка: %s,  причина: %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

         @GetMapping(path = "/max-salary")
        public Employee maxSalary(@RequestParam  Integer departmentId) {
        return departamentService.getEmployeeWithMaxSalary(departmentId);
        }
         @GetMapping(path = "/min-salary")
        public Employee minSalary (@RequestParam Integer departmentId) {
            return departamentService.getEmployeeWithMinSalary(departmentId);
        }
        @GetMapping(path = "/all")
        public Map<String, List<Employee>> allByDepId (@RequestParam (required = false) Integer departmentId) {
            return departamentService.getAll(departmentId);
        }


    }



