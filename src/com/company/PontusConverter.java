package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class PontusConverter {

    /* Example of forbidden fields format.
    String[] forbiddenFields = new String[]{ "id", "userId", "productId","serialVersionUID"};
     */

    //Stores information of Connections already made. Used to solve infitie loops.
    Map<Object,Object> backReferences = new HashMap<Object, Object>() {    };

    //used by users.
    public <T> T convert(Object originObject, Class<T> targetClass, String[] forbiddenFields) throws Exception {
        backReferences = new HashMap<Object, Object>() {    };
            return RecursiveConversion(originObject,targetClass,forbiddenFields);
    }



    private  <T> T RecursiveConversion(Object originObject, Class<T> targetClass, String[] forbiddenFields)
            throws IllegalAccessException,
            NoSuchMethodException,
            InstantiationException,
            InvocationTargetException {


        //The class of the pre-conversion object
        Class<?> originClass = originObject.getClass();

        //The methods of the pre-conversion object
        Method[] originMethods = originClass.getDeclaredMethods();

        //The resulting object from the conversion
        T targetObject = targetClass.getConstructor().newInstance();

        //The Fields of the resulting object
        Field[] targetFields = targetObject.getClass().getDeclaredFields();

        //Tell the program that we already accounted for conversion these objects
        addBackReference(originObject, targetObject);

        //For every field in the desired output class, convert if applicable.
        for (Field targetField : targetFields) {

            //Usually for safety reasons it is not allowed to use fields directly as we are about to do.
            targetField.setAccessible(true);

            //if the name of the field is in the "forbiddenFields" array, continue to the next field
            if (isForbiddenField(targetField, forbiddenFields)){
                targetField.setAccessible(false);
                continue;
            }

            //Return index of getter method  in the originObject with a name corresponding to the target field.
            //Returns -1 if no match found.
            int originMethodIndex = indexOfOriginMethod(targetField,originMethods);

            //if no match found, continue to next field.
            if (originMethodIndex==-1){
                targetField.setAccessible(false);
                continue;
            }

            Method originMethod = originMethods[originMethodIndex];
            originMethod.setAccessible(true);

            //If there already is information set in the resulting object field, continue to the next field
            if(targetReferenceTypeNotNull(targetField, targetObject)){
                targetField.setAccessible(false);
                targetField.setAccessible(false);
                continue;
            }

            //if the field is an object with additional fields. AKA if the field implements "PontusConvertable"
            else if(isNestedObject(originMethod)){
                Object nestedOriginObject = originMethod.invoke(originObject);
                Class nestedTargetClass = targetField.getType();

                if(isBackReference(nestedOriginObject)){
                    targetField.set(targetObject, gettargetBackReference(nestedOriginObject));
                    targetField.setAccessible(false);
                    targetField.setAccessible(false);
                    continue;
                }

                Object nestedTarget = RecursiveConversion(nestedOriginObject, nestedTargetClass, forbiddenFields);
                nestedTarget = nestedTargetClass.cast(nestedTarget);
                targetField.set(targetObject, nestedTarget);
                targetField.setAccessible(false);
                targetField.setAccessible(false);

            }
            //If of dataType Set
            else if (isSet(targetField)){
                Set nestedSet = (Set) originMethod.invoke(originObject);
                Set<Object> targetSet = new HashSet<>();
                for (Object nestedEntry: nestedSet) {
                    Object nestedOriginObject = originMethod.invoke(originObject);

                    ParameterizedType setType = (ParameterizedType)targetField.getGenericType();
                    Class<?> nestedTargetClass = (Class<?>) setType.getActualTypeArguments()[0];
                    if(isBackReference(nestedOriginObject)){
                        targetField.set(targetObject, gettargetBackReference(nestedOriginObject));
                        continue;
                    }
                    Object nestedTarget = RecursiveConversion(nestedEntry, nestedTargetClass, forbiddenFields);
                    nestedTarget = nestedTargetClass.cast(nestedTarget);
                    targetSet.add(nestedTarget);

                }
                targetField.set(targetObject, targetSet);
            }
            //If no special Cases happened above, set the resulting field using the get method of the origin object.
            else {
                targetField.set(    targetObject,     originMethod.invoke(originObject)   );
            }
            targetField.setAccessible(false);
            targetField.setAccessible(false);
        }
        return targetObject;
    }

    private  int indexOfOriginMethod(Field field, Method[] methods){
        for(int i=0; i < methods.length; i++){
            String triedMethodName = methods[i].getName();
            String triedPrefix = triedMethodName.substring(0, 3);

            String triedRemaining = triedMethodName.substring(3).toLowerCase();

            if(     triedPrefix.equals("get")   &&  triedRemaining.equals(field.getName().toLowerCase()) ){
                return i;
            }
        }
        return -1;
    }

    private  boolean isForbiddenField(Field field, String[] forbiddenFields){
        for(String forbidden:forbiddenFields) {
            if (field.getName().equals(forbidden)) {
                return true;
            }
        }
        return false;
    }

    private  boolean isNestedObject(Method originMethod){
        return PontusConvertable.class.isAssignableFrom(originMethod.getReturnType());
    }
    private  boolean originIsNull(Field field, Object originObject){
        try {
            if(field.get(originObject) == null){
                return true;
            }
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    private  boolean targetReferenceTypeNotNull(Field field, Object targetObject){
        if(field.getType().isPrimitive()){
            return false;
        }
        try {
            if(field.get(targetObject) != null){
                return true;
            }
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    private  boolean isSet(Field originField){
        if (originField.getType().equals(Set.class)){
            return true;
        }
        return false;
    }

    private void addBackReference(Object oldObj, Object newObj){
        backReferences.put(oldObj, newObj);
    }

    private boolean isBackReference(Object oldObj){
        return backReferences.containsKey(oldObj);
    }

    private Object gettargetBackReference(Object oldObj){
        return backReferences.get(oldObj);
    }

}
