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

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileValidator implements Validator<MultipartFile>{
    private List<String> allowedMime;
    private List<String> allowedExtension;
    private Long maxSize;
    private Long minSize;

    @Override
    public void validate(MultipartFile file) {
        emptyValidate(file);
        mimeValidate(file);
        extensionValidate(file);
        maxSizeValidate(file);
        minSizeValidate(file);
    }

    private void maxSizeValidate(MultipartFile file){
        if (maxSize == null) return;
        if (file.getSize() > maxSize) throw new InvalidFileSizeException(maxSize + " MB " , String.valueOf(file.getSize()));
    }

    private void emptyValidate(MultipartFile file){
        if (file == null || file.isEmpty()) throw new InvalidFileException(" null or empty file provided ");
    }

    private void extensionValidate(MultipartFile file){
        if (allowedExtension == null) return ;

        String extension = Utils.extractExtension(file.getOriginalFilename());
        if (extension == null || extension.isEmpty() || !allowedExtension.contains(extension.toLowerCase())) {
            throw new InvalidFileExtensionException(extension);
        }
    }

    private void mimeValidate(MultipartFile file){
        if (allowedMime == null) return ;

        String contentType = file.getContentType();
        if (contentType == null || !allowedMime.contains(contentType.toLowerCase())) {
            throw new InvalidFileMimeTypeException(contentType);
        }
    }

    private void minSizeValidate(MultipartFile file){
        if(minSize == null) return;
        if (file.getSize() < minSize) throw new InvalidFileSizeException(" 10 MB " , String.valueOf(file.getSize()));
    }
}
