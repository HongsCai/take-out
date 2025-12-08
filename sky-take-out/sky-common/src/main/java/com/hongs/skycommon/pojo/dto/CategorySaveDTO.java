package com.hongs.skycommon.pojo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


@Data
@Schema(description = "新增分类DTO")
public class CategorySaveDTO implements Serializable {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "类型：1 菜品分类 2 套餐分类")
    private Integer type;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "顺序")
    private Integer sort;
}
