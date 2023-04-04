package org.pro.sky.employee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.employe25.employeers.Employer.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employe25.employeers.Employer.exceptions.EmployeeNotFoundException;
import pro.sky.employe25.employeers.Employer.exceptions.EmployeeStorageIsFullException;
import pro.sky.employe25.employeers.Employer.exceptions.IncorrectNameException;
import pro.sky.employe25.employeers.Employer.model.Employee;

import java.util.*;

import static pro.sky.employe25.employeers.Employer.model.Departament.DEPARTAMENT_MAP_ID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int maxEmployersNumbers = 10;
    private static List<Employee> employees = new ArrayList<>();

    static {
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

    }

    private final Map<Integer, Employee> employeeByHashCode = new HashMap<>();

    private int getEmployeeKey(String firstName, String lastName) {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public Employee add(String firstName, String lastName, float salary, int departamentId) {
//        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
//            throw new IncorrectNameException("Имя или фамилия не могут быть пустой");
//        }
//
//        // Данные сотрудников записываются с большой буквы
//        if (!firstName.equals(StringUtils.capitalize(StringUtils.lowerCase(firstName))) || !lastName.equals(StringUtils.capitalize(StringUtils.lowerCase(lastName)))) {
//            throw new IncorrectNameException(" ФИО должно начинаться с заглавной буквы");
//        }
//        // В переданной строке только буквы и только латинские
//        if (StringUtils.isAlpha(firstName) || StringUtils.isAlpha(lastName)) {
//            throw new IncorrectNameException("в имени и фамилии должны быть только буквы");
//        }
        checksNameAndSurname(firstName, lastName);

//

        if (employeeByHashCode.size() == maxEmployersNumbers) {
            throw new EmployeeStorageIsFullException(" Людей слишком много");
        }
        Employee employee = new Employee(firstName, lastName, salary, DEPARTAMENT_MAP_ID.get(departamentId));


        int employeeKey = getEmployeeKey(firstName, lastName);
        if (employeeByHashCode.containsKey(employeeKey)) {
            throw new EmployeeAlreadyAddedException(" такой тип уже есть");
        }
        employeeByHashCode.put(employeeKey, employee);

        return employee;
    }

    @Override

    public Employee find(String firstName, String lastName) {
        int employeeHashCode = getEmployeeKey(firstName, lastName);
        checksNameAndSurname(firstName, lastName);

        Employee employee = employeeByHashCode.get(employeeHashCode);

        if (employee == null) {
            throw new EmployeeNotFoundException(" при поиске сотрудник не найден.");
        }
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        checksNameAndSurname(firstName, lastName);
        int employeeHashCode = getEmployeeKey(firstName, lastName);
        Employee employee = employeeByHashCode.remove(employeeHashCode);
        if (employee == null) {
            throw new EmployeeNotFoundException(" при поиске сотрудник не найден.");
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    private void checksNameAndSurname(String firstName, String lastName) {
        checksNameException(firstName);
        checksNameException(lastName);

    }

    //  создаем общий метод на проверку имени
    private void checksNameException(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IncorrectNameException("Имя или фамилия не могут быть пустой");
        }

        // Данные сотрудников записываются с большой буквы
        if (!name.equals(StringUtils.capitalize(StringUtils.lowerCase(name)))) {
            throw new IncorrectNameException(" ФИО должно начинаться с заглавной буквы");
        }
        // В переданной строке только буквы и только латинские
        if (!StringUtils.isAlpha(name)) {
            throw new IncorrectNameException("в имени и фамилии должны быть только буквы");
        }
    }
}


