package com.hongs.skyserver.controller.admin;

import com.hongs.skycommon.constant.MessageConstant;
import com.hongs.skycommon.exception.BaseException;
import com.hongs.skycommon.result.Result;
import com.hongs.skycommon.utils.AliOSSUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Tag(name = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private AliOSSUtil aliOSSUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Operation(summary = "文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传");
        try {
            String originalFilename = file.getOriginalFilename();
            // 生成文件名: UUID + 文件后缀
            String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 按年月文件上传路径
            String objectName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM")) + "/" + fileName;
            // 调用工具类上传文件
            String url = aliOSSUtil.upload(objectName, file.getBytes());
            return Result.success(url);
        } catch (IOException e) {
            throw new BaseException(MessageConstant.UPLOAD_FAILED);
        }
    }
}
