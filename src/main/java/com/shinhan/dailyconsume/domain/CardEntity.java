package com.shinhan.dailyconsume.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "t_card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CardEntity {
	@Id
	String cardName;
	String cardImgUrl;
	String cardPageUrl;
	String cardType;
	
	@OneToMany(mappedBy = "card", fetch=FetchType.LAZY)
	private List<MemberCardEntity> memberCards;
}
