package com.typemoon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.typemoon.entity.RoleResource;
import com.typemoon.mapper.RoleResourceMapper;
import com.typemoon.service.RoleResourceService;
import org.springframework.stereotype.Service;

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

}
