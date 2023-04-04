package org.pro.sky.employeers.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pro.sky.employeers.exceptions.DepartmentSearchException;
import org.pro.sky.employeers.model.Departament;
import org.pro.sky.employeers.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.pro.sky.employeers.model.Departament.DEPARTAMENT_MAP_ID;

@ContextConfiguration(classes = {DepartamentServiceImp.class})
@ExtendWith(SpringExtension.class)
class DepartamentServiceImpTest {

    @Autowired
    private DepartamentService departamentService;
    @MockBean
    private EmployeeServiceImpl employeeService;
    //тест на успешность еплоера с МИН зп, успех
    @Test
    void getEmployeeWithMinSalary_success() {
        // Входные данные
        Integer departmentId = 1;
        // вводим переменные для будущего теста по мин и макс зп
        float maxSalary = 50000;
        float minSalary = 20000;
        Employee employeeWithMaxSalary = new Employee("Sergey", "Sergeev", maxSalary, Departament.DEPARTAMENT_MAP_ID.get(departmentId));
        Employee employeeWithMinSalary = new Employee("Igor", "Igorev", minSalary, Departament.DEPARTAMENT_MAP_ID.get(departmentId));
        // создаем лист куда вносим наших работников
        List<Employee> employees = List.of(employeeWithMaxSalary, employeeWithMinSalary);


//        //подготовка Ожидаемый результат
        // что говорят моки - когда - в сервисе принимаем всех = возвращаем емплоеров
        when(employeeService.getAll()).thenReturn(employees);


        // Тест и сравнение

        // тут сравню на макс и на мин селари типов, все ли возвращается и равно ли оно
        Employee actual = departamentService.getEmployeeWithMinSalary(departmentId);
        assertEquals(employeeWithMinSalary, actual);
        // тут я сразу сделал и на макс селари - но потом по видео попросили сделать в отдельном тесте - ниже сделано
        Employee actual2 = departamentService.getEmployeeWithMaxSalary(departmentId);
        assertEquals(employeeWithMaxSalary, actual2);
        assertTrue(minSalary < maxSalary);
    }

    //тест на успешность еплоера с мин зп, если депортамент не найден
    @Test
    void getEmployeeWithMinSalary_withDepartmentSearchException() {
        // Входные данные
        Integer departmentId = 1;
        // Ожидаемый результат
        // тут мы во всех возвращаем пустой лист
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Департамент не найден";
        // Тест
        Exception exception = assertThrows(DepartmentSearchException.class, () -> departamentService.getEmployeeWithMinSalary(departmentId));
        // собственно и сравнение наших месседжей на эксепшн
        assertEquals(expectedMessage, exception.getMessage());
    }

    //тест на успешность еплоера с МАКС зп, успех пишем ченджа нет
    @Test
    void getEmployeeWithMaxSalary_success() {
        // Входные данные
        Integer departmentId = 1;
        // вводим переменные для будущего теста по мин и макс зп
        float maxSalary = 50000;
        float minSalary = 20000;
        Employee employeeWithMaxSalary = new Employee("Sergey", "Sergeev", maxSalary, Departament.DEPARTAMENT_MAP_ID.get(departmentId));
        Employee employeeWithMinSalary = new Employee("Igor", "Igorev", minSalary, Departament.DEPARTAMENT_MAP_ID.get(departmentId));
        // создаем лист куда вносим наших работников
        List<Employee> employees = List.of(employeeWithMaxSalary, employeeWithMinSalary);
       //подготовка Ожидаемый результат
        // что говорят моки - когда - в сервисе принимаем всех = возвращаем емплоеров
        when(employeeService.getAll()).thenReturn(employees);

        // Тест и сравнение
        Employee actual2 = departamentService.getEmployeeWithMaxSalary(departmentId);
        assertEquals(employeeWithMaxSalary, actual2);
        assertTrue(minSalary < maxSalary);

    }

    //тест на успешность еплоера с МАКС зп, если депортамент не найден
    @Test
    void getEmployeeWithMaxSalary_withDepartmentSearchException() {
        // Входные данные
        Integer departmentId = 1;
        // Ожидаемый результат
        // тут мы во всех возвращаем пустой лист
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Департамент не найден";
        // Тест
        Exception exception = assertThrows(DepartmentSearchException.class, () -> departamentService.getEmployeeWithMaxSalary(departmentId));
        // собственно и сравнение наших месседжей на эксепшн
        assertEquals(expectedMessage, exception.getMessage());
    }



    // тест на успешность получения всех типов
    @Test
    void getAll_emptyResult() {
        // Входные данные
        Integer departmentId = 3;
        // Ожидаемый результат
        // когда берем всех сотрудников из сервиса - возвращаем пустоту
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        // Тест
        Map<String, List<Employee>> actualResult = departamentService.getAll(departmentId);
        // проверяем на правду - что пустой результат
        assertTrue(actualResult.isEmpty());

    }

    // тест на пустоту, если типов нет
    @Test
    void getAll_success() {
        // Входные данные
        Integer departmentId = 2;
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

        // Ожидаемый результат

        // когда берем всех сотрудников из сервиса - возвращаем емплоис см выше
        when(employeeService.getAll()).thenReturn(employees);
        // создаем мапу
        Map<String, List<Employee>> excpectedResultat = new HashMap<>();
        // закидываем в нашу мапу бухгалтеров 1,2,3. где ключ номер депортамента
        String departmentName = DEPARTAMENT_MAP_ID.get(departmentId).getName();
        excpectedResultat.put(departmentName, List.of(buchgaltery1,buchgaltery2,buchgaltery3));

        // Тест
        Map<String, List<Employee>> actualResult = departamentService.getAll(departmentId);
        assertEquals(excpectedResultat, actualResult);

    }
    @Test
    void getAll_NullDepartment() {
        Integer departmentId = null;
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

        // Ожидаемый результат

        // когда берем всех сотрудников из сервиса - возвращаем емплоис см выше
        when(employeeService.getAll()).thenReturn(employees);
        // создаем мапу
        Map<String, List<Employee>> excpectedResultat = new HashMap<>();
        // закидываем в нашу мапу бухгалтеров 1,2,3. где ключ номер депортамента

        excpectedResultat.put("It", List.of(it1,it2));
        excpectedResultat.put("Buchgaltery", List.of(buchgaltery1,buchgaltery2,buchgaltery3));
        excpectedResultat.put("managers", List.of(managers1,managers2,managers3, managers4));

        // Тест
        Map<String, List<Employee>> actualResult = departamentService.getAll(departmentId);
        assertEquals(excpectedResultat, actualResult);

    }

}