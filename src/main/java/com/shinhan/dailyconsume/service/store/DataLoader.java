package com.shinhan.dailyconsume.service.store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinhan.dailyconsume.domain.MenuEntity;
import com.shinhan.dailyconsume.domain.StoreCategoryEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.dto.store.JsonMenuDTO;
import com.shinhan.dailyconsume.dto.store.StoreDTO;
import com.shinhan.dailyconsume.repository.MenuRepository;
import com.shinhan.dailyconsume.repository.StoreCategoryRepository;
import com.shinhan.dailyconsume.repository.StoreRepository;

//@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	StoreRepository storeRepo;
	
	@Autowired
	StoreCategoryRepository storeCateRepo;
	
	@Autowired
	MenuRepository menuRepo;

	@Override
	public void run(String... args) throws Exception {
		String filePath = "C:/shinhan3/python/stores.json";
		try {
			File file = new File(filePath);
			List<Map<String, Object>> datas = objectMapper.readValue(file,
					new TypeReference<List<Map<String, Object>>>() {
					});

			List<StoreDTO> storeList = new ArrayList<>();
			List<JsonMenuDTO> menuList = new ArrayList<>();

			Set<String> storeCate = new HashSet<>();
			
			for (Map<String, Object> data : datas) {
				storeCate.add((String)data.get("storeCategory"));
			}
			
			//saveCate(storeCate);
			
			Map<String, Long> mappedCate = mappingCateSeq(storeCateRepo.findAll());
			
			int storeIdx = 1;
			
			for (Map<String, Object> data : datas) {
				
				StoreCategoryEntity cateEntity = StoreCategoryEntity.builder()
						.storeCateSeq(mappedCate.get((String) data.get("storeCategory")))
						.build();
				
				StoreDTO storeDto = StoreDTO.builder().storeRegNum("174-90-" + String.format("%05d", storeIdx))
						.storeName((String) data.get("storeName"))
						.storeCate(cateEntity)
						.storeAddr((String) data.get("storePlace"))
						.storePhone((String) data.get("storePhone"))
						.storeImg((String) data.get("storeImg"))
						.build();

				StoreEntity store = StoreEntity.builder()
						.storeRegNum("174-90-" + String.format("%05d", storeIdx))
						.build();

				storeCate.add((String) data.get("storeCategory"));
				
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> menus = (List<Map<String, Object>>) data.get("menuList");
				for (Map<String, Object> menu : menus) {
					if (String.valueOf(menu.get("menuPrice")).equals("정보 없음"))
						menu.put("menuPrice", 0);
					
					JsonMenuDTO menuDto = JsonMenuDTO.builder().menuName((String) menu.get("menuName"))
							.menuPrice(Long.valueOf(String.valueOf(menu.get("menuPrice")).replace(",", "")))
							.menuImg((String) menu.get("menuImg"))
							.storeInfo(store)
							.build();
					menuList.add(menuDto);
				}
				storeList.add(storeDto);
				storeIdx++;
			}
			
			
			//saveStore(storeList);
			//saveMenu(menuList);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void saveMenu(List<JsonMenuDTO> menuList) {
		for (JsonMenuDTO dto : menuList) {
			MenuEntity entity = MenuEntity.builder()
					.menuName(dto.getMenuName())
					.menuPrice(dto.getMenuPrice())
					.menuImg(dto.getMenuImg())
					.storeInfo(dto.getStoreInfo())
					.build();
			menuRepo.save(entity);
		}
	}
	
	private void saveCate(Set<String> storeCate) {
		for(String cateName: storeCate) {
			StoreCategoryEntity upperEntity = StoreCategoryEntity.builder().storeCateSeq(1L) .build();
			StoreCategoryEntity entity = StoreCategoryEntity.builder()
					.cateDepth(2)
					.cateName(cateName)
					.upperCate(upperEntity)
					.build();
			storeCateRepo.save(entity);
		}
	}
	
	private void saveStore(List<StoreDTO> storeList) {
		for (StoreDTO dto : storeList) {
			StoreEntity entity = StoreEntity.builder()
					.storeRegNum(dto.getStoreRegNum())
					.storeName(dto.getStoreName())
					.storeCate(dto.getStoreCate())
					.storeAddr(dto.getStoreAddr())
					.storePhone(dto.getStorePhone())
					.storeImg(dto.getStoreImg())
					.build();
			storeRepo.save(entity);
		}
	}
	
	private Map<String, Long> mappingCateSeq(List<StoreCategoryEntity> cateList) {
		Map<String, Long> result = new HashMap<>();
		for(StoreCategoryEntity category: cateList) {
			result.put(category.getCateName(), category.getStoreCateSeq());
		}
		return result;
	}
}
