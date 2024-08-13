package com.shinhan.dailyconsume.Domain;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	Long cardId;
	String cardName;
	String cardImgUrl;
	String cardPageUrl;
	
}
