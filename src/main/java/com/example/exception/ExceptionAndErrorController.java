package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAndErrorController {
    /**
     * InvalidUsernameException handler.
     * @param e An InvalidUsernameException object.
     * @return the error message of the exception object and a status of {@code 400 Bad Request}.
     */
    @ExceptionHandler(InvalidUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidUsername(InvalidUsernameException e) {
        return e.getMessage();
    }

    /**
     * InvalidPasswordException handler.
     * @param e An InvalidPasswordException object.
     * @return the error message of the exception object and a status of {@code 400 Bad Request}.
     */
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidPassword(InvalidPasswordException e) {
        return e.getMessage();
    }
    
    /**
     * DuplicateUsernameException handler.
     * @param e An DuplicateUsernameException object.
     * @return the error message of the exception object and a status of {@code 409 Conflict}.
     */
    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String handleDuplicateUsername(DuplicateUsernameException e) {
        return e.getMessage();
    }

    /**
     * InvalidLoginException handler.
     * @param e An InvalidLoginException object.
     * @return the error message of the exception object and a status of {@code 401 Unauthorized}.
     */
    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String handleInvalidLogin(InvalidLoginException e) {
        return e.getMessage();
    }

    /**
     * InvalidMessageException handler.
     * @param e An InvalidMessageException object.
     * @return the error message of the exception object and a status of {@code 400 Bad Request}.
     */
    @ExceptionHandler(InvalidMessageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidMessage(InvalidMessageException e) {
        return e.getMessage();
    }
}
