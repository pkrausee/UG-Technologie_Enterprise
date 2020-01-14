package pkrause.proj2.result;

public class SingleResult<T> implements IResult<T> {
    private T result;
    private String message;
    private boolean success;

    public SingleResult(T result, String message, boolean success) {
        this.result = result;
        this.message = message;
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public boolean success() {
        return success;
    }
}
