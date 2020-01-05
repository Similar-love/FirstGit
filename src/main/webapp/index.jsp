<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="#"/>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
</head>

<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 1800px;height:800px;"></div>
    <script type="text/javascript">
window.onload=function(){
	$.get("myservler",
    		function(data){
    		    var xArr=[];
    		    var yArr=[];
    		    for(var i  in data){
    		    	xArr.push(i);
    		    	yArr.push(data[i]);
    		    }        		    
    		    // 基于准备好的dom，初始化echarts实例
    	        var myChart = echarts.init(document.getElementById('main'));
    	        // 指定图表的配置项和数据
    	        var option = {
    	            title: {
    	                text: '显示景点出现的次数'
    	            },
    	            tooltip: {},
    	            legend: {
    	                data:['景点']
    	            },
    	            xAxis: {
    	                data: xArr
    	            },
    	            yAxis: {},
    	            series: [{
    	                name: '景点',
    	                type: 'bar',
    	                data:  yArr
    	            }]
    	        };

    	        // 使用刚指定的配置项和数据显示图表。
    	        myChart.setOption(option);
    		    
    		}
    	,"json")
    }

</script>
 
</body>

</html>