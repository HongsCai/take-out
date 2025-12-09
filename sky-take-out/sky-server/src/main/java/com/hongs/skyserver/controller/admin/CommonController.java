package com.hongs.skyserver.controller.admin;

import com.hongs.skycommon.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Tag(name = "通用接口")
@Slf4j
public class CommonController {


    @Operation(summary = "文件上传")
    @PostMapping("/update")
    public Result<String> update(MultipartFile file) {
        log.info("文件上传");
        return Result.success();
    }
}
