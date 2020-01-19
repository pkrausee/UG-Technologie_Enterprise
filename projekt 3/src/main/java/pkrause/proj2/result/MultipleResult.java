package pkrause.proj2.result;

import java.util.List;

public class MultipleResult<T> implements IResult<List<T>> {
    private List<T> result;
    private String message;
    private boolean success;

    public MultipleResult(List<T> result, String message, boolean success) {
        this.result = result;
        this.message = message;
        this.success = success;
    }

    public List<T> getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public boolean success() {
        return success;
    }
}
