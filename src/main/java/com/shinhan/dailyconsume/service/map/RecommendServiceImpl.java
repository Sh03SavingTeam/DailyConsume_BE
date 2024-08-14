package com.shinhan.dailyconsume.service.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.map.RecommentDTO;
import com.shinhan.dailyconsume.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendServiceImpl implements RecommendService{

	private final StoreRepository storeRepo;
	
	@Override
	public List<RecommentDTO> recommendTest() {
		List<StoreEntity> entities = storeRepo.findAll();
		List<RecommentDTO> storeList = new ArrayList<>();
		entities.stream().forEach((entity) -> {
			storeList.add(entityToDto(entity));
		});
		return storeList;
	}

}
