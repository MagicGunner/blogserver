package com.typemoon.service.impl;

import com.typemoon.entity.UserRole;
import com.typemoon.mapper.UserRoleMapper;
import com.typemoon.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
