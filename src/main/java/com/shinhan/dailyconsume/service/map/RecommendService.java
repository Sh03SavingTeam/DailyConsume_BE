package com.shinhan.dailyconsume.service.map;

import java.util.List;

import com.shinhan.dailyconsume.domain.MenuEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.map.MenuDTO;
import com.shinhan.dailyconsume.dto.map.RecommendDTO;
import com.shinhan.dailyconsume.dto.map.StoreDetailDTO;
import com.shinhan.dailyconsume.dto.store.StoreDTO;

public interface RecommendService {
	
	List<RecommendDTO> recommendStores();
	
	StoreDetailDTO getStoreDetail(String storeRegNum);
	
	void updateStore(StoreDTO storeDto);

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
	
	default MenuDTO menuEntityToDto(MenuEntity entity) {
		return MenuDTO.builder()
				.menuId(entity.getMenuId())
				.menuName(entity.getMenuName())
				.menuImg(entity.getMenuImg())
				.menuPrice(entity.getMenuPrice())
				.build();
	}
}
