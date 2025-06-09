package com.shinhan.dailyconsume.dto.store;

import com.shinhan.dailyconsume.domain.StoreEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonMenuDTO {
	private Long menuId;
	private String menuName;
	private String menuImg;
	private Long menuPrice;
	StoreEntity storeInfo;
}
