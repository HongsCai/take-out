package com.hongs.skycommon.pojo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


@Data
@Schema(description = "新增分类DTO")
public class CategoryUpdateInfoDTO implements Serializable {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "顺序")
    private Integer sort;
}
