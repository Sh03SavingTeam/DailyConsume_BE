package com.shinhan.dailyconsume.service.myapge;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.WeeklyConsumeEntity;
import com.shinhan.dailyconsume.dto.mypage.MemberInfoDTO;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.WeeklyConsumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

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
        if(wRepo.findByMember(member)!=null){
            WeeklyConsumeEntity weeklyConsume = wRepo.findByMember(member);

            // "2024-08-25."
            String endDate = weeklyConsume.getEndDate()
                    .toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            MemberInfoDTO memberInfoDTO = new MemberInfoDTO(member.getMemberImg(), member.getMemberName(), weeklyConsume.getWeeklyMoney(), endDate);

            return memberInfoDTO;
        }
        else{
            MemberInfoDTO memberInfoDTO = new MemberInfoDTO(member.getMemberImg(), member.getMemberName(), 0, "");

            return memberInfoDTO;
        }
    }
}
