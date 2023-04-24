package com.sumit.api.javablogapis.services;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    String uploadImage(String path, MultipartFile file) throws IOException;
    InputStream getResources(String path, String fileName) throws FileNotFoundException;
}
