package com.person.pojo;

//继承person
public class Man extends Person {
    public String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Man() { }

    public Man(String name, int age, String gender) {
        super(name, age);
        this.gender = gender;
    }

    public Man(String gender) {
        this.gender = gender;
    }

    public void doSomeThings() {
        System.out.println("I am man!");

    }
}
