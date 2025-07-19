package com.lolly.controller;

// THIS CODE IS BORROWED FROM GEEKSFORGEEKS.ORG

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/images")
public class FileController {

    // Base path for image file location
    String pathString = "src/main/resources/static/images/";

    // Endpoint to serve image file
    @GetMapping("")
    public ResponseEntity<Resource> getImage(String receiptUrl) throws Exception {
        // Path to the image file
        Path path = Paths.get(pathString + receiptUrl);
        // Load the resource
        Resource resource = new UrlResource(path.toUri());
        // Return ResponseEntity with image content type
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
