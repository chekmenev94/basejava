package com.urise.webapp;

import com.urise.webapp.model.Resume;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        System.out.println(field.getName());
        field.setAccessible(true);
        System.out.println(field.get(r));
        field.set(r, "new uuid");
        System.out.println(r);
//        вызвать метод через отражение toString
        Method method = r.getClass().getMethod("toString");
        Object metod1 = method.invoke(r);
        System.out.println(metod1);
    }
}
