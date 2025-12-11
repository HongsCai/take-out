package com.hongs.skyserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongs.skycommon.pojo.dto.DishPageQueryDTO;
import com.hongs.skycommon.pojo.dto.DishSaveDTO;
import com.hongs.skycommon.pojo.entity.Dish;
import com.hongs.skycommon.pojo.vo.DishPageQueryVO;
import com.hongs.skycommon.result.PageResult;

import java.util.List;

/**
* @author Hongs
* @description 针对表【dish(菜品)】的数据库操作Service
* @createDate 2025-12-08 18:24:50
*/
public interface DishService extends IService<Dish> {

    /**
     * 保存菜品及对应口味
     * @param dishSaveDTO
     */
    void saveWithFlavor(DishSaveDTO dishSaveDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult<DishPageQueryVO> page(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatchByIds(List<Long> ids);
}
