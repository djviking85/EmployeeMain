package org.pro.sky.employeers.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {

    public EmployeeStorageIsFullException(String message) {
        super(message);

    }
}