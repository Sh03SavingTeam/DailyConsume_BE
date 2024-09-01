package com.shinhan.dailyconsume.dto.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaidStoreDTO {
	private String storeRegNum;
    private String storeName;
    private String storeAddr;
    private Double storeLatx;
    private Double storeLony;
    private String storeImg;
    private String storePhone;
    private String cateName;
}
