package org.example.reflection;

import org.example.reflection.anntation.CustomAnnotation;
import org.example.reflection.subtype.Juice;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Method;
import java.sql.Ref;
import java.util.Set;

public class ReflectionMain {
    public static void main(String[] args) {
        System.out.println("reflection As Annotation Class");
        reflectionAsAnnnotationClass();

        System.out.println("---------------------------");
        System.out.println("reflection as subtype");
        reflectionAsSubtype();

        System.out.println("---------------------------");
        System.out.println("reflection as returnType");
        reflectionsAsReturnTypeMethod();
    }

    public static void reflectionAsAnnnotationClass(){
        Reflections reflections = new Reflections("org.example");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(CustomAnnotation.class);

        for(Class<?> clazz : classes){
            System.out.println(clazz.getName());
        }
    }

    public static void reflectionAsSubtype(){

        Reflections reflections = new Reflections("org.example");
        Set<Class<? extends Juice>> classes = reflections.getSubTypesOf(Juice.class);

        for(Class<?> clazz : classes){
            System.out.println(clazz.getName());
        }
    }

    public static void reflectionsAsReturnTypeMethod(){
        Reflections reflections = new Reflections("org.example", Scanners.MethodsReturn);
        Set<Method> methods = reflections.getMethodsReturn(String.class);

        for(Method method : methods){
            System.out.println(method.getDeclaringClass().getName() + " :"  + method.getName());
        }

    }


}
