package pkrause.proj2.result;

public interface IResult<T> {
    T getResult();

    String getMessage();

    boolean success();
}
