package com.mdkhalilul.JobCheck.review.ReviewServImp;

import com.mdkhalilul.JobCheck.company.Company;
import com.mdkhalilul.JobCheck.company.companyInterface;
import com.mdkhalilul.JobCheck.company.companyServices;
import com.mdkhalilul.JobCheck.review.Review;
import com.mdkhalilul.JobCheck.review.reviewRepository;
import com.mdkhalilul.JobCheck.review.reviewServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class reviewServiceImp implements reviewServiceInterface {
    private final reviewRepository reviewI;
    private final companyInterface companyI;
    private final companyServices companyS;

    public reviewServiceImp(reviewRepository reviewI,companyInterface companyI,companyServices companyS) {
        this.reviewI = reviewI;
        this.companyI=companyI;
        this.companyS=companyS;
    }

    @Override
    public List<Review> getAllReview(Long id) {
        return reviewI.findByCompanyId(id);
    }

    @Override
    public boolean addReview(Long id, Review review) {
        Company company=companyI.getCompanyById(id);
        if(company!=null){
            review.setCompany(company);
            reviewI.save(review);
            return true;
        }
        return  false;
    }

    @Override
    public Review getReviewById(Long company_id,Long review_id) {
        List<Review>reviews=reviewI.findByCompanyId(company_id);
        if(reviews==null)
            return null;
        return reviews.stream().
                filter(review -> review.getId().equals(review_id))
                .findFirst().orElse(null);
    }

    @Override
    public Review updateReviewById(Long company_id, Long review_id, Review review) {
       List<Review>reviews=reviewI.findByCompanyId(company_id);
        if(reviews!=null){
           Review review1= reviews.stream()
                    .filter(rev->rev.getId().equals(review_id))
                    .findFirst().orElse(null);
           if(review1!=null){
               review1.setDescription(review.getDescription());
               review1.setTitle(review.getTitle());
               review1.setRating(review.getRating());
               reviewI.save(review1);
               return review1;
           }
           return  null;
        }
        return null;
    }

    @Override
    public boolean deleteReview(Long company_id, Long review_id) {
        Company company=companyI.getCompanyById(company_id);
        Review review=reviewI.findById(review_id).orElse(null);
        if(company!=null && review!=null){
            Company company1=review.getCompany();
            company1.getReviews().remove(review);
            review.setCompany(null);
            companyS.updateCompany(company1,company_id);
            reviewI.deleteById(review_id);
            return true;
        }
        return false;
    }

}
