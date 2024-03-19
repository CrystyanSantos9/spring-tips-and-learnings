package com.cryss.tipsandlearnings.annotatations.model;

import com.cryss.tipsandlearnings.annotatations.VeryImportant;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {

    @Test
    public void testandoAnnotations() throws InvocationTargetException, IllegalAccessException {
        Cat myCat = new Cat ("Fedo");
        Dog dog = new Dog("Tuf");


//        if(dog.getClass ().isAnnotationPresent (VeryImportant.class)){
//            System.out.println ("This thing is very important");
//        }else {
//            System.out.println ("This thing is not very important");
//        }

        for(Method method: myCat.getClass ().getDeclaredMethods ()){
            if(method.isAnnotationPresent (RunImmediatly.class)){
                RunImmediatly annotation = method.getAnnotation (RunImmediatly.class);
                for(int i = 0; i< annotation.times (); i++){
                    method.invoke (myCat);
                }

            }
        }

        for(Field field: myCat.getClass ().getDeclaredFields ()){

            if(field.isAnnotationPresent (ImportantString.class)){
                Object objectValue = field.get (myCat);

                if(objectValue instanceof String stringValue){
                    System.out.println (stringValue.toUpperCase ());
                }
            }
        }

    }

}