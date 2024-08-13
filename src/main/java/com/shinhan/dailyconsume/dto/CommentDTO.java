package com.shinhan.dailyconsume.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "comment")
public class CommentDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	private String commentContent;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//private MemberDTO memberId;
}
