package com.example.portfolio.domain.image;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void createTest(){
        String fileName = "fileName";
        String originalFileName = "originalFileName";
        String extension = ".extension";

        Image image = Image
            .builder(fileName, originalFileName, extension)
            .build();

        assertEquals(fileName, image.getFileName());
        assertEquals(originalFileName, image.getOriginalFileName());
        assertEquals(extension, image.getExtension());

    }
}