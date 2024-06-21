package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.OperationLog;
import com.typemoon.model.dto.OperationLogDTO;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.vo.ConditionVO;

public interface OperationLogService extends IService<OperationLog> {

    PageResultDTO<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}
