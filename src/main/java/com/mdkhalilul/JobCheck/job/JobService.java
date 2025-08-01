package com.mdkhalilul.JobCheck.job;

import java.util.List;

public interface JobService {
     List<Job>findAllJobs();
     void createJob(Job job);

     Job findJobById(Long id);

     boolean deleteJobById(Long id);

     Job updateJob(Long id,Job job);
}
