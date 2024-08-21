package com.shinhan.dailyconsume.service;

import com.shinhan.dailyconsume.domain.ReviewEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.ReviewDTO;

public interface ReviewService {
	
	//1.리뷰등록
	Long register(ReviewDTO dto);

	
	default ReviewEntity dtoToEntity(ReviewDTO dto) {
		StoreEntity storeen= StoreEntity.builder().storeRegNum(dto.getStoreRegNum()).build();
		ReviewEntity reviewen = ReviewEntity.builder()
				.reviewId(dto.getReviewId())
				.rating(dto.getRating())
				.store(storeen)
				.build();
		
		return reviewen;
	}
	
	default ReviewDTO entityToDTO(ReviewEntity entity) {
		ReviewDTO dto = ReviewDTO.builder()
				.reviewId(entity.getReviewId())
				.rating(entity.getRating())
				.storeRegNum(entity.getStore().getStoreRegNum())
				.build();
		
		return dto;
	}
}
