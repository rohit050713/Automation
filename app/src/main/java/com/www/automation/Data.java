package com.www.automation;

public class Data {
    String text;
    String id;

    public void Data(){

    }

    public Data(String text, String id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }
}
