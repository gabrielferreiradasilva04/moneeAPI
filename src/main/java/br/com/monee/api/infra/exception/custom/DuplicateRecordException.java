package br.com.monee.api.infra.exception.custom;

public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException(String message){
        super(message);
    }
}
