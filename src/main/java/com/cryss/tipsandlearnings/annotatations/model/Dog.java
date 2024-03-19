package com.cryss.tipsandlearnings.annotatations.model;

public class Dog {
    String name;
    int age;

    public Dog(String name) {
        this.name = name;
    }

    public void bark(){
        System.out.println ("Au Au au");
    }

    public void eat(){
        System.out.println ("Munch");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
