package pl.edu.ug.tent.springmvcdemo.result;

public interface Result<T> {
    String getMessage();

    boolean getSuccess();

    T getResult();
}
