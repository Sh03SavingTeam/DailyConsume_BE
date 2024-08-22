package com.shinhan.dailyconsume.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.calendar.CalendarDTO;
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
        
        List<CalendarDTO> payHistoryDetailList = calendarService.getPayHistoryByDayMonthAndYear(memberId, day, month, year);
        System.out.println("[" + memberId + "] 조회된 결제 내역 (연도: " + year + ", 월: " + month + ", 일: " + day + "): " + payHistoryDetailList);
        
        return ResponseEntity.ok(payHistoryDetailList);
    }
    
 
    // 일별 결제 내역 상세 조회 메서드    
    @GetMapping("/payhistory/detail")
    public ResponseEntity<CalendarDTO> getPayHistoryDetail(
            @RequestParam("memberId") String memberId,
            @RequestParam("payId") Long payId) {
    	System.out.println(memberId + payId);
        CalendarDTO payHistoryDetail = calendarService.getPayHistoryDetail(memberId, payId);
        if (payHistoryDetail == null) {
            return ResponseEntity.notFound().build();
        } 
        System.out.println(payHistoryDetail);
        return ResponseEntity.ok(payHistoryDetail);
        
    }
    
    // 결제 내역 등록
    @PostMapping("/register")
    public ResponseEntity<Long> registerPayHistory(@RequestBody CalendarDTO dto) {
        Long payId = calendarService.register(dto);
        return new ResponseEntity<>(payId, HttpStatus.CREATED);
    }
	
    // 이상&정상 결제 처리 수정 엔드포인트
    @PostMapping("/payhistory/update")
    public ResponseEntity<Void> updateMyPayCheck(
            @RequestParam("memberId") String memberId,
            @RequestParam("payId") Long payId,
            @RequestParam("myPayCheck") Integer myPayCheck) {
        boolean updated = calendarService.updateMyPayCheck(memberId, payId, myPayCheck);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    

//    @PostMapping("/payhistory/update")
//    public ResponseEntity<String> updateMyPayCheck(
//            @RequestBody UpdatePayCheckRequest request) {
//        
//        boolean isUpdated = calendarService.updateMyPayCheck(request.getMemberId(), request.getPayId(), request.getMyPayCheck());
//        
//        if (isUpdated) {
//            return ResponseEntity.ok("Update successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed");
//        }
//    }
}
