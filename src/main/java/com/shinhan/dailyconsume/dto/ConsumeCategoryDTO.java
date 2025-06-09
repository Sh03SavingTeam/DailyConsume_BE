package com.shinhan.dailyconsume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeCategoryDTO {
	
	private Long consumeId;
	private String consumeName;
}
