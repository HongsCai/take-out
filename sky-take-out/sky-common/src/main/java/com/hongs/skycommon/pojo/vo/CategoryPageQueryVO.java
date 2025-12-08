package com.hongs.skycommon.pojo.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Schema(description = "分类分页查询VO")
public class CategoryPageQueryVO implements Serializable {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "类型: 1 菜品分类 2 套餐分类")
    private Integer type;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "顺序")
    private Integer sort;

    @Schema(description = "分类状态 0:禁用，1:启用")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private Long createUser;

    @Schema(description = "修改人")
    private Long updateUser;

}
