package com.CrudApp.Crud_App.exception;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not Found The User with id "+id);
    }
}
