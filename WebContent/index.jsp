<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>totyuoa办公自动化管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />

<script>
	function setit() {
		document.forms[0].submit();
	}
</script>
<%-- <script type="text/javascript" src="js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts/modules/exporting.js"></script> 
<script type="text/javascript" src="js/jquery-1.8.3.js"></script> --%>

		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="js/highcharts/highcharts.js"></script>
        <script type="text/javascript" src="js/highcharts/modules/exporting.js"></script> 
</head>

<body>
	<div class="top">
		<div class="global-width">
			<img src="Images/logo.gif" class="logo" />
		</div>
	</div>
	<div class="status">
		<div class="global-width">
			[登陆角色：${emp.position.name_CN}] ${emp.name}
			你好！欢迎访问totyuoa办公管理系统！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="#" onclick="location.href='emp!exit'">注销</a>
		</div>
	</div>
	<form id="myForm" name="myForm" action="userInfo!editData.action"
		method="post">
		<input type="hidden" name="u.id" value="26" /> <input type="hidden"
			name="u.sex" value="2" id="u_sex" /> <input type="hidden"
			name="u.supper" value="0" id="u_supper" />
		<div class="main">
			<div class="global-width">
				<div class="nav" id="nav">
					<div class="t"></div>
					<!-- <dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">信息管理
						</dt>
						<dd>
							<a href="forward.action" target="_self">个人信息</a>
						</dd>
					</dl> -->
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							报销单管理</dt>
						<dd>
							<a id="show1">查看报销单</a>
						</dd>
						
							<dd>
								<a id="add1">添加报销单</a>
							</dd>
					</dl>
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							请假管理</dt>
						<dd>
							<a id="show2">查看请假</a>
						</dd>
							<dd>
								<a id="add2">申请请假</a>
							</dd>
					</dl>
					<c:if test="${emp.position.id==3 }">
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							统计报表</dt>
						<dd>
							<a id="v_m">月度统计详情</a>
						</dd>
						<dd>
							<a id="v_y">年度统计详情</a>
						</dd>
					</dl>
					</c:if>
				</div>
				<script type="text/javascript">
					$(function() {
						$(".action").hide();
						$("#hello").show();
						$("#show1").bind("click", tab);
						$("#add1").bind("click", tab1);
						$("#show2").bind("click", tab2);
						$("#add2").bind("click", tab3);
						$("#v_m").bind("click",show_v_m);
						$("#v_y").bind("click",show_v_y);
					});
					//查看月度统计详情
					function show_v_m(){
						$(".action").hide();
						$("#showVoucher").show();
						$("#v_m_tbody").html("");
						$("#tab_showV_Month").show();
						$("#tab_showV_Year").hide();
						$.post("voucher!showMonth", {
						}, function(result) {
							var vmlist=result.vmlist;
							for(var i =0;i<vmlist.length;i++){
								$("#v_m_tbody").append("<tr><td>"+vmlist[i].id+"</td><td>"+vmlist[i].s_count+"</td><td>"+vmlist[i].year+"</td><td>"+vmlist[i].month+"</td><td><input type='button' id='edit' class='edit_01' name='edit_voucher'/></td></td></tr>");
							}
							$("[name='edit_voucher']").bind("click",edit_voucher);
						},"json");
					}
					function edit_voucher(){
						$(".action").hide();
						$("#v_detail").show();
						$("#detail_m").show();
						$("#detail_y").hide();
						$("#d_m_tbody").html("");
						var year=$(this).parent().parent().children().eq(2).html();
						var month=$(this).parent().parent().children().eq(3).html();
						$.post("voucher!findMonth", {
							"year":year,
							"month":month
						}, function(result) {
							var vmlist=result.vmlist;
							var map=result.l;
							for(var i =0;i<vmlist.length;i++){
								$("#d_m_tbody").append("<tr><td>"+vmlist[i].id+"</td><td>"+vmlist[i].dept.name+"</td><td>"+vmlist[i].total_Count+"</td><td>"+vmlist[i].year+"</td><td>"+vmlist[i].month+"</td></tr>");
							}
							 $('#container').highcharts({
							        chart: {
							            plotBackgroundColor: null,
							            plotBorderWidth: 1,//null,
							            plotShadow: false
							        },
							        title: {
							            text: '部门报销占比统计'
							        },
							        tooltip: {
							            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							        },
							        plotOptions: {
							            pie: {
							                allowPointSelect: true,
							                cursor: 'pointer',
							                dataLabels: {
							                    enabled: true,
							                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
							                    style: {
							                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							                    }
							                }
							            }
							        },
							        exporting:{
							                    enabled:true //用来设置是否显示‘打印’,'导出'等功能 
							        },
							        series: [{
							            type: 'pie',
							            name: '占比',
							            data:map
							            //data:[[vmlist[0].dept.name,vmlist[0].total_Count],[vmlist[1].dept.name,vmlist[1].total_Count],[vmlist[2].dept.name,vmlist[2].total_Count]]
							        }]
							    });
				
						},"json");
					}
					//查看年度统计详情
					function show_v_y(){
						$(".action").hide();
						$("#showVoucher").show();
						$("#tab_showV_Month").hide();
						$("#tab_showV_Year").show();
						$("#v_txt").html("年度统计详情");
						$.post("voucher!showYear", {
						}, function(result) {
							var vmlist=result.vylist;
							for(var i =0;i<vmlist.length;i++){
								$("#v_m_tbody").append("<tr><td>"+vmlist[i].id+"</td><td>"+vmlist[i].s_count+"</td><td>"+vmlist[i].year+"</td><td>"+vmlist[i].month+"</td><td><input type='button' id='edit' class='edit_01' name='edit_year'/></td></td></tr>");
							}
							$("[name='edit_year']").bind("click",edit_year);
						},"json");
					}
					
					function edit_year(){
						$(".action").hide();
						$("#v_detail").show();
						$("#detail_y").show();
						$("#detail_m").hide();
						$("#d_m_tbody").html("");
						var year=$(this).parent().parent().children().eq(2).html();
						$.post("voucher!findMonth", {
							"year":year,
						}, function(result) {
							var vmlist=result.vmlist;
							var map=result.l;
							for(var i =0;i<vmlist.length;i++){
								$("#d_y_tbody").append("<tr><td>"+vmlist[i].id+"</td><td>"+vmlist[i].dept.name+"</td><td>"+vmlist[i].total_count+"</td><td>"+vmlist[i].year+"</td></tr>");
							}
							 $('#container').highcharts({
							        chart: {
							            plotBackgroundColor: null,
							            plotBorderWidth: 1,//null,
							            plotShadow: false
							        },
							        title: {
							            text: '部门报销占比统计'
							        },
							        tooltip: {
							            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							        },
							        plotOptions: {
							            pie: {
							                allowPointSelect: true,
							                cursor: 'pointer',
							                dataLabels: {
							                    enabled: true,
							                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
							                    style: {
							                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							                    }
							                }
							            }
							        },
							        exporting:{
							                    enabled:true //用来设置是否显示‘打印’,'导出'等功能 
							        },
							        series: [{
							            type: 'pie',
							            name: '占比',
							            data:map
							            //data:[[vmlist[0].dept.name,vmlist[0].total_Count],[vmlist[1].dept.name,vmlist[1].total_Count],[vmlist[2].dept.name,vmlist[2].total_Count]]
							        }]
							    });
				
						},"json");
					}
					//查看报销单列表
					function tab() {
						$(".action").hide();
						$("#showCliam").show();
						$("#txt").html("查看报销单");
						$("#showcliam_tbody").html("");
						$.post("claim!showClaim", {
						}, function(result) {
							var list=result.list;
							for(var i=0;i<list.length;i++){
								if(list[i].next==null){
									var n=null;
								}else
									n=list[i].next.name;
								$("#showcliam_tbody").append("<tr><td>"+list[i].id+"</td><td>"+list[i].create_Time+"</td><td>"+list[i].create.name+"</td><td>"+list[i].total_Account+"</td><td>"+list[i].status+"</td><td>"+n+"</td>"+
									"<td><input type='button' id='sub' class='sub_01' name="+list[i].id+" /><input type='button' id='edit' class='edit_01' name="+list[i].id+" /></td></tr>");
							}
							$(".sub_01").unbind("click",sub);
							$(".edit_01").unbind("click",edit);
							$(".sub_01").bind("click",sub2);
							$(".edit_01").bind("click",edit2);
						}, "json");
					}
					function sub2(){
						var id=$(this).parent().parent().children().eq(0).html();
					}
					//查看报销单详情
					var s_clist;
					var claim_date;
					function edit2(){
						var id=$(this).parent().parent().children().eq(0).html();
						var poId="${emp.position.id}";
						po_id="${emp.position.id}";
						$.post("claim!showDetail", {
							"id":id
						}, function(result) {
							$("#tab_e_detail").html("");
							$("#tab_d_tbody").html("");
							var list=result.dlist;
							var clist=result.clist;
							s_clist=result.clist;
							claim_date=clist[0].create_Time;
							if(clist[0].next==null){
								var n=null;
							}else
								n=clist[0].next.name;
							$("#tab_e_detail").append("<tr><td colspan='2'>编号："+clist[0].id+"</td><td colspan='2'> 填写人："+clist[0].create.name+"</td><td colspan='2'>部门："+clist[0].create.dept.name+"</td><td colspan='2'>职位："+clist[0].create.position.name_CN+"</td></tr>");
							$("#tab_e_detail").append("<tr><td colspan='2'>总金额："+clist[0].total_Account+"</td><td colspan='2'>填报日期："+clist[0].create_Time+"</td><td colspan='2'>状态："+clist[0].status+"</td><td colspan='2'>处理人："+n+"</td></tr>");
							 for(var i=0;i<list.length;i++){
								$("#tab_d_tbody").append("<tr><td>"+list[i].item+"</td><td>"+list[i].account+"</td><td>"+list[i].des+"</td></tr>");
							}; 
							if(poId==4){
								$("#d_status").html("<option>付款</option>");
							}
							if(poId==1){
								$(".action").hide();
								$("#c_but").hide();
								$("#emp_detail").html("");
								$("#emp_detail").show();
								$("#showDetail").show();
								var rlist=result.rlist;
								for(var i=0;i<rlist.length;i++){
									$("#emp_detail").append("<tr><td colspan='8'><hr></hr></td></tr>");
									$("#emp_detail").append("<tr><td colspan='2'>"+"职位：["+rlist[i].check_emp.position.name_CN+"]"+rlist[i].check_emp.name+"</td><td colspan='2'>"+rlist[i].check_time+"</td><td colspan='2'><span class='font1'>审核：" + rlist[i].result+"</span><td></tr>");
									$("#emp_detail").append("<tr><td colspan='8'>审核意见："+rlist[i].comm+"</td></tr>");
								}
							}else{
								$(".action").hide();
								$("#showDetail").show();
								$("#c_but").show();
								$("#emp_detail").hide();

								var nlist=result.nlist;
								if(nlist==null){
									$("#claim_n_sn").remove();
								}else{
								 for(var i=0;i<nlist.length;i++){
									$("#claim_n_sn").append("<option>"+nlist[i].name+"</option>");
									$("#claim_n_sn").val(nlist[0].name);
								}; }
								
								if(poId==4){
									$("#check_claim").bind("click",add_stats);
								}
								$("#check_claim").bind("click",check_claim);
							}
						}, "json");
					}
					function add_stats(){
						alert(claim_date);
						var t_count=s_clist[0].total_Account;
						var d_id=s_clist[0].create.dept.id;
						$.post("voucher!saveOrUpMonth", {
							"claim_date":claim_date,
							"t_count":t_count,
							"d_id":d_id
						}, function(result) {
							$.post("voucher!saveOrUpYear", {
								"claim_date":claim_date,
								"t_count":t_count,
								"d_id":d_id
							}, function(result) {
							}, "json");
						}, "json");
					}
					
					function check_claim(){
						var p_id=${emp.position.id};
						var n_sn=$("#claim_n_sn").val();
						var sn="${emp.sn}"
						var status=$("#d_status").val();
						var comm=$("#comm").val();
						$.post("claim!upClaim", {
							"p_id":p_id,
							"status":status,
							"n_sn":n_sn
						}, function(result) {
							if(result.msg=="审核成功"){
								$.post("claim!addCheck", {
									"status":status,
									"sn":sn,
									"comm":comm
								}, function(result) {
									alert(result.msg);
									//$(window).attr('location', 'index.jsp');
								},"json");
							}
						}, "json");
					}

					
					//创建报销单***开始
			
					function tab1() {
						$(".action").hide();
						$("#addCliam").show();
						$("#saveAll").bind("click",saveDetail);
						$("#submit").bind("click",saveDetail2);
						$.post("claim!addClaim", {
						}, function(result) {
							var list=result.mlist;
							 for(var i=0;i<list.length;i++){
								$("#n_sn").append("<option>"+list[i]+"</option>");
							}; 
							$("#n_sn").val(list[0]);
						$("#savesub").bind("click",addDetail);
						}, "json");
					}
					function saveDetail(){
						var c_sn=$("#c_sn").html();
						var event=$("#event").val()
						var n_sn=$("#n_sn").val();
						var all_account=parseFloat($("#all_account").val());
						var status='新创建';
						$.post("claim!add", {
							"c_sn":c_sn,
							"event":event,
							"n_sn":n_sn,
							"all_account":all_account,
							"status":status
						}, function(result) {
							if(result.msg=='添加成功'){
								var trlist=$("#tab_detail_tbody").children("tr");
								for(var i = 0;i<trlist.length;i++){
									var td=trlist.eq(i).find("td");
									var item=td.eq(0).html();
									var account=td.eq(1).html();
									var des=td.eq(2).html();
									 $.post("claim!addDetail", {
										"item":item,
										"account":account,
										"des":des
									}, function(result) {
										alert(result.msg);
										//$(window).attr('location', 'index.jsp');
									}, "json"); 
								}
							}else{
								alert(result.msg);
							}
						}, "json");
					}
					function saveDetail2(){
						var c_sn=$("#c_sn").html();
						var event=$("#event").val()
						var n_sn=$("#n_sn").val();
						var all_account=parseFloat($("#all_account").val());
						var status='未审批';
						$.post("claim!add", {
							"c_sn":c_sn,
							"event":event,
							"n_sn":n_sn,
							"all_account":all_account,
							"status":status
						}, function(result) {
							if(result.msg=='添加成功'){
								var trlist=$("#tab_detail_tbody").children("tr");
								for(var i = 0;i<trlist.length;i++){
									var td=trlist.eq(i).find("td");
									var item=td.eq(0).html();
									var account=td.eq(1).html();
									var des=td.eq(2).html();
									 $.post("claim!addDetail", {
										"item":item,
										"account":account,
										"des":des
									}, function(result) {
										alert(result.msg);
										//$(window).attr('location', 'index.jsp');
									}, "json"); 
								}
							}else{
								alert(result.msg);
							}
						}, "json");
					}
					//添加报销单详情
					function addDetail(){
						var item=$("#item").val();
						var account=parseFloat($("#Account").val());
						var des=$("#des").val();
						$("#item").val("");
						$("#Account").val("");
						$("#des").val("");
						$("#tab_detail_tbody").append("<tr><td>"+item+"</td><td>"+account+"</td><td>"+des+"</td><td><input type='button' name='removeDetail' class='delete_01' /></td></tr>")
						$("[name='removeDetail']").unbind("click",removeDetail);
						$("[name='removeDetail']").bind("click",removeDetail);
						var all_account=parseFloat($("#all_account").val());
						var count=0;
						count=all_account+account
						$("#all_account").val(count);
					}
					function removeDetail(){
						var account=parseFloat($(this).parent().parent().children().eq(1).html());
						$(this).parent().parent().remove();
						var all_account=parseFloat($("#all_account").val());
						var count=0;
						count=all_account-account;
						$("#all_account").val(count);
					}
					//创建报销单结束***
					//请假开始***
					//查看请假单
					function tab2() {
						$(".action").hide();
						$("#hello").show();
						$.post("leave!showLeave", {
						}, function(result) {
							$("#tab").html("");
							$("#txt").html("查看请假");
							$("#tab").html("<tr><td>编号</td><td>名称</td><td>请假天数</td><td>创建时间</td><td>审批时间</td><td>状态</td><td>处理人</td><td>操作</td></tr>");
							var list=result.list;
							for(var i=0;i<list.length;i++){
								$("#tab").append("<tr><td>"+list[i].id+"</td><td>"+list[i].emps.name+"</td><td>"+list[i].leaveday+"</td><td>"+list[i].createTime+"</td><td>"+list[i].modifyTime+"</td><td>"+list[i].approve_Option+"</td><td>"+list[i].next.name+"</td>"+
										"<td><input type='button' id='sub' class='sub_01' name="+list[i].id+" /><input type='button' id='edit' class='edit_01' name="+list[i].id+" /></td></tr>");
							}
							$(".sub_01").unbind("click",sub2);
							$(".edit_01").unbind("click",edit2);
							$(".sub_01").bind("click",sub);
							$(".edit_01").bind("click",edit);
						}, "json");
									}
					function tab3() {
						$(".action").hide();
						$("#hello").show();
							$.post("leave!addLeave", {
							}, function(result) {
								$("#tab").html("");
								$("#txt").html("请假申请");
								$("#tab").html("<tr><td>姓名:<input type='text' id='name' value='${emp.name}' readonly='readonly'/></td><td>部门：<select  id='dname'><option>${emp.dept.name}</option></select></td></tr><br/>"
										+ "<tr><td>开始时间：<input type='date' id='StartTime'/></td> <td>结束时间：<input type='date' id='EndTime'/></td></tr><br/>"
										+ "<tr><td>请假天数：<input type='text' id='leaveday'/>(天)</td><td>休假类型：<select id='leavetype'><option>年假</option><option>事假</option><option>婚假</option><option>产假</option><option>病假</option><select></td></tr><br/>"
										+ "<tr><td colspan='2'>请假事由：<textarea rows='5' cols='50' id='reason'></textarea></td></tr><br/>"
										+ "<tr><td coslpan='2' >下一审批人：<select id='next_deal_sn'><option>${list.next.name}</option></select></td></tr>"
										+ "<tr><td coslpan='2'><input type='button' class='submit_01' id='btn' value='提交流程'/>&nbsp;&nbsp;&nbsp;<input type='button' class='submit_01' id='btn2' value='取消'></td></tr>");
								var list=result.mlist;
								 for(var i=0;i<list.length;i++){
									$("#next_deal_sn").append("<option>"+list[i]+"</option>");
								}; 
								 $("#btn").bind("click",click1);
							}, "json");
					}
					function sub(){
						var id=$(this).parent().parent().children().eq(0).html();
						alert(id)

					}
					function edit(){
						var id=$(this).parent().parent().children().eq(0).html();
						var poId="${emp.position.id}"
						alert(poId);
						$.post("leave!findLeave", {
							"id":id							
						}, function(result) {
							var list=result.list[0];
							$("#tab").html("");
							$("#txt").html("请假审批");
							if(poId==1){
								$("#tab").html("<tr><td>姓名:<input type='text' id='name' value="+list.emps.name+" readonly='readonly'/></td><td>部门：<select  id='dname'><option>${emp.dept.name}</option></select></td></tr><br/>"
										+ "<tr><td>开始时间：<input type='text' id='StartTime' value="+list.startTime+" readonly='readonly'/></td> <td>结束时间：<input type='text' id='EndTime' value="+list.endTime+" readonly='readonly'/></td></tr><br/>"
										+ "<tr><td>请假天数：<input type='text' id='leaveday' value="+list.leaveday+" readonly='readonly'/>(天)</td><td>休假类型：<select id='leavetype'><option>"+list.leaveType+"</option><select></td></tr><br/>"
										+ "<tr><td colspan='2'>请假事由：<textarea rows='5' cols='50' id='reason' readonly='readonly'> "+list.reason+" </textarea></td></tr><br/>");
							}else{
								$("#tab").html("<tr><td>姓名:<input type='text' id='name' value="+list.emps.name+" readonly='readonly'/></td><td>部门：<select  id='dname'><option>${emp.dept.name}</option></select></td></tr><br/>"
										+ "<tr><td>开始时间：<input type='text' id='StartTime' value="+list.startTime+" readonly='readonly'/></td> <td>结束时间：<input type='text' id='EndTime' value="+list.endTime+" readonly='readonly'/></td></tr><br/>"
										+ "<tr><td>请假天数：<input type='text' id='leaveday' value="+list.leaveday+" readonly='readonly'/>(天)</td><td>休假类型：<select id='leavetype'><option>"+list.leaveType+"</option><select></td></tr><br/>"
										+ "<tr><td colspan='2'>请假事由：<textarea rows='5' cols='50' id='reason' readonly='readonly'> "+list.reason+" </textarea></td></tr><br/>"
										+"<tr><td coslpan='2'>审批意见:<select id='Approve_Option'><option>同意</option><option>打回</option></select></td><tr>"
										+ "<tr><td coslpan='2'><input type='button' class='submit_01' id='btn2' value='提交'/>&nbsp;&nbsp;&nbsp;<input type='button' class='submit_01' id='btn2' value='取消'></td></tr>");
								 $("#btn2").bind("click",btn2); 
							}
							
						}, "json");
					}
					function btn2(){
						var aOption=$("#Approve_Option").val();
						$.post("leave!upLeave", {
							"aOption":aOption
						}, function(result) {
							alert(result.msg);
							//$(window).attr('location', 'leave!findLeave');
						}, "json");
					}
					function click1(){
						var sn="${emp.sn}";
						var StartTime=$("#StartTime").val();
						var EndTime=$("#EndTime").val();
						var reason=$("#reason").val();
						var next_deal_sn=$("#next_deal_sn").val();
						var leaveday=$("#leaveday").val();
						var leaveType=$("#leavetype").val();
						var CreateTime=new Date();
						var Approve_Option="新创建";
						$.post("leave!add", {
							"sn":sn,
							"sTime":StartTime,
							"eTime":EndTime,
							"reason":reason,
							"next_sn":next_deal_sn,
							"leaveday":leaveday,
							"leaveType":leaveType,
							"CreateTime":CreateTime,
							"Approve_Option":Approve_Option
						}, function(result) {
							alert(result.msg);
						}, "json");
					}
				</script>
				<!-- 报销div -->
				<div class="action" id="showDetail">
					<div class="t">查看报销单详情</div>
					<div class="pages">
						<h1>基本信息</h1>
						<table id="tab_e_detail"  width="90%" border="0" cellspacing="10" cellpadding="10" >
						</table>
						<br/>
						<br/>
						<table id="tab_detail" width="90%" border="0" cellspacing="10" cellpadding="10">
								<tr><td>项目类别</td><td>项目金额</td><td>费用说明</td><td>操作</td></tr>
								<tbody id="tab_d_tbody"></tbody>
						</table>
						<br/>
						<br/>
						<h1>审批结果</h1>
						<table id="emp_detail"  width="90%" border="0" cellspacing="10" cellpadding="10" >
						
						</table>
						<table id="c_but">
							<tr><td>*审批意见:<select id='d_status' ><option>同意</option><option>拒绝</option><option>打回</option></select></td></tr>
							<tr><td colspan='4'>*审批原因：<textarea rows='5' cols='50' id="comm" ></textarea></td><td >下一审批人：<select id='claim_n_sn' name='claim.Next_Deal_sn'></select></td></tr>
							<tr><td><input type='button' id='check_claim' class='submit_01' value="提交"/></td></tr>
						</table>
					</div>
				</div>
				
				<!-- 统计div -->
				<div class="action" id="showVoucher">
					<div class="t" id="v_txt">月度统计详情</div>
					<div class="pages">
						<table id="tab_showV_Month" width="90%" border="0" cellspacing="10" cellpadding="10">
							<tr><td>编号</td><td>总计</td><td>年份</td><td>月份</td><td>操作</td></tr>
							<tbody id="v_m_tbody"></tbody>
						</table>
						<table id="tab_showV_Year" width="90%" border="0" cellspacing="10" cellpadding="10">
							<tr><td>编号</td><td>总计</td><td>年份</td><td>操作</td></tr>
							<tbody id="v_y_tbody"></tbody>
						</table>
					
					</div>
				</div>
				
				<div id="v_detail" class="action">
					<div class="t" id="d_txt">月度统计详情</div>
					<div class="pages">
						<table id="detail_m" width="90%" border="0" cellspacing="10" cellpadding="10">
							<tr><td>编号</td><td>部门</td><td>报销总额</td><td>年份</td><td>月份</td></tr>
							<tbody id="d_m_tbody"></tbody>
						</table>
						<table id="detail_y" width="90%" border="0" cellspacing="10" cellpadding="10">
							<tr><td>编号</td><td>部门</td><td>报销总额</td><td>年份</td></tr>
							<tbody id="d_y_tbody"></tbody>
						</table>
						
						<br/>
						<br/>
						<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
						</div>
					</div>
				</div>
				
				
				
				
				<!-- 报销单操作div -->
				<div class="action" id="showCliam">
					<div class="t">查看报销单</div>
					<div class="pages">
					<table id="tab_showcliam"  width="90%" border="0" cellspacing="10" cellpadding="10">
						<tr><td>编号</td><td>填报日期</td><td>填报人</td><td>总金额</td><td>状态</td><td>处理人</td><td>操作</td></tr>
							<tbody id="showcliam_tbody"></tbody>
					</table>
					</div>
				</div>
				
				<div class="action" id="addCliam">
					<div class="t" >添加报销单</div>
					<div class="pages">
						<table id="tab_detail" width="90%" border="0" cellspacing="10" cellpadding="10">
								<tr><td>项目类别</td><td>项目金额</td><td>费用说明</td><td>操作</td></tr>
								<tr><td><select id='item'><option>城际交通费</option><option>市内交通费</option><option>通讯费</option><option>礼品费</option><option>办公费</option><option>交际餐费</option><option>餐补</option><option>住宿费</option></select></td><td><input type='text' id='Account' /></td><td><input type='text' id='des'/></td><td><input type='button' id='savesub' class='sub_01' /></td></tr>
								<tbody id="tab_detail_tbody"></tbody>
						</table>
							<br></br>
							<br></br>
						<table id="tab_addCliam" >
							<tr><td colspan='2' >*填报人：<label  id="c_sn" >${emp.name}</label></td><td colspan='2'>*填报时间： <label id="c_time">${nowT}</label></td></tr>
							<tr><td colspan='2'>*总金额：<input type='text' name="claim.Total_Account" id="all_account" readonly='readonly' value="0" /></td><td colspan='2'>*状态：<input type='text'  id='status' value='新创建'/></td></tr>
							<tr><td colspan='4'>*</td></tr>
							<tr><td colspan='4'>*事由：<textarea rows='5' cols='50' id='event' name='claim.event'></textarea></td></tr>
							<tr><td colspan='4' >下一审批人：<select id='n_sn' name='claim.Next_Deal_sn'></select></td></tr>
							<tr><td colspan='4'><input type='button' id='saveAll' class='submit_01' value="保存"/> <input type='button' id='submit' class='submit_01' value="提交"/> </td></tr>
						</table>
					</div>
					
				</div>
				
				
				<div class="action" id="hello">
					<div class="t" id="txt">欢迎</div>
					<div class="pages">
					<table width="90%" border="0" cellspacing="0" cellpadding="0" id="tab">
					<tr><td>
						欢迎使用OA办公管理软件
					</td></tr>
					</table>
					</div>
				</div>
				
			</div>
		</div>
	</form>
	<div class="copyright">Copyright &nbsp; &copy; &nbsp; totyuoa</div>

</body>
</html>
