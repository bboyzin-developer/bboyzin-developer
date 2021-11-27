<!--기간은 30일만 조회할 수 있도록 처리-->
function DateCheck() {
    var ckSrtDt = document.frm.StartDate.value.split("-");
    var ckEndDt = document.frm.EndDate.value.split("-");

    if (ckSrtDt.length != 3 || ckEndDt.length != 3) {
        alert("날짜 형식이 잘못되었습니다.");
        return false;
    }

    var startDt = new Date(Number(ckSrtDt[0]), Number(ckSrtDt[1]) - 1, Number(ckSrtDt[2])); //시작날짜 객체 생성
    var endDt = new Date(Number(ckEndDt[0]), Number(ckEndDt[1]) - 1, Number(ckEndDt[2])); //종료날짜 객체 생성
    resultDt = Math.floor(endDt.valueOf() / (24 * 60 * 60 * 1000) - startDt.valueOf() / (24 * 60 * 60 * 1000)); //날짜의 차 계산

    //조건에 따른 경고 팝업처리
    if (resultDt < 0) {
        alert("조회 시작일이 종료일 보다 큽니다.");
        return false;
    }

    if (resultDt > 30) {
        alert("조회기간은 30일을 초과할 수 없습니다.");
        return false;
    }
    return true;
}