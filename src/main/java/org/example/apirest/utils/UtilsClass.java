package org.example.apirest.utils;

import java.lang.reflect.Field;
import java.util.Collection;

public class UtilsClass {
    public static void updateFields(Object oldObject, Object objectToInsert) {
        Class<?> clazz = oldObject.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (Collection.class.isAssignableFrom(field.getType())) {
                    continue;
                }

                Object newValue = field.get(objectToInsert);
                if (newValue != null) {
                    field.set(oldObject, newValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
