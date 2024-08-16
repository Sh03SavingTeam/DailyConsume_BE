package com.shinhan.dailyconsume.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeCategoryDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long consumeId;
	private String consumeName;
}
