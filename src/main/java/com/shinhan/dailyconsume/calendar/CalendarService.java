package com.shinhan.dailyconsume.calendar;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.ConsumeCategoryEntity;
import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;

import jakarta.transaction.Transactional;

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
        Map<String, Object> result = calendarRepo.findPayHistoryDetailWithMenu(payId, memberId);

        if (result != null) {
            CalendarDTO dto = new CalendarDTO();
            for(String columName : result.keySet()) {
            	 if(columName.equals("pay_id")) dto.setPayId((Long) result.get(columName));
            	 if(columName.equals("pay_amount"))dto.setPayAmount((Long) result.get(columName));
            	 if(columName.equals("pay_date"))dto.setPayDate((Timestamp) result.get(columName));
            	 if(columName.equals("consume_name"))dto.setConsumeCategory((String) result.get(columName));
            	 if(columName.equals("card_num"))dto.setCardNum((String) result.get(columName));
            	 if(columName.equals("card_name"))dto.setCardName((String) result.get(columName));
            	 if(columName.equals("store_reg_num"))dto.setStoreRegNum((String) result.get(columName));
            	 if(columName.equals("store_name"))dto.setStoreName((String) result.get(columName));
            	 if(columName.equals("my_pay_check"))dto.setMyPayCheck((Integer) result.get(columName));
            	 if(columName.equals("menu_name"))dto.setMenuName((String) result.get(columName));
            }
            return dto;
        }
        return null;
    }
    
    // 이상&정상 결제 처리 수정
	/**
     * 특정 결제 내역의 myPayCheck 값을 업데이트합니다.
     *
     * @param memberId   회원 ID
     * @param payId      결제 ID
     * @param myPayCheck 업데이트할 myPayCheck 값 (1: 정상 결제, 0: 이상 결제)
     * @return 업데이트 성공 여부 (true: 성공, false: 실패)
     */
	@Transactional
	public boolean updateMyPayCheck(String memberId, Long payId, Integer myPayCheck) {
	    try {
	        PayHistoryEntity payHistory = calendarRepo.findByMemberCardMemberMemberIdAndPayId(memberId, payId);
	        if (payHistory != null) {
	            payHistory.setMyPayCheck(myPayCheck);
	            calendarRepo.save(payHistory);  // save 메서드 호출 시 사용되는 객체는 calendarRepo입니다.
	            System.out.println("Updated myPayCheck to " + myPayCheck + " for payId: " + payId);
	            return true;
	        } else {
	            System.out.println("PayHistoryEntity not found for memberId: " + memberId + " and payId: " + payId);
	        }
	        return false;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


    
    // entity를 dto로 변환
    public CalendarDTO entityToDTO(PayHistoryEntity entity) {
        return CalendarDTO.builder()
                .payId(entity.getPayId())
                .payAmount(entity.getPayAmount())
                .payDate(entity.getPayDate())
                .consumeCategory(entity.getConsumeCategory().getConsumeName())
                .cardNum(entity.getMemberCard().getCardNum())
                .storeRegNum(entity.getStores().getStoreRegNum())
                .storeName(entity.getStores().getStoreName())
                .myPayCheck(entity.getMyPayCheck())                
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
