package com.mdkhalilul.JobCheck.job;

import java.util.List;

public interface JobService {
     List<Job>findAllJobs();
     void createJob(Job job);
}
