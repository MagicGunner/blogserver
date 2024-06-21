package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Role;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.RoleDTO;
import com.typemoon.model.dto.UserRoleDTO;
import com.typemoon.model.vo.ConditionVO;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<UserRoleDTO> listUserRoles();

    PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO);

//    void saveOrUpdateRole(RoleVO roleVO);

    void deleteRoles(List<Integer> ids);

}
