package com.shinhan.dailyconsume.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "menuEntity")
	private List<MenuImgsEntity> memuImgs = new ArrayList<>();
	
	private String memnuName;
	
	private Long menuPrice;
	
	//private String storeId;
	
	
	
	
	
	
}
