<!--2021년만 조회할수 있도록 처리-->
function DateCheck2() {
    var frmStartDt = document.frm2.StartDate.value;
    var ckSrtDt = document.frm2.StartDate.value.split("-");
    var ckEndDt = document.frm2.EndDate.value.split("-");

    if (ckSrtDt.length != 3 || ckEndDt.length != 3) {
        alert("날짜 형식이 잘못되었습니다.");
        return false;
    }
    var startDt = new Date(Number(ckSrtDt[0]), Number(ckSrtDt[1]) - 1, Number(ckSrtDt[2]));
    var endDt = new Date(Number(ckEndDt[0]), Number(ckEndDt[1]) - 1, Number(ckEndDt[2]));
    resultDt = Math.floor(endDt.valueOf() / (24 * 60 * 60 * 1000) - startDt.valueOf() / (24 * 60 * 60 * 1000));

    if (resultDt < 0) {
        alert("조회 시작일이 종료일 보다 큽니다.");
        return false;
    }
    if (frmStartDt<'2020-01-01'){
        alert("2019년 이전은 조회할수 없습니다.");
        return false;
    }
    return true;
}

// 전체값 조회
$(function() {
    if($('#keyword0').attr('value')!=null) {
        getKeywordGraph('',0);
    }
});

<!--검색리스트 ajax 처리-->
function getKeywordGraph(keyword,index) {
    //입력값 변수화
    var StartDate = $("#start_date").val();
    var EndDate = $("#end_date").val();
    var thisAvg = $('#avg_this'+index).val();
    var lastAvg = $('#avg_last'+index).val();
    var result = [];
    var curDate = new Date(StartDate);
    while(curDate <=new Date(EndDate)){
        result.push((curDate.getMonth()+1)+'/'+curDate.getDate());
        curDate.setDate(curDate.getDate() + 1);
    }
    // 전송 json
    var list = {
        "keyword": keyword,
        "StartDate": StartDate,
        "EndDate": EndDate
    };
    // ajax 통신
    $.ajax({
        type: "get",
        url: "/getJson.do",
        contentType: "application/json",
        data: list,
        dataType : 'json',
    })
        //통신 성공시 리스트 추출
        .success(function (jsonArrList) {
            var data1 = JSON.parse(jsonArrList[0].thisyear);
            var data2 = JSON.parse(jsonArrList[1].lastyear);
            if(keyword==''){
                thisAvg = JSON.parse(jsonArrList[2].avgThis);
                lastAvg = JSON.parse(jsonArrList[2].avgLast);
                var mainTitle = '전체';
            }else {
                thisAvg = thisAvg;
                lastAvg = lastAvg;
                var mainTitle = list.keyword;
            }
            Highcharts.chart('areachat', {
                chart: {
                    type: 'area'
                },
                title: {
                    text: '"'+mainTitle+'" 일자별 키워드 현황'
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    verticalAlign: 'top',
                    x: 100,
                    y: 70,
                    floating: true,
                    borderWidth: 1,
                    backgroundColor:
                        Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF'
                },
                xAxis: {
                    categories: result
                },
                yAxis: [{
                    title: {
                        text: '키워드 수'
                    },
                    plotLines: [{//기준점 사용 옵션.
                        color: '#fa219c',
                        width: 2,
                        value: thisAvg,
                        dashStyle: 'longdash',//라인 스타일 지정 옵션
                        zIndex: 5,
                        label: {
                            text:'금년 평균 :' + thisAvg,
                            align: 'right',
                            useHTML: true,
                            x: -10,
                            y: -10,
                            style: {
                                color:'#fa219c',
                                backgroundColor: 'white'
                            }
                        }
                    },
                        {
                            color: '#33C0FF',
                            width: 2,
                            value: lastAvg,
                            dashStyle: 'shortdash',
                            zIndex: 5,

                            label: {
                                text:'전년 평균 :' + lastAvg,
                                useHTML: true,
                                align: 'right',
                                x: -10,
                                y: -10,
                                style: {
                                    color:'#33C0FF',
                                    backgroundColor : 'white',
                                },
                            }
                        }]
                }],
                plotOptions: {
                    area: {
                        fillOpacity: 0.4
                    }
                },
                credits: {
                    enabled: false
                },
                series: [{
                    name: '조회 연도',
                    data: data1
                }, {
                    name: '조회 전연도',
                    data: data2
                }]
            })
        });
}
$(function(){
    var sBtn = $(".keywordList");
    sBtn.find("td").click(function(){
        sBtn.removeClass("info");
        $(this).parent().addClass("info");

    })
});