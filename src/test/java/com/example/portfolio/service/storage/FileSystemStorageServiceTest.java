package com.example.portfolio.service.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileSystemStorageServiceTest {
    private StorageProperties properties = new StorageProperties();
    private FileSystemStorageService service;

    @BeforeEach
    void init(){
        service = new FileSystemStorageService(properties);
        service.init();
    }

    private MockMultipartFile createMockFileForTest(){
        return new MockMultipartFile(
            "temp",
            "temp.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Temp text file for test".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void store() {
        MockMultipartFile file = createMockFileForTest();
        service.store(file);
        assertThat(service.load(file.getOriginalFilename())).exists();
    }

}