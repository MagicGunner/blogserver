package com.typemoon.strategy.context;

import com.typemoon.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

import static com.typemoon.enums.UploadModeEnum.getStrategy;

@Service
public class UploadStrategyContext {

    @Value("${upload.mode}")
    private String uploadMode;

    private final Map<String, UploadStrategy> uploadStrategyMap;

    public UploadStrategyContext(Map<String, UploadStrategy> uploadStrategyMap) {
        this.uploadStrategyMap = uploadStrategyMap;
    }

    public String executeUploadStrategy(MultipartFile file, String path) {
        return uploadStrategyMap.get(getStrategy(uploadMode)).uploadFile(file, path);
    }

    public String executeUploadStrategy(String fileName, InputStream inputStream, String path) {
        return uploadStrategyMap.get(getStrategy(uploadMode)).uploadFile(fileName, inputStream, path);
    }

}
