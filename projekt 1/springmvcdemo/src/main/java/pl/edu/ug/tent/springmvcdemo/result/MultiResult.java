package pl.edu.ug.tent.springmvcdemo.result;

import java.util.List;

public class MultiResult<TResult> implements Result<List<TResult>> {

    private String message;
    private boolean success;
    private List<TResult> result;

    public MultiResult(String message, boolean success, List<TResult> result) {
        this.message = message;
        this.success = success;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

    public List<TResult> getResult() {
        return result;
    }
}
