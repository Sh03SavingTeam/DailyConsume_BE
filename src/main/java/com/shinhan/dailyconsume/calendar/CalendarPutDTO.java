package com.shinhan.dailyconsume.calendar;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarPutDTO {
	private String memberId;
	private Long payId;
	private int myPayCheck;
}
