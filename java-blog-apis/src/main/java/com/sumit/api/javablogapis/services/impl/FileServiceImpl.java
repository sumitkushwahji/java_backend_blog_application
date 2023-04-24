package com.sumit.api.javablogapis.services.impl;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.api.javablogapis.services.FileService;

@Service
public class FileServiceImpl implements FileService {


    //  String "path" representing the path where the file should be saved,
    //  MultipartFile "file" representing the uploaded file.
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
       
        //File Name
        String name = file.getOriginalFilename();
        // abc.png

        // Random name genrate file
        String randomID = UUID.randomUUID().toString();
        String fileName1 =randomID.concat(name.substring(name.lastIndexOf(".")));
        

        // Fullpath
        String filePath =path + File.separator + fileName1;

        // Create folder if not created
        File f =new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        // File copy
        Files.copy(file.getInputStream(),Paths.get(filePath));
        
        return name;
    }

    @Override
    public InputStream getResources(String path, String fileName) throws FileNotFoundException {
    
        String fullpath = path + File.separator +fileName;

        InputStream is= new FileInputStream(fullpath);

        // db logic to return inputstream
        return is;

        
    }
    
}
