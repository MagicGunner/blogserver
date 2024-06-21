package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Job;
import com.typemoon.model.dto.JobDTO;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.vo.JobRunVO;
import com.typemoon.model.vo.JobSearchVO;
import com.typemoon.model.vo.JobStatusVO;
import com.typemoon.model.vo.JobVO;

import java.util.List;

public interface JobService extends IService<Job> {

    void saveJob(JobVO jobVO);

    void updateJob(JobVO jobVO);

    void deleteJobs(List<Integer> tagIds);

    JobDTO getJobById(Integer jobId);

    PageResultDTO<JobDTO> listJobs(JobSearchVO jobSearchVO);

    void updateJobStatus(JobStatusVO jobStatusVO);

    void runJob(JobRunVO jobRunVO);

    List<String> listJobGroups();

}
