package Megapoly.Squares;
import Megapoly.*;

public class Lucky extends Square{
    private String message;
    private int operation;
    public Lucky(int position, String message, int operation) {
        super(position);
        this.message = message;
        this.operation = operation;
    }

    public String getMessage() {
        return message;
    }

    public int getOperation() {
        return operation;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
}
