package com.lovatsis.sandbox.examples.java8;

//A custom bean
class CustomBean {
    private Integer value;

    public CustomBean(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}

