package pl.edu.ug.tent.springweb.Exception;

public class NumberException extends Exception{
    public NumberException() { super(); }
    public NumberException(String message) { super(message); }
    public NumberException(String message, Throwable cause) { super(message, cause); }
    public NumberException(Throwable cause) { super(cause); }
}

