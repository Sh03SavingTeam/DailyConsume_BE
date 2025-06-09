package com.shinhan.dailyconsume.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name ="upperCateSeq")
    private StoreCategoryEntity upperCate;

	@OneToMany (mappedBy = "upperCate", cascade = CascadeType.ALL)
    private List<StoreCategoryEntity> subCate;
    
    @OneToMany(mappedBy = "storeCate")
    private List<StoreEntity> store;
}
