package com.example.portfolio.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ImageRepositoryTest {
    @Autowired
    private ImageRepository repository;

    private String fileName = "fileName";
    private String originalFileName = "originalFileName";
    private String extension = ".extension";

    private Image getImageForTest(){
        return Image
            .builder(fileName, originalFileName, extension)
            .build();
    }

    @Test
    @DisplayName("저장")
    void save(){
        Image image = getImageForTest();

        repository.save(image);

        assertNotNull(image.getId());
    }

    @Test
    @DisplayName("아이디로 조회")
    void findById(){
        Image image = getImageForTest();
        repository.save(image);

        Image foundImage = repository
            .findById(image.getId())
            .orElseThrow(RuntimeException::new);
        assertEquals(image.getFileName(), foundImage.getFileName());
        assertEquals(image.getOriginalFileName(), foundImage.getOriginalFileName());
        assertEquals(image.getExtension(), foundImage.getExtension());
    }

    @Test
    @DisplayName("삭제")
    void delete(){
        Image image = getImageForTest();
        repository.saveAndFlush(image);

        repository.delete(image);
        repository.flush();

        assertFalse(repository.findById(image.getId()).isPresent());
    }
}