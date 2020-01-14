package pl.edu.ug.tent.springmvcdemo.result;

public class SingleResult<TResult> implements Result<TResult> {

    private String message;
    private boolean success;
    private TResult result;

    public SingleResult(String message, boolean success, TResult result) {
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

    public TResult getResult() {
        return result;
    }
}
