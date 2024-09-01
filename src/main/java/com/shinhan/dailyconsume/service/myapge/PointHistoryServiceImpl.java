package com.shinhan.dailyconsume.service.myapge;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.PointHistoryEntity;
import com.shinhan.dailyconsume.dto.point.PointAccountDTO;
import com.shinhan.dailyconsume.dto.point.PointDTO;
import com.shinhan.dailyconsume.dto.point.PointHistoryDTO;
import com.shinhan.dailyconsume.dto.point.PointRegisterDTO;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PointHistoryServiceImpl implements PointHistoryService{

    private final PointHistoryRepository phRepo;
    private final MemberRepository mRepo;

    public PointHistoryDTO entityToDTO(PointHistoryEntity entity){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        PointHistoryDTO dto = new PointHistoryDTO(entity.getDivNum(), entity.getAmount(), sdf.format(entity.getPointRegDate()), entity.getCmt());
        return dto;
    }

    // 멤버 포인트 내역 가져오기
    @Override
    public PointDTO getPointByMember(Pageable pageable, String memberId) {

        //회원 정보 조회
        MemberEntity member = mRepo.findByMemberId(memberId);

        //총 포인트 조회
        Long plusPoint = phRepo.getPlusPointByMember(member);
        Long minusPoint = phRepo.getMinusPointByMember(member);
        Long totalPoint = plusPoint-minusPoint;

        //포인트 이력 조회 (DTO 변환 + 페이징)
        List<PointHistoryDTO> pointHistories = phRepo.findByMemberOrderByPointRegDateDesc(member).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());

        //총 내역 개수
        int historyCount = pointHistories.size();

        //페이징
        List<PointHistoryDTO> pagingHistories=new ArrayList<>();

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), historyCount);

        List<PointHistoryDTO> paginatedHistories = new ArrayList<>(pointHistories).subList(startIndex, endIndex);

        for(PointHistoryDTO pointHistoryDTO : paginatedHistories){
            pagingHistories.add(pointHistoryDTO);
        }

        PointDTO pointDTO = new PointDTO(totalPoint, historyCount, pagingHistories);

        return pointDTO;
    }

    // 멤버 포인트 양, 계좌 정보 가져오기
    @Override
    public PointAccountDTO getPointAccount(String memberId) {
        //회원 정보 조회
        MemberEntity member = mRepo.findByMemberId(memberId);

        //총 포인트 조회
        Long plusPoint = phRepo.getPlusPointByMember(member);
        Long minusPoint = phRepo.getMinusPointByMember(member);
        Long totalPoint = plusPoint-minusPoint;

        PointAccountDTO pointAccountDTO = new PointAccountDTO(totalPoint.intValue(), member.getMemberAccount());
        return pointAccountDTO;
    }

    // 포인트 환급화
    @Override
    public String pointToCash(String memberId, int point) {
        //회원 정보 조회
        MemberEntity member = mRepo.findByMemberId(memberId);

        //회원 포인트 양 업데이트
        Long newPoint = Long.valueOf(member.getPointAmount().intValue()-point);
        member.update(newPoint);

        //포인트 내역 데이터 삽입
        PointHistoryEntity pointHistory = PointHistoryEntity.builder()
                .member(member)
                .divNum(1L)
                .amount(Long.valueOf(point))
                .cmt("계좌로 출금")
                .build();

        phRepo.save(pointHistory);

        return memberId;
    }

    // 포인트 지급
    @Override
    public PointHistoryEntity register(PointRegisterDTO pointRegisterDTO) {

        //System.out.println(pointRegisterDTO);

        //System.out.println("================================" + pointRegisterDTO.getMemberId());

        MemberEntity member = mRepo.findById(pointRegisterDTO.getMemberId()).orElse(null);

        //System.out.println(member);

        PointHistoryEntity pointHistory = PointHistoryEntity.builder()
                .divNum(0L)
                .amount(Long.valueOf(pointRegisterDTO.getPoint()))
                .cmt(pointRegisterDTO.getComment())
                .member(member)
                .build();

        PointHistoryEntity result = phRepo.save(pointHistory);

        //System.out.println("==========================세이브 후 SOUT");

        return result;
    }
}
