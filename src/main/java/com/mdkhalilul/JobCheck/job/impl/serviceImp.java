package com.mdkhalilul.JobCheck.job.impl;

import com.mdkhalilul.JobCheck.job.Job;
import com.mdkhalilul.JobCheck.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public Job findJobById(Long id) {
        for(Job job:jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        Iterator<Job> iterator = jobs.iterator();
        while (iterator.hasNext()) {
            Job job = iterator.next();
            if (job.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Job updateJob(Long id,Job updatedJob) {
        for(Job job:jobs){
            if(job.getId().equals(id)){
                job.setLocation(updatedJob.getLocation());
                job.setTitle(updatedJob.getTitle());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                return job;
            }
        }
        return null;
    }

}
