package com.example.demo;

public class Car {

    private Long id;
    private String num;
    private String color;
    private String mark;

    public String getNum() {
        return num;
    }

    public Long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getColor() {
        return color;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setColor(String color) {
        this.color = color;
    }
}