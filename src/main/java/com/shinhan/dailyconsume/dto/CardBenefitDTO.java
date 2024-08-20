package com.shinhan.dailyconsume.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardBenefitDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long benefitId;
	String cardName;
	String benefit;

}
