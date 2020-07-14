package com.upgrad.proman.service.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class UnauthorizedException extends Exception{
    private static String code;

    public static String getCode() {
        return code;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    private static String errorMessage;

    public UnauthorizedException(final String code, final String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    @Override
    public void printStackTrace(){
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintWriter w){
        super.printStackTrace(w);
    }

    @Override
    public void printStackTrace(PrintStream s){
        super.printStackTrace(s);
    }
}
