package com.shinhan.dailyconsume.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankHistoryInfoDTO {
	private Long score;
	private String coment;
	private String memberId;
}
