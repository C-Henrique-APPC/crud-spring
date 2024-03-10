
package com.curso.crudspring.exception;

public class RecordNotFoudException extends RuntimeException {

    public RecordNotFoudException(Long id) {
        super("Registro não encotrado com ID :" + id);
    }
}