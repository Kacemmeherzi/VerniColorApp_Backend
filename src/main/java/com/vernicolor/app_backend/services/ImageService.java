package com.vernicolor.app_backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveImage(MultipartFile imageFile) throws IOException {
        // Create the directory if it does not exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Ensure the directory is created
        }

        // Clean the original file name
        String originalFileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        String fileExtension = "";

        // Extract the file extension if available
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex != -1) {
            fileExtension = originalFileName.substring(dotIndex);
        }

        // Generate a new unique file name
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // Full file path for storing the image
        Path filePath = uploadPath.resolve(uniqueFileName);

        // Save the file to the directory
        Files.copy(imageFile.getInputStream(), filePath);

        // Return the unique filename so it can be used in generating the URL
        return uniqueFileName;
    }

    public String generateImageUrl(String fileName) {
        // Generate a URL for the uploaded image
      //  return ServletUriComponentsBuilder.fromCurrentContextPath()
            //    .path("/uploads/")
              //  .path(fileName)
             //   .toUriString();
        //for android emulator user kahaw
        String baseUrl = "http://10.0.2.2:8082/uploads/";
        return baseUrl + fileName;
    }
}
