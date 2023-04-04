package org.pro.sky.employee.model;

import java.util.Objects;

public class Employee {
    // создадим переменные, которые участвуют в конструкторе
    private String firstName;
    private String lastName;
    private float salary;
    private Departament departament;


    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }



    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }



    public String getFirstNameEmployer() {
        return firstName;
    }
    public String getLastNameEmployer() {
        return lastName;
    }

    public Employee (String firstName, String lastName, float salary, Departament departament) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departament = departament;
        this.salary = salary;

    }

    @Override
    public String toString() {
        return "  Имя:  " + this.firstName +
                ", Фамилия: " + this.lastName;
    }

       @Override
       public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != getClass()) return false;
          Employee employee = (Employee) o;
         return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
      }
    @Override
    public int hashCode () {
        return Objects.hash(firstName, lastName);
    }
}