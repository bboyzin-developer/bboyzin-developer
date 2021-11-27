package com.bbs.test_bbs.biz;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BbsDAO {

    @Autowired
    private SqlSession sqlSession;

    public List<BbsVO> getBbsList(BbsVO vo) {
        List<BbsVO> bbsList = sqlSession.selectList("BbsDAO.getBbsList", vo);
        return bbsList;
    }

    public List<BbsVO> getBbsCount(BbsVO vo) {
        List<BbsVO> bbsCount = sqlSession.selectList("BbsDAO.getBbsCount", vo);
        return bbsCount;
    }

    public List<BbsVO> getBbsQna(BbsVO vo) {
        List<BbsVO> bbsQna = sqlSession.selectList("BbsDAO.getBbsQna", vo);
        return bbsQna;
    }

    public List<BbsVO> getBbsKey(BbsVO vo) {
        List<BbsVO> bbsKey = sqlSession.selectList("BbsDAO.getBbsKey", vo);
        return bbsKey;
    }

    public List<BbsVO> getKeyThisYear(BbsVO vo) {
        List<BbsVO> keyThisYear = sqlSession.selectList("BbsDAO.getKeyThisYear", vo);
        return keyThisYear;
    }

    public List<BbsVO> getKeyLastYear(BbsVO vo) {
        List<BbsVO> keyLastYear = sqlSession.selectList("BbsDAO.getKeyLastYear", vo);
        return keyLastYear;
    }

    public BbsVO getThisYearAvg(BbsVO vo) {
        BbsVO getThisYearAvg = sqlSession.selectOne("BbsDAO.getThisYearAvg", vo);
        return getThisYearAvg;
    }

    public BbsVO getLastYearAvg(BbsVO vo) {
        BbsVO getLastYearAvg = sqlSession.selectOne("BbsDAO.getLastYearAvg", vo);
        return getLastYearAvg;
    }

}