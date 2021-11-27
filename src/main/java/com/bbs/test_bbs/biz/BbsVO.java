package com.bbs.test_bbs.biz;

import lombok.Data;

import java.util.Date;

@Data
public class BbsVO {

    private Date date;
    private Date c_date;
    private int number;
    private String category;
    private String q_class;
    private String lName;
    private String l_title;
    private int clickNumber;
    private String url;
    private String mainContent;

    //검색을 위한 변수 지정
    private String StartDate;
    private String EndDate;
    private int cnt; // 일자별 게시글 총카운트
    private String searchKeyword;
    private String searchCondition;
    private int cnt_total;

    //페이징을 위한 변수 지정
    private int page; // 현재 페이지
    private int perPageNum; // 한 페이지당 보여줄 게시글의 갯수
    private int pageStart;
    private int endpage;

    public int getPageStart() {  //특정 페이지의 게시글 시작번호, 게시글 시작 행 번호
        return (this.page-1)*perPageNum;
    }

    public BbsVO() { //기본 생성자
        this.page = 1;
        this.perPageNum = 10;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) { //페이지가 음수값이 되지 않게 설정, 음수가 되면 1페이지를 나타낸다.
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    public int getPerPageNum() {
        return perPageNum;
    }
    public void setPerPageNum(int pageCount) { //페이지당 보여줄 게시글 수가 변하지 않게 설정
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }

    // 차트
    private double round_category; // 키워드 비중(상위 키워드 10개 합)
    private int cs; // 상위 10개 키워드
    private String c_category;

    // 키워드 차트
    private int cnt_last_key;
    private double round_last;
    private int avg_this;
    private int avg_last;
    private String keyword;
}