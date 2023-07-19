package com.example.oo_harjoitustyo;

public class BattleMsgContainer {
    public static enum MessageType {
        LEFT,
        RIGHT,
        TEXT,
    }

    private MessageType messageType;
    private String message;
    private Lutemon lutemon;


    public BattleMsgContainer() {}
    public BattleMsgContainer(String message, MessageType messageType, Lutemon lutemon) {
        this.message = message;
        this.messageType = messageType;
        this.lutemon = lutemon;
    }

    public Lutemon getLutemon() {
        return lutemon;
    }

    public void setLutemon(Lutemon lutemon) {
        this.lutemon = lutemon;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
