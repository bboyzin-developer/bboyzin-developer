package com.bbs.test_bbs.controller;

import com.bbs.test_bbs.biz.BbsService;
import com.bbs.test_bbs.biz.BbsVO;
import com.bbs.test_bbs.biz.PageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.util.List;

@Controller
public class BbsController {

    @Autowired
    private BbsService bbsService;

    //로컬 호스트 호출
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    //리스트 조회 컨트롤러
    @RequestMapping(value = "/getBbsList.do")
    public String getBbsList(BbsVO bbsvo, Model model) {
        //0. 페이징을 위한 VO가져오기
        PageMaker pageMaker = new PageMaker();
        pageMaker.setBbsVO(bbsvo);

        //1. 리스트 상세
        List<BbsVO> bbsList = bbsService.getBbsList(bbsvo);
        model.addAttribute("bbsList", bbsList);

        //0-1. 페이징 처리
        pageMaker.setTotalCount(bbsList.get(0).getCnt_total());
        bbsvo.setCnt_total(bbsList.get(0).getCnt_total());
        bbsvo.setEndpage((int)Math.ceil((double) bbsList.get(0).getCnt_total()/10)); //총 게시물수 / 한페이지당 게시물 수
        model.addAttribute("pageMaker", pageMaker);
        System.out.println(pageMaker);

        //2. 일자별 질문건수 카운팅
        List<BbsVO> bbsCount = bbsService.getBbsCount(bbsvo);
        model.addAttribute("bbsCount", bbsCount);

        //2-1. 검색 상태를 유지하기 위해서  Model에 BbsVO 정보를 등록한다.
        model.addAttribute("search", bbsvo);

        return "main";
    }
}