<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>学生分析</title>
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
<table>
<tr>
<td>
	<select id="depart" onchange="refresh()" class="form-control required">
		<c:forEach items="${departList }" var ="depart" >
			<option value="${depart.id }">${depart.d_name }</option>
		</c:forEach>
	</select>
	</td>
	<td> 
	<select id="classs" onchange="refresh()" class="form-control required">
		<option value=""></option>
		<c:forEach items="${classsList }" var ="classs" >
			<option value="${classs.id }">${classs.c_name }</option>
		</c:forEach>
	</select>
	</td>
</tr>
</table>

	<!-- 学生分数排名 -->
	<div class="row-fluid">   
         <div class="col-lg-12" >
            <div id="StuScoreLine" class="widget style1" style="height: 500px">
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
	  		createStuLine(); 
	  		
	  		

	  	});

	 function refresh(){
		 
		 var classsId = $("#classs").val();
		 var departId = $("#depart").val();
		 createStuLine(departId,classsId);
	 }
		
       /*
        *统计前十名学生
        */
       function createStuLine(did,cid){
            var domStuScoreBar = document.getElementById("StuScoreLine");
            var myChartScore = echarts.init(domStuScoreBar);
            var app = {};
            var option = null;
            
	        
 			$.ajax({
                type : "POST",
                url : "${ctx}/analysis/student/StuLineData",
                data:{"departId":did,"classsId":cid},
                dataType : "json",
                success : function(sresult) {
                	//console.log(sresult);
                	option = {
                		    title: {
                		        text: '学生前十名成绩',
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
                                //rotate:-45//旋转
                            },
                            data:sresult.LineName
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
	                		        type: 'line',
	                		        itemStyle: {
	                	                normal: {color: 'rgba(100,2,100,0.5)'}
	                	            },
	                		        data:sresult.LineValue
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