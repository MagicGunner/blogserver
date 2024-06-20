package com.typemoon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.typemoon.entity.ArticleTag;
import com.typemoon.mapper.ArticleTagMapper;
import com.typemoon.service.ArticleTagService;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
