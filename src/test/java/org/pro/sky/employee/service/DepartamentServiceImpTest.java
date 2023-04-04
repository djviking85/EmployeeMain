package org.pro.sky.employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.employe25.employeers.Employer.exceptions.DepartmentSearchException;
import pro.sky.employe25.employeers.Employer.model.Departament;
import pro.sky.employe25.employeers.Employer.model.Employee;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

        // Ожидаемый результат

        // Тест

    }

    //тест на успешность еплоера с МАКС зп, если депортамент не найден
    @Test
    void getEmployeeWithMaxSalary_withDepartmentSearchException() {
        // Входные данные

        // Ожидаемый результат

        // Тест

    }

    // тест на успешность получения всех типов
    @Test
    void getAll_success() {
        // Входные данные

        // Ожидаемый результат

        // Тест

    }

    // тест на пустоту, если типов нет
    @Test
    void getAll_noResult() {
        // Входные данные

        // Ожидаемый результат

        // Тест

    }

}