package app.mian.wangliwei.toolsproject.utils;

/**
 * EventBus Massage
 */

public class MessageEvent {
    private String message;

    public MessageEvent() {
        this.message = "From EventBus.";
    }

    public String getMessage(){
        return this.message;
    }
}
