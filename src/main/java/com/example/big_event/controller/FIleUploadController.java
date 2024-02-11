package com.example.big_event.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.big_event.pojo.Result;
import com.example.big_event.utils.AliOssUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Tag(name = "文件上传")
@AllArgsConstructor
public class FIleUploadController {

    private final AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile multipartFile) throws IOException, ClientException {

        String originalFilename = multipartFile.getOriginalFilename();

        String fileName = null;
        if (originalFilename != null) {
            fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String urlPath = aliOssUtil.upload(multipartFile.getBytes(), fileName);
        return Result.success(urlPath);
    }
}
