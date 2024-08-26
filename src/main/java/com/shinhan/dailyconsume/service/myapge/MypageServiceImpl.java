package com.shinhan.dailyconsume.service.myapge;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.WeeklyConsumeEntity;
import com.shinhan.dailyconsume.dto.mypage.MemberInfoDTO;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.WeeklyConsumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MypageServiceImpl implements MypageService{

    private final MemberRepository mRepo;
    private final WeeklyConsumeRepository wRepo;

    @Override
    public MemberInfoDTO getMemberInfo(String memberId) {

        //회원 정보 조회
        MemberEntity member = mRepo.findByMemberId(memberId);

        //회원 주간 미션 정보 조회
        if(wRepo.findByMemberOrderByEndDateDesc(member).size()!=0){
            List<WeeklyConsumeEntity> weeklyConsumes = wRepo.findByMemberOrderByEndDateDesc(member);

            // "2024-08-25."
            String endDate = weeklyConsumes.get(0).getEndDate()
                    .toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            LocalDate today = LocalDate.now();

            // 이번 주의 시작일(월요일)
            LocalDate weekStart = today.minusDays(today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());

            // 이번 주의 종료일(일요일)
            LocalDate weekEnd = weekStart.plusDays(6);

            String nowEndDate = weekEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            MemberInfoDTO memberInfoDTO;
            if(endDate.equals(nowEndDate)){
                memberInfoDTO = new MemberInfoDTO(member.getMemberImg(), member.getMemberName(), weeklyConsumes.get(0).getWeeklyMoney(), endDate);
            }
            else{
                memberInfoDTO = new MemberInfoDTO(member.getMemberImg(), member.getMemberName(), 0, "");
            }
            return memberInfoDTO;
        }
        else{
            MemberInfoDTO memberInfoDTO = new MemberInfoDTO(member.getMemberImg(), member.getMemberName(), 0, "");
            return memberInfoDTO;
        }
    }
}
