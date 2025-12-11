package com.hongs.skyserver.controller.admin;


import com.hongs.skycommon.pojo.dto.DishPageQueryDTO;
import com.hongs.skycommon.pojo.dto.DishSaveDTO;
import com.hongs.skycommon.pojo.vo.DishPageQueryVO;
import com.hongs.skycommon.result.PageResult;
import com.hongs.skycommon.result.Result;
import com.hongs.skyserver.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜品管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Tag(name = "菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dishSaveDTO
     * @return
     */
    @Operation(summary = "新增菜品")
    @PostMapping
    public Result save(@RequestBody DishSaveDTO dishSaveDTO) {
        log.info("新增菜品: {}", dishSaveDTO);
        dishService.saveWithFlavor(dishSaveDTO);
        return Result.success();
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @Operation(summary = "菜品分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询: {}", dishPageQueryDTO);
        PageResult<DishPageQueryVO> pageResult = dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

}
