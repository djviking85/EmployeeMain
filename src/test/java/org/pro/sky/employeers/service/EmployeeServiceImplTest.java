package org.pro.sky.employeers.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pro.sky.employeers.exceptions.EmployeeAlreadyAddedException;
import org.pro.sky.employeers.exceptions.EmployeeNotFoundException;
import org.pro.sky.employeers.exceptions.EmployeeStorageIsFullException;
import org.pro.sky.employeers.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.pro.sky.employeers.model.Departament.DEPARTAMENT_MAP_ID;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void add_success() {
        Employee managers2 = new Employee("Sam", "Smith", 65000, DEPARTAMENT_MAP_ID.get(3));
        Employee expectedEmpl = managers2;
        Employee actualEmpl = employeeService.add("Sam", "Smith", 65000,3);
        assertEquals(expectedEmpl,actualEmpl);

    }


    @Test
    void add_alreadyAdded() {
        String expectedMessage = " такой тип уже есть";

        Exception exception = assertThrows(EmployeeAlreadyAddedException.class,
                () -> {
                    employeeService.add("Алекс", "Сигурдсон", 200000,1);
                    employeeService.add("Алекс", "Сигурдсон", 200000,1);
                }
        );
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void remove_success() {
        Employee managers2 = new Employee("Sam", "Smith", 65000, DEPARTAMENT_MAP_ID.get(3));
        Employee expectedEmpl = managers2;

        employeeService.add("Sam", "Smith", 65000,3);
        Employee actualEmpl = employeeService.remove("Sam", "Smith");
        assertEquals(expectedEmpl,actualEmpl);

    }

    @Test
    void remove_employeeNotFound() {
        String expectedMessage = " при поиске сотрудник не найден.";

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> {
                    employeeService.remove("Иван", "Ионов");
                }
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void find_success() {
        Employee buchgaltery2 = new Employee("Silvia", "Nilson", 35000, DEPARTAMENT_MAP_ID.get(2));

        Employee expectedEmployee = buchgaltery2;
        employeeService.add("Silvia", "Nilson", 35000, 2);
        Employee actualEmployee = employeeService.find("Silvia", "Nilson");

        assertEquals(expectedEmployee, actualEmployee);

    }


    @Test
    void getAll_success() {
        List<Employee> employees = new ArrayList<>();

        Employee it1 = new Employee("Алекс", "Сигурдсон", 200000, DEPARTAMENT_MAP_ID.get(1));
        Employee it2 = new Employee("Sam", "Schott", 150000, DEPARTAMENT_MAP_ID.get(1));

        Employee buchgaltery1 = new Employee("Eva", "Brown", 25000, DEPARTAMENT_MAP_ID.get(2));
        Employee buchgaltery2 = new Employee("Silvia", "Nilson", 35000, DEPARTAMENT_MAP_ID.get(2));
        Employee buchgaltery3 = new Employee("Mira", "O'Hara", 45000, DEPARTAMENT_MAP_ID.get(2));

        Employee managers1 = new Employee("Frank", "Blue", 60000, DEPARTAMENT_MAP_ID.get(3));
        Employee managers2 = new Employee("Sam", "Smith", 65000, DEPARTAMENT_MAP_ID.get(3));
        Employee managers3 = new Employee("Fill", "Varrant", 75000, DEPARTAMENT_MAP_ID.get(3));
        Employee managers4 = new Employee("Ed", "Hollywood", 101000, DEPARTAMENT_MAP_ID.get(3));

        employees.add(it1);
        employees.add(it2);

        employees.add(buchgaltery1);
        employees.add(buchgaltery2);
        employees.add(buchgaltery3);

        employees.add(managers1);
        employees.add(managers2);
        employees.add(managers3);
        employees.add(managers4);


        List<Employee> expectedEmployees = employees;
        List<Employee> actualEmployees =  employeeService.getAll();

        assertEquals(expectedEmployees, actualEmployees);


    }
}