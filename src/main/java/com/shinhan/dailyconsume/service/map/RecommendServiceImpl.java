package com.shinhan.dailyconsume.service.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.MenuEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.map.HomePayHistroyDTO;
import com.shinhan.dailyconsume.dto.map.MenuDTO;
import com.shinhan.dailyconsume.dto.map.RecommendDTO;
import com.shinhan.dailyconsume.dto.map.StoreDetailDTO;
import com.shinhan.dailyconsume.dto.map.WeeklyConsumeProjection;
import com.shinhan.dailyconsume.dto.store.StoreDTO;
import com.shinhan.dailyconsume.repository.MenuRepository;
import com.shinhan.dailyconsume.repository.PayHistoryRepository;
import com.shinhan.dailyconsume.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendServiceImpl implements RecommendService{

	private final StoreRepository storeRepo;
	private final MenuRepository menuRepo;
	private final PayHistoryRepository payHistoryRepo;
	
	Double longitude = 126.92270138644199;
	Double latitude = 37.55936310336185;
	Double distance = 500.0;
	
	@Override
	public List<RecommendDTO> recommendStores() {
		List<StoreEntity> entities = storeRepo.findAll();
		List<RecommendDTO> storeList = new ArrayList<>();
		
		entities.stream().forEach((entity) -> {
			RecommendDTO recommendStore = entityToDto(entity);
			recommendStore.setCate(entity.getStoreCate().getCateName());
			storeList.add(recommendStore);
		});
		return storeList;
	}

	@Override
	public StoreDetailDTO getStoreDetail(String storeRegNum) {
		StoreEntity entity = storeRepo.findById(storeRegNum).orElse(null);
		List<MenuEntity> menuEntityList = menuRepo.findByStoreInfo(entity);
		List<MenuDTO> dtoList = new ArrayList<>();
		
		for(MenuEntity menuEntity: menuEntityList) {
			dtoList.add(menuEntityToDto(menuEntity));
		}
		System.out.println("====================menu: " + dtoList);
		StoreDetailDTO dto = StoreDetailDTO.builder()
				.storeRegNum(entity.getStoreRegNum())
				.storeName(entity.getStoreName())
				.storeAddr(entity.getStoreAddr())
				.storePhone(entity.getStorePhone())
				.storeLatX(entity.getStoreLatX())
				.storeLonY(entity.getStoreLonY())
				.storeImg(entity.getStoreImg())
				.storeCate(entity.getStoreCate().getCateName())
				.menus(dtoList)
				.build();
		return dto;
	}

	@Override
	public void updateStore(StoreDTO storeDto) {
		StoreEntity store = storeRepo.findById(storeDto.getStoreRegNum()).orElse(null);
		store.setStoreLatX(storeDto.getStoreLatX());
		store.setStoreLonY(storeDto.getStoreLonY());
		
		storeRepo.save(store);
	}

	@Override
	public List<RecommendDTO> consumeRecommend() {
		List<StoreEntity> entities = storeRepo.findStoresWithinDistance(longitude, latitude, distance);
		List<RecommendDTO> storeList = new ArrayList<>();
		
		
		for (int i = 0; i < 40; i++) {
			int randomIdx = (int) (Math.random() * (entities.size() -1));;
			RecommendDTO recommendStore = entityToDto(entities.get(randomIdx));
			recommendStore.setCate(entities.get(randomIdx).getStoreCate().getCateName());
			storeList.add(recommendStore);
		}
		return storeList;
	}

	@Override
	public List<RecommendDTO> peerRecommend() {
		List<StoreEntity> entities = storeRepo.findStoresWithinDistance(longitude, latitude, distance);
		List<RecommendDTO> storeList = new ArrayList<>();
		
		
		for (int i = 0; i < 40; i++) {
			int randomIdx = (int) (Math.random() * (entities.size() -1));;
			RecommendDTO recommendStore = entityToDto(entities.get(randomIdx));
			recommendStore.setCate(entities.get(randomIdx).getStoreCate().getCateName());
			storeList.add(recommendStore);
		}
		return storeList;
	}

	@Override
	public List<RecommendDTO> dayPatternRecommend() {
		List<StoreEntity> entities = storeRepo.findStoresWithinDistance(longitude, latitude, distance);
		List<RecommendDTO> storeList = new ArrayList<>();
		
		for (int i = 0; i < 40; i++) {
			int randomIdx = (int) (Math.random() * (entities.size() -1));;
			RecommendDTO recommendStore = entityToDto(entities.get(randomIdx));
			recommendStore.setCate(entities.get(randomIdx).getStoreCate().getCateName());
			storeList.add(recommendStore);
		}
		return storeList;
	}

	@Override
	public List<RecommendDTO> allPatternRecommend() {
		List<StoreEntity> entities = storeRepo.findStoresWithinDistance(longitude, latitude, distance);
		List<RecommendDTO> storeList = new ArrayList<>();
		
		for (int i = 0; i < 40; i++) {
			int randomIdx = (int) (Math.random() * (entities.size() -1));;
			RecommendDTO recommendStore = entityToDto(entities.get(randomIdx));
			recommendStore.setCate(entities.get(randomIdx).getStoreCate().getCateName());
			storeList.add(recommendStore);
		}
		return storeList;
	}

	@Override
	public List<HomePayHistroyDTO> getPayHistory(String memId) {
		Pageable pageable = PageRequest.of(0,20);
		List<PayHistoryEntity> entityList = payHistoryRepo.findByMemberId(memId, pageable);
		List<HomePayHistroyDTO> dtoList = new ArrayList<>();
		for(PayHistoryEntity ph : entityList) {
			HomePayHistroyDTO dto = HomePayHistroyDTO.builder()
					.storeName(ph.getStores().getStoreName())
					.payAmount(ph.getPayAmount().intValue())
					.payDate(ph.getPayDate())
					.build();
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<WeeklyConsumeProjection> getWeeklyConsumeStore(String memId) {
		List<WeeklyConsumeProjection> dtoList = storeRepo.findStoreWeeklyConsume(memId);
		return dtoList;
	}

	@Override
	public List<WeeklyConsumeProjection> getStorePayHistory(String memId) {
		System.out.println("++++++++++++++++++++++++++" + memId);
		List<WeeklyConsumeProjection> dtoList = storeRepo.findStorePayHistory(memId);
		return dtoList;
	}

}
