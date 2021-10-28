package com.emrevarol.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {
    private final HttpServletRequest request;
    private final Path root = Paths.get("src/main/resources/static");

    public String saveFile(MultipartFile file) {
        String path = generateFileName(file);
        if (!file.isEmpty()) {
            try {
                Files.copy(file.getInputStream(), this.root.resolve(path));
            } catch (Exception e) {
                throw new IllegalArgumentException("File Upload Error:" + e.getMessage());
            }
        }
        return path;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }
}