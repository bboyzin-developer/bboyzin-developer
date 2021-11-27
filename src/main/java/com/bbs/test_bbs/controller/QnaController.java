package com.bbs.test_bbs.controller;

import com.bbs.test_bbs.biz.BbsService;
import com.bbs.test_bbs.biz.BbsVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class QnaController {

    @Autowired
    private BbsService bbsService;

    @RequestMapping("/getBbsQna.do")
    public String getBbsQna(BbsVO bbsVO, Model model) {

        //검색어 유지를 위한 모델 저장
        model.addAttribute("search", bbsVO);

        //VO에 값이 있을 경우
        if (bbsVO.getStartDate() != null) {

            //리스트 추출
            List<BbsVO> bbsQna = bbsService.getBbsQna(bbsVO);
            double sum_ect = 0.0;

            //하위 5개 항목 병합
            for (int i = 5; i < 10; i++) {
                sum_ect = sum_ect + bbsQna.get(i).getCnt_total();
            }

            //Array 객체 생성
            JsonArray jsonArr = new JsonArray();

            //상위 첫번째 행 선택
            for (int i = 0; i < 1; i++) {
                JsonObject json1 = new JsonObject();
                json1.addProperty("name", bbsQna.get(i).getC_category());
                json1.addProperty("y", bbsQna.get(i).getCnt_total());
                json1.addProperty("sliced", true);
                json1.addProperty("selected", true);
                jsonArr.add(json1);
                System.out.println(json1);
            }

            //상위 5개 Array 삽입
            for (int i = 1; i < 5; i++) {
                JsonObject json2 = new JsonObject();
                json2.addProperty("name", bbsQna.get(i).getC_category());
                json2.addProperty("y", bbsQna.get(i).getCnt_total());
                jsonArr.add(json2);
                System.out.println(json2);
            }

            //나머지 기타 항목 삽입
            JsonObject json3 = new JsonObject();
            json3.addProperty("name", "기타");
            json3.addProperty("y", sum_ect);
            jsonArr.add(json3); // 최종 array에 기타항목 삽입
            System.out.println(json3);

            model.addAttribute("jsonArr", jsonArr);
            model.addAttribute("bbsQna", bbsQna);
        }
        return "qna";
    }
}