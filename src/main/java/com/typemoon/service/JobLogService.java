package com.typemoon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.JobLog;
import com.typemoon.model.dto.JobLogDTO;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.vo.JobLogSearchVO;

import java.util.List;


public interface JobLogService extends IService<JobLog> {

    PageResultDTO<JobLogDTO> listJobLogs(JobLogSearchVO jobLogSearchVO);

    void deleteJobLogs(List<Integer> ids);

    void cleanJobLogs();

    List<String> listJobLogGroups();

}
