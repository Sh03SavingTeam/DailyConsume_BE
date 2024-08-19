package com.shinhan.dailyconsume.service.map;

import java.util.List;

import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.map.RecommendDTO;
import com.shinhan.dailyconsume.dto.map.StoreDetailDTO;

public interface RecommendService {
	
	List<RecommendDTO> recommendStores();
	
	StoreDetailDTO getStoreDetail(String storeRegNum);

	default RecommendDTO entityToDto(StoreEntity entity) {
		return RecommendDTO.builder()
				.storeRegNum(entity.getStoreRegNum())
				.storeName(entity.getStoreName())
				.storeAddr(entity.getStoreAddr())
				.storePhone(entity.getStorePhone())
				.storeLatX(entity.getStoreLatX())
				.storeLonY(entity.getStoreLonY())
				.storeImg(entity.getStoreImg())
				.build();
	}
}
