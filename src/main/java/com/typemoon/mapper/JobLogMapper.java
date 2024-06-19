package com.typemoon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.typemoon.entity.JobLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobLogMapper extends BaseMapper<JobLog> {

    List<String> listJobLogGroups();

}
