package com.shinhan.dailyconsume.dto;


import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeeklyConsumeDTO {
	
	private Long weeklySeq;
	private Timestamp startDate;
	private Timestamp endDate;
	private String weeklyCheck;
	private Long memberId;
}
