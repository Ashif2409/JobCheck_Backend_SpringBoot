package com.mdkhalilul.JobCheck.review;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface reviewServiceInterface {
    List<Review> getAllReview(Long id);
    boolean addReview(Long id,Review review);
    Review getReviewById(Long company_id,Long review_id);
    Review updateReviewById(Long company_id,Long review_id,Review review);
    boolean deleteReview(Long company_id,Long review_id);
}
