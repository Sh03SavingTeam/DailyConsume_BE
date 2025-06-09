package com.shinhan.dailyconsume.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_discounting_info")
public class DiscountingInfoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long discountId;
	
	private String storeName;
	
	private String productName;
	
	private String amount;
	
	private String productContent;
	
	private String prodImg;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumeId")
	private ConsumeCategoryEntity consumeCategory;
}
