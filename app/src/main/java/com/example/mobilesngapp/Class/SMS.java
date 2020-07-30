package com.example.mobilesngapp.Class;

public class SMS {
    public String Content;
    public String Sender;
    public String Recipient;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

    public SMS(String content, String sender, String recipient) {
        Content = content;
        Sender = sender;
        Recipient = recipient;
    }
}
