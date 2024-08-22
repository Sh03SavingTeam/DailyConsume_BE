package com.shinhan.dailyconsume.calendar;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.ConsumeCategoryEntity;
import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;

@Service
public class CalendarService {

	@Autowired
    private CalendarRepository calendarRepo;
	
	/**
     * 특정 회원의 특정 월의 결제 내역을 조회합니다.
     * 
     * @param memberId 회원 ID
     * @param month 월 (1월은 1, 12월은 12)
     * @param year 연도 (예: 2024)
     * @return 해당 월의 결제 내역 리스트
     */
	// 특정 월의 결제 내역 조회
	public List<CalendarDTO> getPayHistoryByMonthAndMemberId(String memberId, int month, int year) {
        List<PayHistoryEntity> payHistories = calendarRepo.findByMemberAndMonthAndYear(memberId, month, year);
        return payHistories.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
	
	// 특정 연도, 월, 일의 결제 내역 조회
	public List<CalendarDTO> getPayHistoryByDayMonthAndYear(String memberId, int day, int month, int year) {
	    List<PayHistoryEntity> payHistories = calendarRepo.findByMemberAndDayAndMonthAndYear(memberId, day, month, year);
	    return payHistories.stream()
	            .map(this::entityToDTO)
	            .collect(Collectors.toList());
	}
	
    // 결제 내역 등록	
	public Long register(CalendarDTO dto) {
        PayHistoryEntity newPayHistoryEntity = calendarRepo.save(dtoToEntity(dto));
        return newPayHistoryEntity.getPayId();
    }

	// 일별 결제 내역 상세 조회 메서드
    public CalendarDTO getPayHistoryDetail(String memberId, Long payId) {
        PayHistoryEntity payHistory = calendarRepo.findByMemberIdAndPayId(memberId, payId);
        return payHistory != null ? entityToDTO(payHistory) : null;
    }
	
    // 이상&정상 결제 처리 수정
    public boolean updateMyPayCheck(String memberId, Long payId, Integer myPayCheck) {
        try {
            PayHistoryEntity payHistory = calendarRepo.findByMemberIdAndPayId(memberId, payId);
            if (payHistory != null) {
                payHistory.setMyPayCheck(myPayCheck);
                calendarRepo.save(payHistory);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	// entity를 dto로 변환
    private CalendarDTO entityToDTO(PayHistoryEntity entity) {
        return CalendarDTO.builder()
                .payId(entity.getPayId())
                .payAmount(entity.getPayAmount())
                .payDate(entity.getPayDate())
                .consumeCategory(entity.getConsumeCategory().getConsumeName())
                .cardNum(entity.getMemberCard() != null ? entity.getMemberCard().getCardNum() : null)
                .storeRegNum(entity.getStores() != null ? entity.getStores().getStoreRegNum() : null)
                .storeName(entity.getStores() != null ? entity.getStores().getStoreName() : null)
                .myPayCheck(entity.getMyPayCheck()) // 본인 결제 여부 추가
                .build();
    }

    // dto를 entity로 변환
    private PayHistoryEntity dtoToEntity(CalendarDTO dto) {
        MemberCardEntity card = new MemberCardEntity();
        card.setCardNum(dto.getCardNum());

        StoreEntity store = new StoreEntity();
        store.setStoreRegNum(dto.getStoreRegNum());
        store.setStoreName(dto.getStoreName());

        return PayHistoryEntity.builder()
                .payAmount(dto.getPayAmount())
                .payDate(dto.getPayDate() != null ? dto.getPayDate() : new Timestamp(System.currentTimeMillis()))
                .consumeCategory(new ConsumeCategoryEntity()) // 실제로는 DTO에서 값이 있어야 합니다.
                .memberCard(card)
                .stores(store)
                .myPayCheck(dto.getMyPayCheck()) // 본인 결제 여부 추가
                .build();
    }
	
}
