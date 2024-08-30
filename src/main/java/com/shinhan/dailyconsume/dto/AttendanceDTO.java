package com.shinhan.dailyconsume.dto;

import com.shinhan.dailyconsume.dto.mypage.RankHistoryInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceDTO {
	private String memberId;
	private int attendanceInfo;
	private int totalAttendance;
}
