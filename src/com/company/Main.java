package com.company;

import com.company.exampleEntities.ExampleEntity;
import com.company.exampleEntities.ExampleFirstNestedEntity;
import com.company.exampleEntities.ExampleSecondNestedEntity;
import com.company.exampleModels.ExampleFirstNestedModel;
import com.company.exampleModels.ExampleModel;
import com.company.exampleModels.ExampleSecondNestedModel;

public class Main {

    public static void main(String[] args) throws Exception {


        ExampleEntity exampleObject = new ExampleEntity();
        ExampleFirstNestedEntity firstNestedEntity = new ExampleFirstNestedEntity();
        ExampleSecondNestedEntity secondNestedEntity = new ExampleSecondNestedEntity();

        exampleObject.setName("this is the name of the entity");
        exampleObject.setFirstNestedObject(firstNestedEntity);
        exampleObject.setSecretField("This should never be copied");

        firstNestedEntity.setExampleObject(exampleObject);
        firstNestedEntity.setName("this is the name of the first nested entity");
        firstNestedEntity.setSecretField("This is super secret and must never be copied");
        firstNestedEntity.setSecondNestedObject(secondNestedEntity);

        secondNestedEntity.setFirstNestedObject(firstNestedEntity);
        secondNestedEntity.setName("Allan Turing");
        secondNestedEntity.setSecretInt(42);

        //===================================================

        PontusConverter converter = new PontusConverter();
        String[] forbiddenFields = new String[]{ "secretField", "secretInt"};
        ExampleModel resultingModelObject = converter.convert(exampleObject,ExampleModel.class, forbiddenFields);

        //===================================================

        System.out.println("ExampleEntity name: " + resultingModelObject.getName());

        System.out.println("ExampleFirstNestedEntity name: " + resultingModelObject.getFirstNestedObject().getName());

        String secondNestedObjectName = resultingModelObject.getFirstNestedObject().getSecondNestedObject().getName();
        System.out.println("SecondNestedModel name: " + secondNestedObjectName);

        System.out.println("ExampleEntity secret string after conversion: " + resultingModelObject.getSecretField());

        System.out.println("ExampleFirstNestedEntity secret string after conversion: " +
                resultingModelObject.getFirstNestedObject().getSecretField());

        int theSecretInt = resultingModelObject.getFirstNestedObject().getSecondNestedObject().getSecretInt();
        System.out.println("ExampleSecondNestedEntity secret int after conversion: "  + theSecretInt);

        ExampleModel model = new ExampleModel();
        ExampleFirstNestedModel firstNestedModel = new ExampleFirstNestedModel();
        ExampleSecondNestedModel secondNestedModel = new ExampleSecondNestedModel();

    }
}
