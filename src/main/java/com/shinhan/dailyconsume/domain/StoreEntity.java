package com.shinhan.dailyconsume.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_store")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StoreEntity {
	
	@Id
	private String storeRegNum;
	
	private String storeName;
	private String storeAddr;
	private String storephone;
	
	@OneToMany(mappedBy = "store"
			, fetch = FetchType.LAZY)
	private List<ReviewEntity> review;

	@OneToOne
	@JoinColumn(name = "storeCateSeq")
	StoreCategoryEntity storeCate;
}
