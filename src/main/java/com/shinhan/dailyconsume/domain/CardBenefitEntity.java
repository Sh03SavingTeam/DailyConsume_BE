package com.shinhan.dailyconsume.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="t_card_benefit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CardBenefitEntity {

	@Id
	Long benefitId;
	String cardName;
	String benefit;
	
}
