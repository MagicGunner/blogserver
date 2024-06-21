package com.typemoon.controller;

import com.typemoon.annotation.OptLog;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.RoleDTO;
import com.typemoon.model.dto.UserRoleDTO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.ResultVO;
import com.typemoon.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.typemoon.constant.OptTypeConstant.DELETE;

@Api(tags = "角色模块")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询用户角色选项")
    @GetMapping("/admin/users/role")
    public ResultVO<List<UserRoleDTO>> listUserRoles() {
        return ResultVO.ok(roleService.listUserRoles());
    }


    @ApiOperation(value = "查询角色列表")
    @GetMapping("/admin/roles")
    public ResultVO<PageResultDTO<RoleDTO>> listRoles(ConditionVO conditionVO) {
        return ResultVO.ok(roleService.listRoles(conditionVO));
    }

//    @OptLog(optType = SAVE_OR_UPDATE)
//    @ApiOperation(value = "保存或更新角色")
//    @PostMapping("/admin/role")
//    public ResultVO<?> saveOrUpdateRole(@RequestBody @Valid RoleVO roleVO) {
//        roleService.saveOrUpdateRole(roleVO);
//        return ResultVO.ok();
//    }

    @OptLog(optType = DELETE)
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/admin/roles")
    public ResultVO<?> deleteRoles(@RequestBody List<Integer> roleIdList) {
        roleService.deleteRoles(roleIdList);
        return ResultVO.ok();
    }
}
