package com.shinhan.dailyconsume.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_review")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReviewEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	private double rating;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storeRegNum")
	private StoreEntity store;
}
