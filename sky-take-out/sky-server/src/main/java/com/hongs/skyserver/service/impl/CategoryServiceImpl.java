package com.hongs.skyserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hongs.skycommon.constant.StatusConstant;
import com.hongs.skycommon.context.BaseContext;
import com.hongs.skycommon.pojo.dto.CategoryPageQueryDTO;
import com.hongs.skycommon.pojo.dto.CategorySaveDTO;
import com.hongs.skycommon.pojo.dto.CategoryUpdateInfoDTO;
import com.hongs.skycommon.pojo.entity.Employee;
import com.hongs.skycommon.pojo.vo.CategoryPageQueryVO;
import com.hongs.skycommon.result.PageResult;
import com.hongs.skyserver.mapper.CategoryMapper;
import com.hongs.skycommon.pojo.entity.Category;
import com.hongs.skyserver.service.CategoryService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Hongs
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2025-12-08 12:12:19
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

    /**
     * 新增分类
     * @param categorySaveDTO
     */
    @Override
    public void save(CategorySaveDTO categorySaveDTO) {
        Category category = Category.builder()
                .id(categorySaveDTO.getId())
                .name(categorySaveDTO.getName())
                .sort(categorySaveDTO.getSort())
                .type(categorySaveDTO.getType())
                .status(StatusConstant.ENABLE)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId())
                .updateUser(BaseContext.getCurrentId())
                .build();
        this.save(category);
    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult<CategoryPageQueryVO> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        IPage<Category> iPage = new Page<>(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(categoryPageQueryDTO.getName()), Category::getName, categoryPageQueryDTO.getName())
                .eq(categoryPageQueryDTO.getType() != null, Category::getType, categoryPageQueryDTO.getType())
                .orderByDesc(Category::getSort);
        this.page(iPage, wrapper);
        List<CategoryPageQueryVO> records = iPage.getRecords().stream().map(category -> {
            CategoryPageQueryVO vo = new CategoryPageQueryVO();
            BeanUtils.copyProperties(category, vo);
            return vo;
        }).toList();
        return new PageResult<>(iPage.getTotal(), records);
    }

    /**
     * 启用禁用分类
     * @param status
     * @param id
     * @return
     */
    @Override
    public void updateStatus(Integer status, Long id) {
        LambdaUpdateWrapper<Category> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Category::getId, id).set(Category::getStatus, status);
        this.update(wrapper);
    }

    /**
     * 修改分类
     * @param categoryUpdateInfoDTO
     * @return
     */
    @Override
    public void updateInfo(CategoryUpdateInfoDTO categoryUpdateInfoDTO) {
        LambdaUpdateWrapper<Category> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Category::getId, categoryUpdateInfoDTO.getId())
                .set(Category::getName, categoryUpdateInfoDTO.getName())
                .set(Category::getSort, categoryUpdateInfoDTO.getSort())
                .set(Category::getUpdateTime, LocalDateTime.now())
                .set(Category::getUpdateUser, BaseContext.getCurrentId());
        this.update(wrapper);
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Override
    public List<Category> listByType(Integer type) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(type != null, Category::getType, type);
        return this.list(wrapper);
    }
}




