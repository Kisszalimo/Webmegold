package com.unideb.error;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(long id) {
        super("Tanulo nem talalhato : " + id);
    }

}
