package com.shinhan.dailyconsume.service.point;

import com.shinhan.dailyconsume.dto.point.PointAccountDTO;
import com.shinhan.dailyconsume.dto.point.PointDTO;
import org.springframework.data.domain.Pageable;

public interface PointHistoryService {

    // 멤버 포인트 내역 가져오기
    public PointDTO getPointByMember(Pageable pageable, String memberId);

    // 멤버 포인트 양, 계좌 정보 가져오기
    public PointAccountDTO getPointAccount(String memberId);

    // 포인트 환급화
    public String pointToCash(String memberId, int point);
}
