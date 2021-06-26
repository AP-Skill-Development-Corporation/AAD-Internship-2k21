package com.example.firedb;

public class MyModel {
    String img,name,mail,number;

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getNumber() {
        return number;
    }

    public MyModel() {
    }

    public MyModel(String img, String name, String mail, String number) {
        this.img = img;
        this.name = name;
        this.mail = mail;
        this.number = number;
    }
}
