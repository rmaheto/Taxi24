package com.codemaniac.taxi.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id){

        super("entity with id: "+id +" not found");
    }
}
