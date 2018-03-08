package com.lovatsis.sandbox.examples.validation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Min(2)
    @Max(150)
    private Integer age;

    @URL(protocol = "http", groups = {ExtendedValidationGroup.class})
    private String webpage;

    public Person(String name, Integer age, String webpage) {
        setName(name);
        setAge(age);
        setWebpage(webpage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }
}