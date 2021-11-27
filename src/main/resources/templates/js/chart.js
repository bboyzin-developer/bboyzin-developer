    $(document).ready(function() {
        var data = JSON.parse($("#pieValue").val());
        piechart(data);
    });

        var piechart = function(data){
            Highcharts.chart('container', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: '질문 상위 키워드 Top 10',
                    style: {
                        fontFamily:'hanna',"fontSize":"19px"
                    }
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
                },
                accessibility: {
                    point: {
                        valueSuffix: '%'
                    }
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.2f} %'
                        }
                    }
                },
                series: [{
                    innerSize: '50%',
                    name: 'Keyword',
                    colorByPoint: true,
                    data: data,
                    dataLabels: {
                        enabled: true,
                        style: {
                            fontFamily:'hanna',"fontSize":"13px"
                        }
                    }
                }]
            })
        };
