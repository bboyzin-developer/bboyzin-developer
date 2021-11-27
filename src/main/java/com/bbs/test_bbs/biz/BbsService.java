package com.bbs.test_bbs.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsService {

    @Autowired
    private BbsDAO bbsDAO;

    //검색 기간내 질문 리스트 구현
    public List<BbsVO> getBbsList(BbsVO vo) {
        return bbsDAO.getBbsList(vo);
    }

    //질문건수 카운팅 구현
    public List<BbsVO> getBbsCount(BbsVO vo) {
        return bbsDAO.getBbsCount(vo);
    }
    //질문 건수 하이챠트 구현
    public List<BbsVO> getBbsQna(BbsVO vo) {
        return bbsDAO.getBbsQna(vo);
    }
    //키워드 하이챠트 구현
    public List<BbsVO> getBbsKey(BbsVO vo) {
        return bbsDAO.getBbsKey(vo);
    }
    //금년 하이챠트 구현
    public List<BbsVO> getKeyThisYear(BbsVO vo) {
        return bbsDAO.getKeyThisYear(vo);
    }
    // 전년 하이챠트 구현
    public List<BbsVO> getKeyLastYear(BbsVO vo) {
        return bbsDAO.getKeyLastYear(vo);
    }

    // 금년 평균 값 구현
    public BbsVO getThisYearAvg(BbsVO vo) {
        return bbsDAO.getThisYearAvg(vo);
    }
    // 전년 평균 값 구현
    public BbsVO getLastYearAvg(BbsVO vo) {
        return bbsDAO.getLastYearAvg(vo);
    }
}
