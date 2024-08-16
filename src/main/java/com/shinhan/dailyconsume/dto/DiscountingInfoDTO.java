package com.shinhan.dailyconsume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DiscountingInfoDTO {
	
	private String storeName;

	private String productName;

	private int amount;

	private String prductContent;

	private String productCategory;

	private String prodImg;
}