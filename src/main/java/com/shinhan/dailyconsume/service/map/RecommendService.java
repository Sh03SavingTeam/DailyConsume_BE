package com.shinhan.dailyconsume.service.map;

import java.util.List;

import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.map.RecommentDTO;

public interface RecommendService {
	
	List<RecommentDTO> recommendTest();

	default RecommentDTO entityToDto(StoreEntity entity) {
		return RecommentDTO.builder()
				.storeRegNum(entity.getStoreRegNum())
				.storeName(entity.getStoreName())
				.storeAddr(entity.getStoreAddr())
				.storePhone(entity.getStorePhone())
				.storeLatX(entity.getStoreLatX())
				.storeLonY(entity.getStoreLonY())
				.build();
	}
}
