package com.unideb.error;

import java.util.Set;

public class StudentUnSupportedFieldPatchException extends RuntimeException {

    public StudentUnSupportedFieldPatchException(Set<String> keys) {
        super("A " + keys.toString() + " mezo frissitese nem lehetseges.");
    }

}
