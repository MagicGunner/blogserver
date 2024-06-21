package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Category;
import com.typemoon.model.dto.CategoryAdminDTO;
import com.typemoon.model.dto.CategoryDTO;
import com.typemoon.model.dto.CategoryOptionDTO;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.vo.CategoryVO;
import com.typemoon.model.vo.ConditionVO;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<CategoryDTO> listCategories();

    PageResultDTO<CategoryAdminDTO> listCategoriesAdmin(ConditionVO conditionVO);

    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO conditionVO);

    void deleteCategories(List<Integer> categoryIds);

    void saveOrUpdateCategory(CategoryVO categoryVO);

}
