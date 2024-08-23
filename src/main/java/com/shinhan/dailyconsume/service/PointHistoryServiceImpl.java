package com.shinhan.dailyconsume.service;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.PointHistoryEntity;
import com.shinhan.dailyconsume.dto.PointAccountDTO;
import com.shinhan.dailyconsume.dto.PointDTO;
import com.shinhan.dailyconsume.dto.PointHistoryDTO;
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

    @Override
    public PointDTO getPointByMember(Pageable pageable, String memberId) {

        //회원 정보 조회
        MemberEntity member = mRepo.findByMemberId(memberId);

        //총 포인트 조회
        Long totalPoint = phRepo.getTotalPointByMember(member);

        //포인트 이력 조회 (DTO 변환 + 페이징)
        List<PointHistoryDTO> pointHistories = phRepo.findPointHistoryByMember(member).stream()
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

    @Override
    public PointAccountDTO getPointAccount(String memberId) {
        //회원 정보 조회
        MemberEntity member = mRepo.findByMemberId(memberId);
        PointAccountDTO pointAccountDTO = new PointAccountDTO(member.getPointAmount().intValue(), member.getMemberAccount());
        return pointAccountDTO;
    }
}
