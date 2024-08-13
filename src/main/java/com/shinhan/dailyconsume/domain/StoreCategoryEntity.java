package com.shinhan.dailyconsume.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_store_category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StoreCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeCateSeq;
	
	private int cateDepth;
	private String cateName;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name ="upperCateSeq")
    private StoreCategoryEntity upperCate;

    @OneToOne (mappedBy = "upperCate", cascade = CascadeType.ALL)
    private StoreCategoryEntity subCate;
    
    @OneToOne(mappedBy = "storeCate")
    private StoreEntity store;
}
