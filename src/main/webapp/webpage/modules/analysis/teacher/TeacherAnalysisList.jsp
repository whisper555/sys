<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>教师分析</title>
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


	<!-- 教师带领人数部分 -->
	<div class="row-fluid">   
         <div class="col-lg-6" >
            <div id="StuNumBar" class="widget style1" style="height: 300px">
            </div>
        </div>
	</div>
	<!-- 教师学生的平均分部分 -->
	<div class="row-fluid">   
         <div class="col-lg-6" >
            <div id="StuScoreBar" class="widget style1" style="height: 300px">
            </div>
        </div>
	</div>
	<!-- 教师学生的优秀部分 -->
	<div class="row-fluid">   
         <div class="col-lg-6" >
            <div id="StuRateBar" class="widget style1" style="height: 300px">
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

	  		//某院系教师带领人数柱形图
	  		createStuNumBar(); 
	  		//去年教师学生平均分
	  		createStuScoreBar(); 
	  		//去年教师学生优秀率
	  		createStuRateBar(); 

	  	});

	 
		/*
        *统计某院系教师带领人数统计
        */
       function createStuNumBar(){
            var domStuNumBar = document.getElementById("StuNumBar");
            var myChartNum = echarts.init(domStuNumBar);
            var app = {};
            var option = null;
            
	        
 			$.ajax({
                type : "POST",
                url : "${ctx}/analysis/teacher/StuNumBar",
                data:{},
                dataType : "json",
                success : function(nresult) {
                	//console.log(nresult);
                	option = {
                		    title: {
                		        text: '教师名下学生人数统计',
                		        left:100
                		    },
                		    tooltip : {
                		        trigger: 'axis',
                		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                		        }
                		    },
                		    legend: {
                		        data:['人数']
                		    },
                		    xAxis: {
                		    name:'姓名',
                		    axisTick: {
                                alignWithLabel: true
                            },
                            axisLabel: {
                                interval:0,
                                rotate:-45
                            },
                		    data:nresult.xAxis
                		   
                		    },
                		   yAxis:[ {  
			                        type : 'value',  
			                        name:'人数',
			                        axisLabel : {  
			                            formatter : '{value} 个'  
			                        },  
			                        splitArea : {  
			                            show : true  
			                        }  ,

			                         minInterval: 1
			                        
			                    } ],
			                    
			                   	
	                		    series: [{
	                		        name: '人数',
	                		        type: 'bar',
	                		        itemStyle: {
	                	                normal: {color: 'rgba(1,1,1,0.5)'}
	                	            },
	                		        data:nresult.yAxis
	                		    }]
                		};
		            if (option && typeof option === "object") {
		                myChartNum.setOption(option, true);
		            }
            	}//success
            });            
        }
       /*
        *统计某院系教师学生毕设平均分数统计
        */
       function createStuScoreBar(){
            var domStuScoreBar = document.getElementById("StuScoreBar");
            var myChartScore = echarts.init(domStuScoreBar);
            var app = {};
            var option = null;
            
	        
 			$.ajax({
                type : "POST",
                url : "${ctx}/analysis/teacher/StuScoreBar",
                data:{},
                dataType : "json",
                success : function(sresult) {
                	//console.log(sresult);
                	option = {
                		    title: {
                		        text: '教师学生毕设分数平均分',
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
                		    name:'姓名',
                		    axisTick: {
                                alignWithLabel: true
                            },
                            axisLabel: {
                                interval:0,
                                rotate:-45
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

			                         minInterval: 1
			                        
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
       /*
        *统计某院系教师带领优秀率统计
        */
       function createStuRateBar(){
            var domStuRateBar = document.getElementById("StuRateBar");
            var myChartRate = echarts.init(domStuRateBar);
            var app = {};
            var option = null;
            
	        
 			$.ajax({
                type : "POST",
                url : "${ctx}/analysis/teacher/StuRateBar",
                data:{},
                dataType : "json",
                success : function(rresult) {
                	//console.log(rresult);
                	option = {
                		    title: {
                		        text: '教师名下学生优秀率统计',
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
                		    name:'姓名',
                		    axisTick: {
                                alignWithLabel: true
                            },
                            axisLabel: {
                                interval:0,
                                rotate:-45
                            },
                		    data:rresult.xAxis
                		    // data:['100','100','100','100','100','100']
                		   
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

			                         minInterval: 1
			                        
			                    } ],
			                    
			                   	
	                		    series: [{
	                		        name: '百分比',
	                		        type: 'bar',
	                		        itemStyle: {
	                	                normal: {color: 'rgba(2,200,200,0.5)'}
	                	            },
	                		        data:rresult.yAxis
	                		        //data:[1,1,1,1,1,1]
	                		    }]
                		};
		            if (option && typeof option === "object") {
		                myChartRate.setOption(option, true);
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