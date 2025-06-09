package com.shinhan.dailyconsume.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.calendar.CalendarDTO;
import com.shinhan.dailyconsume.calendar.CalendarPutDTO;
import com.shinhan.dailyconsume.calendar.CalendarService;

@CrossOrigin
@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
	@Autowired
	CalendarService calendarService;
		
	/**
     * 특정 회원의 특정 월의 결제 내역을 조회합니다.
     * 
     * @param memberId 회원 ID
     * @param month 월 (1월은 1, 12월은 12)
     * @param year 연도 (예: 2024)
     * @return 해당 월의 결제 내역 리스트
     */
	// 월별 결제 내역 조회 메서드
    @GetMapping("/payhistory")
    public ResponseEntity<List<CalendarDTO>> getMonthlyPayHistory(
            @RequestParam("memberId") String memberId,
            @RequestParam("month") int month,
            @RequestParam(value = "year", defaultValue = "2024") int year) {

    	System.out.println(memberId + month + year);
        List<CalendarDTO> payHistoryList = calendarService.getPayHistoryByMonthAndMemberId(memberId, month, year);
        
        if (payHistoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(payHistoryList);
    }

    /**
     * 특정 회원의 특정 연도, 월, 일의 결제 내역을 조회합니다.
     *
     * @param memberId 회원 ID
     * @param day 일 (1일부터 31일까지)
     * @param month 월 (1월은 1, 12월은 12)
     * @param year 연도 (예: 2024)
     * @return 해당 일의 결제 내역 리스트
     */
    // 일별 결제 내역 조회 메서드 
    @GetMapping("/payhistory/daily")
    public ResponseEntity<List<CalendarDTO>> getDailyPayHistory(
            @RequestParam("memberId") String memberId,
            @RequestParam("day") int day,
            @RequestParam("month") int month,
            @RequestParam("year") int year) {
        
        List<CalendarDTO> payHistoryDailyList = calendarService.getPayHistoryByDayMonthAndYear(memberId, day, month, year);
        System.out.println("[" + memberId + "] 조회된 결제 내역 (연도: " + year + ", 월: " + month + ", 일: " + day + "): " + payHistoryDailyList);
        
        return ResponseEntity.ok(payHistoryDailyList);
    }
    
 
    // 일별 결제 내역 상세 조회 메서드    
    @GetMapping("/payhistory/detail")
    public ResponseEntity<CalendarDTO> getPayHistoryDetail(
            @RequestParam("memberId") String memberId,
            @RequestParam("payId") Long payId) {
        
        CalendarDTO dto = calendarService.getPayHistoryDetail(memberId, payId);
        
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    // 결제 내역 등록
    @PostMapping("/register")
    public ResponseEntity<Long> registerPayHistory(@RequestBody CalendarDTO dto) {
        Long payId = calendarService.register(dto);
        return new ResponseEntity<>(payId, HttpStatus.CREATED);
    }
	
    // 이상&정상 결제 처리 수정 엔드포인트
    // PUT 요청으로 이상&정상 결제 처리 수정 엔드포인트
    @PutMapping("/payhistory/update")
    public ResponseEntity<CalendarDTO> updateMyPayCheck(
            @RequestBody CalendarPutDTO updateMyCheck) {
    	System.out.println(updateMyCheck);
        CalendarDTO updatedDto = calendarService.updateMyPayCheck(updateMyCheck.getMemberId(), updateMyCheck.getPayId(), updateMyCheck.getMyPayCheck());
        
        if (updatedDto != null) {
            return ResponseEntity.ok(updatedDto);
        } else {
            // 더 세분화된 에러 처리를 고려할 수 있습니다.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }
    
    // 주간 설정 금액 잔액 조회
    /**
     * 특정 회원의 주간 소비 금액 요약 정보를 가져옵니다.
     * 
     * @param memberId 회원 ID
     * @param year     연도
     * @param month    월
     * @param day      일
     * @return 주간 소비 요약 정보
     */
    @GetMapping("/payweekly")
    public ResponseEntity<Map<String, Object>> getWeeklyConsumeSummary(
            @RequestParam("memberId") String memberId,
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("day") int day) {

        Map<String, Object> summary = calendarService.getWeeklyConsumeSummary(memberId, year, month, day);
        
        if (summary != null) {
            return ResponseEntity.ok(summary);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }        
    }
    
    // 특정 월의 주간 설정 금액 조회
    @GetMapping("/weeklyConsume/month")
    public ResponseEntity<List<Map<String, Object>>> getWeeklyConsumeByMonth(
            @RequestParam("memberId") String memberId,
            @RequestParam("month") int month) {

        List<Map<String, Object>> weeklyConsumeList = calendarService.getWeeklyConsumeByMonth(memberId, month);

        if (weeklyConsumeList == null || weeklyConsumeList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(weeklyConsumeList);
    }
}
