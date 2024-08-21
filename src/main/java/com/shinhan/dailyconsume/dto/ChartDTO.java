package com.shinhan.dailyconsume.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChartDTO {
	private int age;
	private int currentMonth;
	List<PayHistoryDTO> payHistories;

}
