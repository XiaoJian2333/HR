<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>人力资源管理系统</title>
		<link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" href="css/style.css">
<script type="text/javascript">

</script>
</head>
<body oncontextmenu="return false">
	  <div class="page-container">
            <h1>人力资源管理系统</h1>
            <form action="login" method="post" >
				<div>
					<input type="text" name="userName" class="username" placeholder="用户名" autocomplete="off" value="${userName }"/>
				</div>
                <div>
					<input type="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false"  value="${password }"/>
                </div>
                <div style="padding-top:20px">
					<p style="color:red">${error }</p>
                </div>
                <button id="submit"  >登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
          </form>
            <div class="connect">
                <p>Welcome to the human resource management system.</p>
				<p style="margin-top:20px;">欢迎登录人力资源管理系统。</p>
            </div>
        </div>
		<div class="alert" style="display:none">
			<h2>消息</h2>
			<div class="alert_con">
				<p id="ts"></p>
				<p style="line-height:70px"><a class="btn">确定</a></p>
			</div>
		</div>
        <!-- Javascript -->
		<script src="jquery-easyui-1.3.3/jquery.min.js" type="text/javascript"></script>
        <script src="js/supersized.3.2.7.min.js"></script>
        <script src="js/supersized-init.js"></script>
		<script>
		window.onload = function()
		{
			$(".connect p").eq(0).animate({"left":"0%"}, 600);
			$(".connect p").eq(1).animate({"left":"0%"}, 400);
		}
		function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
		function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
		</script>
</body>
</html>