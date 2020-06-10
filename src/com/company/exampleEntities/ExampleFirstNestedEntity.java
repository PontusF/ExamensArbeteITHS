package com.company.exampleEntities;

import com.company.PontusConvertable;

public class ExampleFirstNestedEntity implements PontusConvertable {
    private String name;
    private String secretField;
    private ExampleEntity exampleObject;
    private ExampleSecondNestedEntity secondNestedObject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretField() {
        return secretField;
    }

    public void setSecretField(String secretField) {
        this.secretField = secretField;
    }

    public ExampleEntity getExampleObject() {
        return exampleObject;
    }

    public void setExampleObject(ExampleEntity exampleObject) {
        this.exampleObject = exampleObject;
    }

    public ExampleSecondNestedEntity getSecondNestedObject() {
        return secondNestedObject;
    }

    public void setSecondNestedObject(ExampleSecondNestedEntity secondNestedObject) {
        this.secondNestedObject = secondNestedObject;
    }
}
