package com.typemoon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.typemoon.entity.FriendLink;
import com.typemoon.mapper.FriendLinkMapper;
import com.typemoon.model.dto.FriendLinkAdminDTO;
import com.typemoon.model.dto.FriendLinkDTO;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.FriendLinkVO;
import com.typemoon.service.FriendLinkService;
import com.typemoon.util.BeanCopyUtil;
import com.typemoon.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public List<FriendLinkDTO> listFriendLinks() {
        List<FriendLink> friendLinks = friendLinkMapper.selectList(null);
        return BeanCopyUtil.copyList(friendLinks, FriendLinkDTO.class);
    }

    @Override
    public PageResultDTO<FriendLinkAdminDTO> listFriendLinksAdmin(ConditionVO conditionVO) {
        Page<FriendLink> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        Page<FriendLink> friendLinkPage = friendLinkMapper.selectPage(page, new LambdaQueryWrapper<FriendLink>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), FriendLink::getLinkName, conditionVO.getKeywords()));
        List<FriendLinkAdminDTO> friendLinkBackDTOs = BeanCopyUtil.copyList(friendLinkPage.getRecords(), FriendLinkAdminDTO.class);
        return new PageResultDTO<>(friendLinkBackDTOs, (int) friendLinkPage.getTotal());
    }

    @Override
    public void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO) {
        FriendLink friendLink = BeanCopyUtil.copyObject(friendLinkVO, FriendLink.class);
        this.saveOrUpdate(friendLink);
    }

}
