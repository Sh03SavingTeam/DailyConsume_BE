package com.shinhan.dailyconsume.dto.map;

import java.util.List;

import com.shinhan.dailyconsume.domain.MenuEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDetailDTO {
	private String storeRegNum;
	private String storeName;
	private String storeAddr;
	private String storePhone;
	private Double storeLatX;
	private Double storeLonY;
	private String storeImg;
	private String storeCate;
	private List<MenuDTO> menus;
}
