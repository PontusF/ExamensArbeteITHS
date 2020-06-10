package com.company.exampleModels;

public class ExampleSecondNestedModel {
    private String name;
    private int secretInt;
    private ExampleFirstNestedModel firstNestedObject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSecretInt() {
        return secretInt;
    }

    public void setSecretInt(int secretInt) {
        this.secretInt = secretInt;
    }

    public ExampleFirstNestedModel getFirstNestedObject() {
        return firstNestedObject;
    }

    public void setFirstNestedObject(ExampleFirstNestedModel firstNestedObject) {
        this.firstNestedObject = firstNestedObject;
    }
}
