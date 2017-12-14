<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人力资源管理系统</title>
<%
	// 权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	// 数据

	
	// 实例化树菜单
	$("#tree").tree({
			lines:true,
			url:'auth?parentId=-1',
			onLoadSuccess:function(){
				$("#tree").tree('expandAll');
			},
			onClick:function(node){
				if(node.attributes.authPath){
					openTab(node);
				}
			}
	});
	
	// 新增Tab
	function openTab(node){
		if($("#tabs").tabs("exists",node.text)){
			$("#tabs").tabs("select",node.text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src="+node.attributes.authPath+"></iframe>"
			$("#tabs").tabs("add",{
				title:node.text,
				closable:true,
				content:content
			});
		}
	}
});
</script>
</head>
<body class="easyui-layout">
	<div region="north" style="height: 80px;background-color: #E0EDFF">
		<div align="left" style="width: 80%;float: left"><img src="img/1511665312_713374.png"height="75px"></div>
		<div style="padding-top: 50px;padding-right: 20px;">当前用户：&nbsp;<font color="red" >${currentUser.userName }</font></div>
		
	</div>
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" >
				<div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用</font></div>
			</div>
		</div>
	</div>
	<div region="west" style="width: 200px;" title="导航菜单" split="true">
		<ul id="tree" class="easyui-tree"></ul>
	</div>
	<div region="south" style="height: 25px;" align="center">版权所有<a href="http://www.baidu.com">www.baidu.com</a></div>
</body>
</html>