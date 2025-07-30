package com.mdkhalilul.JobCheck.job;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class jobController {
    private final JobService jobServices;

    public jobController(JobService jobServices) {
        this.jobServices = jobServices;
    }

    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobServices.findAllJobs();
    }

    @PostMapping("/jobs")
    public String addJob(@RequestBody Job job){
        jobServices.createJob(job);
        return "Successfully Job Added";
    }
}
