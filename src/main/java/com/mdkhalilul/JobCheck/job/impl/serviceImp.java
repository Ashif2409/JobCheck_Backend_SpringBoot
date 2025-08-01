package com.mdkhalilul.JobCheck.job.impl;

import com.mdkhalilul.JobCheck.job.Job;
import com.mdkhalilul.JobCheck.job.JobInterface;
import com.mdkhalilul.JobCheck.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class serviceImp implements JobService {
//    private final List<Job>jobs= new ArrayList<>();
//      private Long id=1L;
    private final JobInterface jobInterface;

    public serviceImp(JobInterface jobInterface) {
        this.jobInterface = jobInterface;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobInterface.findAll();
    }

    @Override
    public void createJob(Job job) {
        // Let JPA auto-generate the ID
        job.setId(null);
        jobInterface.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobInterface.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        if (jobInterface.existsById(id)) {
            jobInterface.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Job updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobInterface.findById(id);
        if (optionalJob.isPresent()) {
            Job existingJob = optionalJob.get();
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            existingJob.setLocation(updatedJob.getLocation());
            return jobInterface.save(existingJob);
        }
        return null;
    }

}
