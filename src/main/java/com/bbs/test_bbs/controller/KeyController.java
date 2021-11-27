package com.bbs.test_bbs.controller;

import com.bbs.test_bbs.biz.BbsService;
import com.bbs.test_bbs.biz.BbsVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class KeyController {

    @Autowired
    private BbsService bbsService;

    @RequestMapping("/getBbsKey.do")
    public String getBbsKey(BbsVO bbsVO, Model model) {

        //검색어 유지를 위한 모델 저장
        model.addAttribute("search", bbsVO);

        //리스트에 담기
        List<BbsVO> bbsKey = bbsService.getBbsKey(bbsVO);

        //모델에 담기
        model.addAttribute("bbsKey", bbsKey);

        return "keyword";
    }


    //Ajax Controller 구현
    @ResponseBody
    @RequestMapping(value = "/getJson.do")
    public String getJson(BbsVO bbsVO, Model model) {
    //기존값 유지(입력값 저장)
        model.addAttribute("search", bbsVO);

    //그래프 데이터 담기
        List<BbsVO> keyThisYear = bbsService.getKeyThisYear(bbsVO);
        List<BbsVO> keylastYear = bbsService.getKeyLastYear(bbsVO);
        BbsVO thisYearAvg = bbsService.getThisYearAvg(bbsVO);
        BbsVO lastYearAvg = bbsService.getLastYearAvg(bbsVO);

    //Array 객체 생성
        JsonArray jsonArr = new JsonArray();
        // 금년
        JsonObject json1 = new JsonObject();
            json1.addProperty("name","2021");
            JsonArray thisYear = new JsonArray();
            for (int i = 0; i < keyThisYear.size(); i++) {
                thisYear.add(keyThisYear.get(i).getCnt_total());
            }
            json1.addProperty("thisyear",thisYear.toString());
        jsonArr.add(json1);

        // 작년
        JsonObject json2 = new JsonObject();
            json2.addProperty("name","2020");
            JsonArray lastYear = new JsonArray();
            for (int i = 0; i < keylastYear.size(); i++) {
                lastYear.add(keylastYear.get(i).getCnt_total());
            }
            json2.addProperty("lastyear",lastYear.toString());
        jsonArr.add(json2);

        // 평균
        JsonObject json3 = new JsonObject();
            json3.addProperty("avgThis",thisYearAvg.getAvg_this());
            json3.addProperty("avgLast", lastYearAvg.getAvg_last());
        jsonArr.add(json3);

        Gson gson = new Gson();
        String jsonArrList = gson.toJson(jsonArr);

        return jsonArrList;
    }

}