package com.typemoon.quartz;

import com.typemoon.entity.Job;
import com.typemoon.util.JobInvokeUtil;
import org.quartz.JobExecutionContext;

public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
