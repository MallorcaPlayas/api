package org.example.apirest.controller.validators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirest.error.file_exceptions.InvalidFileException;
import org.example.apirest.error.file_exceptions.InvalidFileExtensionException;
import org.example.apirest.error.file_exceptions.InvalidFileMimeTypeException;
import org.example.apirest.error.file_exceptions.InvalidFileSizeException;
import org.example.apirest.utils.Utils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileValidator implements Validator {
    private List<String> allowedMime;
    private List<String> allowedExtension;
    private Long maxSize;
    private Long minSize;

    @Override
    public boolean validate(Predicate<Object[]> callBack,Object... files){
        return this.validate(files) && callBack.test(files);
    }

    @Override
    public boolean validate(Object... objects) {
        MultipartFile[] files = (MultipartFile[]) objects;
        return Arrays.stream(files)
                .allMatch(file ->
                        emptyValidate(file) &&
                        mimeValidate(file) &&
                        extensionValidate(file) &&
                        maxSizeValidate(file) &&
                        minSizeValidate(file)
                );
    }

    private boolean maxSizeValidate(MultipartFile file) {
        if (maxSize == null) return true;
        return file.getSize() <= maxSize;
    }

    private boolean emptyValidate(MultipartFile file) {
        return file != null && !file.isEmpty();
    }

    private boolean extensionValidate(MultipartFile file) {
        if (allowedExtension == null) return true;
        String extension = Utils.extractExtension(file.getOriginalFilename());
        return extension != null && !extension.isEmpty() && allowedExtension.contains(extension.toLowerCase());
    }

    private boolean mimeValidate(MultipartFile file) {
        if (allowedMime == null) return true;

        String contentType = file.getContentType();
        return contentType != null && allowedMime.contains(contentType.toLowerCase());
    }

    private boolean minSizeValidate(MultipartFile file) {
        if (minSize == null) return true;
        return file.getSize() >= minSize;
    }
}
