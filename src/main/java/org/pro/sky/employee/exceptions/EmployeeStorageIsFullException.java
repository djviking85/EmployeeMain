package org.pro.sky.employee.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {

    public EmployeeStorageIsFullException(String message) {
        super(message);

    }
}