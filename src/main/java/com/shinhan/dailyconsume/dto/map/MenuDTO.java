package com.shinhan.dailyconsume.dto.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {
	private Long menuId;
	private String menuName;
	private String menuImg;
	private Long menuPrice;
	private String storeRegNum;
}
