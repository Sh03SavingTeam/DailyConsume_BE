package com.shinhan.dailyconsume.dto.map;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RecommendDTO {
	private String storeRegNum;
	private String storeName;
	private String storeAddr;
	private String storePhone;
	private Double storeLatX;
	private Double storeLonY;
	private String storeImg;
	private String cate;
}
