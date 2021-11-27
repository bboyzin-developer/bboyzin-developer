package com.bbs.test_bbs.biz;

import lombok.Data;

@Data
public class Criteria {

    private int page; // 현재 페이지
    private int perPageNum; // 한 페이지당 보여줄 게시글의 갯수
    private  int pageStart;

    public int getPageStart() {  //특정 페이지의 게시글 시작번호, 게시글 시작 행 번호
        return (this.page-1)*perPageNum;
    }

    public Criteria() { //기본 생성자
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
}