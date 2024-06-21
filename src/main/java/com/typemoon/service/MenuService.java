package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Menu;
import com.typemoon.model.dto.LabelOptionDTO;
import com.typemoon.model.dto.MenuDTO;
import com.typemoon.model.dto.UserMenuDTO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.IsHiddenVO;
import com.typemoon.model.vo.MenuVO;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<MenuDTO> listMenus(ConditionVO conditionVO);

    void saveOrUpdateMenu(MenuVO menuVO);

    void updateMenuIsHidden(IsHiddenVO isHiddenVO);

    void deleteMenu(Integer menuId);

    List<LabelOptionDTO> listMenuOptions();

    List<UserMenuDTO> listUserMenus();

}
