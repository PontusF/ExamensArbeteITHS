package com.company.exampleModels;

import com.company.PontusConvertable;

public class ExampleModel  {
    private String name;
    private ExampleFirstNestedModel firstNestedObject;
    private String secretField;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExampleFirstNestedModel getFirstNestedObject() {
        return firstNestedObject;
    }

    public void setFirstNestedObject(ExampleFirstNestedModel firstNestedObject) {
        this.firstNestedObject = firstNestedObject;
    }

    public String getSecretField() {
        return secretField;
    }

    public void setSecretField(String secretField) {
        this.secretField = secretField;
    }
}
