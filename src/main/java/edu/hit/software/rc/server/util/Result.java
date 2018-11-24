package edu.hit.software.rc.server.util;

public class Result<S, F> {
    private final Status status;
    private final Object value;

    public Result(Status status, Object value){
        this.status = status;
        this.value = value;
    }

    public enum Status{
        OK, ERR
    }
}
