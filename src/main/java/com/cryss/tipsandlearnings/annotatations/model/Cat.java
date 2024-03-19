package com.cryss.tipsandlearnings.annotatations.model;

import com.cryss.tipsandlearnings.annotatations.VeryImportant;

@VeryImportant
public class Cat {

    @ImportantString
    String name;
    int age;

    public Cat(String name) { this.name = name;}

    @RunImmediatly(times = 3)
    public void meow(){
        System.out.println ("Meow");
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
