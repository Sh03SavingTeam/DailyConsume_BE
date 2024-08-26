package com.shinhan.dailyconsume.service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.dto.ChartDTO;
import com.shinhan.dailyconsume.dto.PayHistoryDTO;
import com.shinhan.dailyconsume.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayHistoryService {

	private final MemberRepository memberRepo;

	//개인 카테고리별 지출내역 구하는 메소드
	@Transactional
	public List<PayHistoryDTO> getPayHistory(String memberId) {

		List<PayHistoryDTO> payHistories = new ArrayList<>();

		payHistories.add(new PayHistoryDTO(0, "식비", 0));
		payHistories.add(new PayHistoryDTO(0, "교통비", 0));
		payHistories.add(new PayHistoryDTO(0, "온라인쇼핑", 0));
		payHistories.add(new PayHistoryDTO(0, "여가", 0));

		List<PayHistoryEntity> datas = memberRepo.findPayHistoriesByMemberId(memberId);
		
		int totalAmount = 0;

		for (PayHistoryEntity payHistory : datas) {
			int consumeId = payHistory.getConsumeCategory().getConsumeId().intValue();
			int payAmount = payHistory.getPayAmount().intValue();
			totalAmount += payAmount;
			switch (consumeId) {
			case 1:
				payHistories.get(0).addPayAmount(payAmount);
				break;
			case 2:
				payHistories.get(1).addPayAmount(payAmount);
				break;
			case 3:
				payHistories.get(2).addPayAmount(payAmount);
				break;
			case 4:
				payHistories.get(3).addPayAmount(payAmount);
				break;
			}
		}
		
		for(int i=0;i<4;i++) {
			float payAmount = payHistories.get(i).getPayAmount();
	        //totalAmount가 0이 아닌지 확인
	        if (totalAmount > 0) {
	            float percentage = (payAmount / (float) totalAmount) * 100;
	            payHistories.get(i).setPercentage((float)((Math.round(percentage * 10.0) / 10.0)));
	        } else {
	            //totalAmount가 0일 경우 비율을 0으로 설정
	            payHistories.get(i).setPercentage(0);
	        }
		}
		return payHistories;
	}

	//또래 카테고리별 지출내역 평균 구하는 메소드
	@Transactional
	public ChartDTO getPeerPayHistory(String memberId) {

		Date memberBirth = memberRepo.findByMemberId(memberId).getMemberBirth();
		
		int age = ((java.time.Year.now().getValue() - memberBirth.toLocalDate().getYear()) / 10) * 10;
		
		List<MemberEntity> peers = memberRepo.findPeerByAge(age);
		List<PayHistoryEntity> datas = new ArrayList<>();
		
		int totalMember = peers.size();

		for (MemberEntity member : peers) {
			datas.addAll(memberRepo.findPayHistoriesByMemberId(member.getMemberId()));
		}
		
		List<PayHistoryDTO> payHistories = new ArrayList<>();

		payHistories.add(new PayHistoryDTO(0, "식비"));
		payHistories.add(new PayHistoryDTO(0, "교통비"));
		payHistories.add(new PayHistoryDTO(0, "온라인쇼핑"));
		payHistories.add(new PayHistoryDTO(0, "여가"));
		
		for (PayHistoryEntity payHistory : datas) {
			int consumeId = payHistory.getConsumeCategory().getConsumeId().intValue();
			int payAmount = payHistory.getPayAmount().intValue();
			switch (consumeId) {
			case 1:
				payHistories.get(0).addPayAmount(payAmount);
				break;
			case 2:
				payHistories.get(1).addPayAmount(payAmount);
				break;
			case 3:
				payHistories.get(2).addPayAmount(payAmount);
				break;
			case 4:
				payHistories.get(3).addPayAmount(payAmount);
				break;
			}
		}
		
		for(int i=0;i<4;i++) {
			payHistories.get(i).avgAmount(totalMember);
		}
		
		ChartDTO chartDTO = new ChartDTO(age, LocalDate.now().getMonthValue(), payHistories);
		
		return chartDTO;
	}

}