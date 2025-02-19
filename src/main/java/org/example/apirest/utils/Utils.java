package org.example.apirest.utils;

import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.UUID;

public class Utils {
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

    public static String extractExtension(String fileName) {
        String extension = "";
        if (fileName != null && fileName.contains(".")) {
            extension = fileName.substring(fileName.lastIndexOf("."));
        }
        return extension;
    }

    public static String extractExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return extractExtension(fileName);
    }

    public static String randomFileName(MultipartFile file){
        String originalFileName = file.getOriginalFilename();
        String uniqueName = UUID.randomUUID().toString();
        String extension = Utils.extractExtension(originalFileName);
        return uniqueName + extension;
    }
}
