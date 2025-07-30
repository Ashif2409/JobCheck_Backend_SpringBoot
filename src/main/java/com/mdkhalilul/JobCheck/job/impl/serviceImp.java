package com.mdkhalilul.JobCheck.job.impl;

import com.mdkhalilul.JobCheck.job.Job;
import com.mdkhalilul.JobCheck.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class serviceImp implements JobService {
    private final List<Job>jobs= new ArrayList<>();
    @Override
    public List<Job> findAllJobs() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
    jobs.add(job);
    }
}
