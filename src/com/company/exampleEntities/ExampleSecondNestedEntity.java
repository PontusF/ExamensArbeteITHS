package com.company.exampleEntities;

import com.company.PontusConvertable;

public class ExampleSecondNestedEntity implements PontusConvertable {
    private String name;
    private int secretInt;
    private ExampleFirstNestedEntity firstNestedObject;

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

    public ExampleFirstNestedEntity getFirstNestedObject() {
        return firstNestedObject;
    }

    public void setFirstNestedObject(ExampleFirstNestedEntity firstNestedObject) {
        this.firstNestedObject = firstNestedObject;
    }
}
