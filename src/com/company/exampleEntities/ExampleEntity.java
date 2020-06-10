package com.company.exampleEntities;

import com.company.PontusConvertable;

public class ExampleEntity implements PontusConvertable {
    private String name;
    private ExampleFirstNestedEntity firstNestedObject;
    private String secretField;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExampleFirstNestedEntity getFirstNestedObject() {
        return firstNestedObject;
    }

    public void setFirstNestedObject(ExampleFirstNestedEntity firstNestedObject) {
        this.firstNestedObject = firstNestedObject;
    }

    public String getSecretField() {
        return secretField;
    }

    public void setSecretField(String secretField) {
        this.secretField = secretField;
    }
}
