package com.shinhan.dailyconsume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.ReviewEntity;
import com.shinhan.dailyconsume.dto.ReviewDTO;
import com.shinhan.dailyconsume.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepository reviewRepo;
	
	@Override
	public Long register(ReviewDTO dto) {
		ReviewEntity newReviewEntity = reviewRepo.save(dtoToEntity(dto));
		return newReviewEntity.getReviewId();
	}
}
