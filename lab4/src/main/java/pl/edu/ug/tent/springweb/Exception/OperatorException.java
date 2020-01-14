package pl.edu.ug.tent.springweb.Exception;

public class OperatorException extends Exception{
    public OperatorException() { super(); }
    public OperatorException(String message) { super(message); }
    public OperatorException(String message, Throwable cause) { super(message, cause); }
    public OperatorException(Throwable cause) { super(cause); }
}
