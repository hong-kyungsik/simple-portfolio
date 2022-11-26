package com.example.portfolio.domain.image;

import com.example.portfolio.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void createTest(){
        String fileName = "fileName";
        String originalFileName = "originalFileName";
        User user = User.hiddenBuilder().build();

        Image image = Image
            .builder(fileName, originalFileName, user)
            .build();

        assertEquals(fileName, image.getFileName());
        assertEquals(originalFileName, image.getOriginalFileName());

    }
}