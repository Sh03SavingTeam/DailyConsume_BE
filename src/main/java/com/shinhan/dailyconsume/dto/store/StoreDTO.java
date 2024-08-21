package com.shinhan.dailyconsume.dto.store;

import com.shinhan.dailyconsume.domain.StoreCategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
	private String storeRegNum;
	private String storeName;
	private StoreCategoryEntity storeCate;
	private String storeAddr;
	private String storePhone;
	private Double storeLatX;
	private Double storeLonY;
	private String storeImg;
}
