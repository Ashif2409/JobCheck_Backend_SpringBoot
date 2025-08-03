package com.mdkhalilul.JobCheck.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{id}/review")
public class reviewController {
    private final reviewServiceInterface reviewService;

    public reviewController(reviewServiceInterface reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviewOfCompany(@PathVariable Long id){
        return new ResponseEntity<>(reviewService.getAllReview(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> postReview(@PathVariable Long id,@RequestBody Review review){
        boolean isFound=reviewService.addReview(id,review);
        if(isFound){
            return new ResponseEntity<>("Added Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to add", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{review_id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id,@PathVariable Long review_id){
        Review review=reviewService.getReviewById(id,review_id);
        if(review!=null){
            return new ResponseEntity<Review>(review,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{review_id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id,@PathVariable Long review_id,@RequestBody Review review){
        Review review1=reviewService.updateReviewById(id,review_id,review);
        if(review1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review1,HttpStatus.OK);
    }

    @DeleteMapping("/{review_id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id, @PathVariable Long review_id){
        boolean isDeleted= reviewService.deleteReview(id,review_id);
        if(isDeleted){
            return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
