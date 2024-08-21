package com.shinhan.dailyconsume.domain;


import jakarta.persistence.Column;
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
@Table(name = "t_menu")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MenuEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;
	private String menuName;
	@Column(length = 500)
	private String menuImg;
	private Long menuPrice;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storeRegNum")
	StoreEntity storeInfo;

}
