package com.logey.aimin.ai.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    void upload(MultipartFile file);
}
