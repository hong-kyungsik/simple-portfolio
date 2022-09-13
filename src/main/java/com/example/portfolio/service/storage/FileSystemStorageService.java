package com.example.portfolio.service.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file) {
        try{
            if(file.isEmpty()){
                throw new StorageException("file is empty");
            }
            Path destinationFile = this.rootLocation.resolve(
                Paths.get(file.getOriginalFilename())
            ).normalize().toAbsolutePath();
            if(!isValidRootPath(destinationFile)){
                throw new StorageException("Invalid path.");
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch(IOException ioException){
            throw new StorageException("Falied to store file.", ioException);
        }
    }

    private boolean isValidRootPath(Path destinationFilePath){
        return destinationFilePath.getParent().equals(this.rootLocation.toAbsolutePath());
    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try{
            Resource resource = new UrlResource(load(fileName).toUri());
            if(isRegularResource(resource)) return resource;
            throw new StorageException("Could not read file");
        }catch(MalformedURLException e){
            throw new StorageException("Could not read file");
        }
    }

    private boolean isRegularResource(Resource resource){
        return resource.exists()||resource.isReadable();
    }

    @Override
    public void init(){
        try{
            Files.createDirectories(rootLocation);
        }catch(IOException e){
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
