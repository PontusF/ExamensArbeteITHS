package com.company.exampleModels;


public class ExampleFirstNestedModel {
    private String name;
    private ExampleModel exampleObject;
    private ExampleSecondNestedModel secondNestedObject;
    private String secretField;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExampleModel getExampleObject() {
        return exampleObject;
    }

    public void setExampleObject(ExampleModel exampleObject) {
        this.exampleObject = exampleObject;
    }

    public ExampleSecondNestedModel getSecondNestedObject() {
        return secondNestedObject;
    }

    public void setSecondNestedObject(ExampleSecondNestedModel secondNestedObject) {
        this.secondNestedObject = secondNestedObject;
    }

    public String getSecretField() {
        return secretField;
    }

    public void setSecretField(String secretField) {
        this.secretField = secretField;
    }
}
