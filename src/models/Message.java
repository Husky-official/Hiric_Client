package models;

/**
 * @author : Abijuru Seth
 * @description : A model to represents an entity of a message;
 */

public class Message {
    private Integer messageId;
    private String messageType;
    private String messageContent;
    private Integer originalMessage;
    private Integer sender;
    private Integer receiver;
    private String sentAt;

    public Integer getOriginalMessage() {
        return originalMessage;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setOriginalMessage(Integer originalMessage) {
        this.originalMessage = originalMessage;
    }

    public Message() {}

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
}
