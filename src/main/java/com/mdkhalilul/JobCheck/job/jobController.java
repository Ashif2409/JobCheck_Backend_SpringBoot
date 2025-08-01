package com.mdkhalilul.JobCheck.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class jobController {
    private final JobService jobServices;
    private  Long nextId=1L;
    public jobController(JobService jobServices) {
        this.jobServices = jobServices;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobServices.findAllJobs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job){
        job.setId(nextId);
        nextId++;
        jobServices.createJob(job);
        return new ResponseEntity<>("Job Created Successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobServices.findJobById(id);
        if(job==null){
            return new ResponseEntity<Job>((Job) null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Job>(job,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted=jobServices.deleteJobById(id);
        if(!deleted){
            return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Job deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable Long id,@RequestBody Job job){
        Job updatedJob= jobServices.updateJob(id,job);
        if(updatedJob==null){
            return new ResponseEntity<>((Job)null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Job>(updatedJob,HttpStatus.OK);
    }
}
