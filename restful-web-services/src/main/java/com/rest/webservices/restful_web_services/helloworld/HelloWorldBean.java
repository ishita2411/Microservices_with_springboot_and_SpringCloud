package com.rest.webservices.restful_web_services.helloworld;

public class HelloWorldBean {
    private String text;

    public HelloWorldBean(String text) {
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "text='" + text + '\'' +
                '}';
    }
}
