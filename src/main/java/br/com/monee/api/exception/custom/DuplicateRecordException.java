package br.com.monee.api.exception.custom;

public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException(String message){
        super(message);
    }
}
