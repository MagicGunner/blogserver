package com.typemoon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.typemoon.entity.Role;
import com.typemoon.model.dto.ResourceRoleDTO;
import com.typemoon.model.dto.RoleDTO;
import com.typemoon.model.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<ResourceRoleDTO> listResourceRoles();

    List<String> listRolesByUserInfoId(@Param("userInfoId") Integer userInfoId);

    List<RoleDTO> listRoles(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

}
