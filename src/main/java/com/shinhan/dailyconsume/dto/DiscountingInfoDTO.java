package com.shinhan.dailyconsume.dto;

import com.shinhan.dailyconsume.domain.DiscountingInfoEntity;
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

	private String amount;

	private String productContent;

	private String productCategory;

	private String prodImg;

	public DiscountingInfoDTO(DiscountingInfoEntity entity) {
		this.storeName = entity.getStoreName();
		this.productName = entity.getProductName();
		this.amount = entity.getAmount();
		this.productContent = entity.getProductContent();
		this.productCategory = entity.getProductCategory();
		this.prodImg = entity.getProdImg();
	}
}