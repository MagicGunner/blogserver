package com.typemoon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.typemoon.entity.UserAuth;
import com.typemoon.model.dto.UserAdminDTO;
import com.typemoon.model.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthMapper extends BaseMapper<UserAuth> {

    List<UserAdminDTO> listUsers(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

    Integer countUser(@Param("conditionVO") ConditionVO conditionVO);
}
