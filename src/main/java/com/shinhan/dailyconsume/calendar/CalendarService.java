package com.shinhan.dailyconsume.calendar;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.ConsumeCategoryEntity;
import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.domain.WeeklyConsumeEntity;

import jakarta.transaction.Transactional;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepository calendarRepo;

	/**
	 * 특정 회원의 특정 월의 결제 내역을 조회합니다.
	 * 
	 * @param memberId 회원 ID
	 * @param month    월 (1월은 1, 12월은 12)
	 * @param year     연도 (예: 2024)
	 * @return 해당 월의 결제 내역 리스트
	 */
	// 특정 월의 결제 내역 조회
	public List<CalendarDTO> getPayHistoryByMonthAndMemberId(String memberId, int month, int year) {
		List<PayHistoryEntity> payHistories = calendarRepo.findByMemberAndMonthAndYear(memberId, month, year);
		List<CalendarDTO> a = payHistories.stream().map(this::entityToDTO).collect(Collectors.toList());;
		System.out.println("!!!!"+a+"!!!!");
		return a;
	}

	// 특정 연도, 월, 일의 결제 내역 조회
	public List<CalendarDTO> getPayHistoryByDayMonthAndYear(String memberId, int day, int month, int year) {
		List<PayHistoryEntity> payHistories = calendarRepo.findByMemberAndDayAndMonthAndYear(memberId, day, month,
				year);
		return payHistories.stream().map(this::entityToDTO).collect(Collectors.toList());
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
			for (String columName : result.keySet()) {
				if (columName.equals("pay_id"))
					dto.setPayId((Long) result.get(columName));
				if (columName.equals("pay_amount"))
					dto.setPayAmount((Long) result.get(columName));
				if (columName.equals("pay_date"))
					dto.setPayDate((Timestamp) result.get(columName));
				if (columName.equals("consume_name"))
					dto.setConsumeCategory((String) result.get(columName));
				if (columName.equals("card_num"))
					dto.setCardNum((String) result.get(columName));
				if (columName.equals("card_name"))
					dto.setCardName((String) result.get(columName));
				if (columName.equals("store_reg_num"))
					dto.setStoreRegNum((String) result.get(columName));
				if (columName.equals("store_name"))
					dto.setStoreName((String) result.get(columName));
				if (columName.equals("my_pay_check"))
					dto.setMyPayCheck((Integer) result.get(columName));
				if (columName.equals("menu_name"))
					dto.setMenuName((String) result.get(columName));
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
	public CalendarDTO updateMyPayCheck(String memberId, Long payId, Integer myPayCheck) {

		try {
			PayHistoryEntity payHistory = calendarRepo.findByMemberCardIdAndPayId(memberId, payId);
			if (payHistory != null) {
				payHistory.setMyPayCheck(myPayCheck);
				PayHistoryEntity updatedEntity = calendarRepo.save(payHistory);
				return entityToDTO(updatedEntity);
			} else {
				// 예외를 던지거나 로그에 기록
				throw new IllegalArgumentException("Payment history not found for the given memberId and payId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 필요에 따라 예외를 재던지거나 특정 예외를 처리
			return null;
		}
	}
	
	/**
     * 특정 회원의 주간 소비 금액 요약 정보를 가져옵니다.
     *
     * @param memberId 회원 ID
     * @param year     연도
     * @param month    월
     * @param day      일
     * @return 주간 소비 요약 정보
     */
    public Map<String, Object> getWeeklyConsumeSummary(String memberId, int year, int month, int day) {
    	System.out.println("getWeeklyConsumeSummary called with: " + memberId + ", " + year + "-" + month + "-" + day);

        try {
            // 입력된 날짜를 Timestamp로 변환
            LocalDate selectedLocalDate = LocalDate.of(year, month, day);
            Timestamp selectedDate = Timestamp.valueOf(selectedLocalDate.atStartOfDay());

            // 주간 설정 정보 조회
            Map<String, Object> summary = calendarRepo.findWeeklyConsumeSummary(memberId, selectedDate);

            if (summary == null || summary.isEmpty()) {
                return null;
            }

            System.out.println("Summary data: " + summary);
            return summary;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 특정 회원의 특정 월의 주간 설정 금액 데이터를 조회합니다.
     * 
     * @param memberId 회원 ID
     * @param month    월 (1월은 1, 12월은 12)
     * @return 해당 월의 주간 설정 금액 리스트
     */
    public List<Map<String, Object>> getWeeklyConsumeByMonth(String memberId, int month) {
        List<Map<String, Object>> weeklyConsumeList = calendarRepo.findWeeklyConsumeByMonth(memberId, month);
        if (weeklyConsumeList.isEmpty()) {
            return null;
        }
        return weeklyConsumeList;
    }
    
	// entity를 dto로 변환
	public CalendarDTO entityToDTO(PayHistoryEntity entity) {
		return CalendarDTO.builder().payId(entity.getPayId()).payAmount(entity.getPayAmount())
				.payDate(entity.getPayDate()).consumeCategory(entity.getConsumeCategory().getConsumeName())
				.cardNum(entity.getMemberCard().getCardNum()).storeRegNum(entity.getStores().getStoreRegNum())
				.storeName(entity.getStores().getStoreName()).myPayCheck(entity.getMyPayCheck()).build();
	}

	// dto를 entity로 변환
	private PayHistoryEntity dtoToEntity(CalendarDTO dto) {
		MemberCardEntity card = new MemberCardEntity();
		card.setCardNum(dto.getCardNum());

		StoreEntity store = new StoreEntity();
		store.setStoreRegNum(dto.getStoreRegNum());
		store.setStoreName(dto.getStoreName());

		return PayHistoryEntity.builder().payAmount(dto.getPayAmount())
				.payDate(dto.getPayDate() != null ? dto.getPayDate() : new Timestamp(System.currentTimeMillis()))
				.consumeCategory(new ConsumeCategoryEntity() ) // 실제로는 DTO에서 값이 있어야 합니다.
				.memberCard(card).stores(store).myPayCheck(dto.getMyPayCheck()) // 본인 결제 여부 추가
				.build();
	}
}