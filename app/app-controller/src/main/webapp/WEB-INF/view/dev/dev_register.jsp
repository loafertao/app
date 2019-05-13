<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<title>Insert title here</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="../static/css/style1.css">
	<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css.map">
	<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css.map">
	<link rel="stylesheet" type="text/css" href="../static/css/font-awesome.min.css">
	<script type="text/javascript" src="../static/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../static/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		function register() {
			var formData = $("#saveForm").serialize();
			$.ajax({
				url:"register",
				type:"post",
				data: formData,
				dataType:"json",
				success: function(data){
					//获取标志位的结果是什么
					var flag = data.result;
					if (flag == true) {
						//注册成功，将开发者的编号显示在模态框
						var devNo = data.devNo;
						$("#devNo").html(devNo);
						//给按钮设定具体的操作
						$("#modalBtn").click(function() {
							location.href = "devLogin";
						});
						// 开启模态框
						$("#myModal").modal({
							keyboard: false
						});
					}else {
						$("#devNo").html("注册失败，请重新注册");
						// 给按钮设置具体的操作
						$("#modalBtn").click(function() {
							location.href = "register";
						});
						// 开启模态框
						$("#myModal").modal({
							keyboard: false
						});
					}
					
				}
				
			});
		}
	</script>
	</head>
	<body style="background-color: #0BE093;">
		<div class="app-location" style=" width:50%; margin:0 auto;text-align: center; background: #323542; padding:4em;">
			<h2>App信息管理平台</h2>
			<div class="line"><span></span></div>
			<div class="location" style="margin:0 auto"><img src="../static/images/location.png" class="img-responsive" alt="" /></div>
			<form id="saveForm">
				<input type="text" id="devName" name="devName" placeholder="请输入您的姓名">
				<input type="password" id="password" name="password" placeholder="请输入您的密码">
				<input type="text" id="cellphone" name="cellphone" placeholder="请输入您电话号码">
				<input type="text" id="email" name="email" placeholder="请输入您的电子邮箱">
				<textarea rows="" cols="" id="devInfo" name="devInfo" placeholder="请输入您的简介"></textarea>
				<div class="submit"><input type="button" onclick="register();" value="注册"></div>
				<div class="clear"><a href="devLogin" class="btn btn-link" style="font-size: 20px;">
								已经有账号，点击登录
							</a></div>
			</form>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">开发者编号：</h4>
		            </div>
		            <div class="modal-body" style="text-align: center;">
		            	<h1 id="devNo" style="color: black;"></h1>
		            </div>
		            <div class="modal-footer">
		                <button type="button" id="modalBtn" class="btn btn-default">关闭</button>
		            </div>
		        </div>
		    </div>
		</div>
		
	</body>
</html>