package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.ExceptionLog;
import com.typemoon.model.dto.ExceptionLogDTO;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.vo.ConditionVO;

public interface ExceptionLogService extends IService<ExceptionLog> {

    PageResultDTO<ExceptionLogDTO> listExceptionLogs(ConditionVO conditionVO);

}
