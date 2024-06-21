package com.typemoon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.typemoon.entity.RoleMenu;
import com.typemoon.mapper.RoleMenuMapper;
import com.typemoon.service.RoleMenuService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
