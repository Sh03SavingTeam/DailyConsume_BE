package com.shinhan.dailyconsume.service.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.map.RecommendDTO;
import com.shinhan.dailyconsume.dto.map.StoreDetailDTO;
import com.shinhan.dailyconsume.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendServiceImpl implements RecommendService{

	private final StoreRepository storeRepo;
	
	@Override
	public List<RecommendDTO> recommendStores() {
		List<StoreEntity> entities = storeRepo.findAll();
		List<RecommendDTO> storeList = new ArrayList<>();
		String currentYear = java.time.Year.now().toString();
		int currentYearInt = Integer.parseInt(currentYear);
		
		entities.stream().forEach((entity) -> {
			storeList.add(entityToDto(entity));
		});
		return storeList;
	}

	@Override
	public StoreDetailDTO getStoreDetail(String storeRegNum) {
		StoreEntity entity = storeRepo.findById(storeRegNum).orElse(null);
		return StoreDetailDTO.builder()
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
