package com.mdkhalilul.JobCheck.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInterface extends JpaRepository<Job,Long> {
}
