<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>Insert title here</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.col-xs-12 span {
			color:white;
		}
		
		.fa-star {
			float: left;
		}

	</style>
	</head>
<body>


	<!-- 班级平均分部分 -->
	<div class="row-fluid">   
         <div class="col-lg-6" >
            <div id="ClaScoreBar" class="widget style1" style="height: 300px">
            </div>
        </div>
	</div>
	
	<!-- 教师学生的通过率部分 -->
	<div class="row-fluid">   
         <div class="col-lg-6" >
            <div id="ClaRateBar" class="widget style1" style="height: 300px">
            </div>
        </div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${ctxStatic}/echarts/echarts.min.js"></script>
	<script type="text/javascript">
		//获取当前年月，显示当前年月的数据
		var d = new Date();
		var mm = d.getMonth()+1;
		mm = mm<10?"0"+mm:mm;
		var yyyymm = d.getFullYear() + "/" + mm;//"2016/12";

		var ecConfig = echarts.config; 

	  	$(document).ready(function() {

	  		//班级平均分
	  		createClaRateBar(); 
	  		//班级毕设通过率
	  		createClaScoreBar(); 
	  		

	  	});

	 
		/*
        *统计某院系教师带领人数统计
        */
       function createClaRateBar(){
            var domClaRateBar = document.getElementById("ClaRateBar");
            var myChartRate = echarts.init(domClaRateBar);
            var app = {};
            var option = null;
            
	        
 			$.ajax({
                type : "POST",
                url : "${ctx}/analysis/classs/ClaRateBar",
                data:{},
                dataType : "json",
                success : function(nresult) {
                	//console.log(nresult);
                	option = {
                		    title: {
                		        text: '班级毕设通过率',
                		        left:100
                		    },
                		    tooltip : {
                		        trigger: 'axis',
                		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                		        }
                		    },
                		    legend: {
                		        data:['百分比']
                		    },
                		    xAxis: {
                		    name:'班级',
                		    axisTick: {
                                alignWithLabel: true
                            },
                            axisLabel: {
                                interval:0,
                               // rotate:-45
                            },
                		    data:nresult.xAxis
                		   
                		    },
                		   yAxis:[ {  
			                        type : 'value',  
			                        name:'百分比',
			                        axisLabel : {  
			                            formatter : '{value} %'  
			                        },  
			                        splitArea : {  
			                            show : true  
			                        }  ,

			                       /*  minInterval: 1*/
			                        
			                    } ],
			                    
			                   	
	                		    series: [{
	                		        name: '百分比',
	                		        type: 'bar',
	                		        itemStyle: {
	                	                normal: {color: 'rgba(1,1,1,0.5)'}
	                	            },
	                		        data:nresult.yAxis
	                		    }]
                		};
		            if (option && typeof option === "object") {
		                myChartRate.setOption(option, true);
		            }
            	}//success
            });            
        }
       /*
        *统计某院系教师学生毕设平均分数统计
        */
       function createClaScoreBar(){
            var domClaScoreBar = document.getElementById("ClaScoreBar");
            var myChartScore = echarts.init(domClaScoreBar);
            var app = {};
            var option = null;
            
	        
 			$.ajax({
                type : "POST",
                url : "${ctx}/analysis/classs/ClaScoreBar",
                data:{},
                dataType : "json",
                success : function(sresult) {
                	//console.log(sresult);
                	option = {
                		    title: {
                		        text: '班级毕设分数平均分',
                		        left:100
                		    },
                		    tooltip : {
                		        trigger: 'axis',
                		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                		        }
                		    },
                		    legend: {
                		        data:['分数']
                		    },
                		    xAxis: {
                		    name:'班级',
                		    axisTick: {
                                alignWithLabel: true
                            },
                            axisLabel: {
                                interval:0,
                                //rotate:-45
                            },
                            data:sresult.xAxis
                		    //data:['100','100','100','100','100','100']
                		    },
                		   yAxis:[ {  
			                        type : 'value',  
			                        name:'分数',
			                        axisLabel : {  
			                            formatter : '{value} 分'  
			                        },  
			                        splitArea : {  
			                            show : true  
			                        }  ,

			                       /*  minInterval: 1*/
			                        
			                    } ],
			                    
			                   	
	                		    series: [{
	                		        name: '分数',
	                		        type: 'bar',
	                		        itemStyle: {
	                	                normal: {color: 'rgba(100,2,100,0.5)'}
	                	            },
	                		        data:sresult.yAxis
	                		      // data:[1,1,1,1,1,1]
	                		    }]
                		};
		            if (option && typeof option === "object") {
		                myChartScore.setOption(option, true);
		            }
            	}//success
            });            
        }

	  	function showLayer(url,param){
	  		//iframe层
			layer.open({
			  type: 2,
			  title: '',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['70%', '70%'],
			  content: url //iframe的url
			}); 
	  	}

	</script>
</body>
</html>