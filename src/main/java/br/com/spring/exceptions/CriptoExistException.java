package br.com.spring.exceptions;

public class CriptoExistException extends Exception {
    public CriptoExistException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;
}
