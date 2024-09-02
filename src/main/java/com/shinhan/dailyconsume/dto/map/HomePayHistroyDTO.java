package com.shinhan.dailyconsume.dto.map;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomePayHistroyDTO {
	private String storeName;
	private Timestamp payDate;
	private int payAmount;
}
