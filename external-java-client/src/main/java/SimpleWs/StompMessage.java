package SimpleWs;

public class StompMessage {
    String message;

    public StompMessage() {}

    public StompMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
