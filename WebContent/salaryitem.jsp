<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

function save(){
	$("#fm").form("submit",{
		url:'SalaryItem?add=1',
		onSubmit:function(){
			return $(this).form("validate");
		},
		success:function(result){
			var result = eval('(' + result + ')');
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}else{
				$.messager.alert("系统提示","保存成功");
				resetValue();
			}
		}
	});
}
function resetValue(){
	$("#itemName").val("");
}

</script>
</head>
<body>
	
	
	<div>
		<form id="fm" method="post">
			<table>
				<tr>
					<td>薪酬项目名称：</td>
					<td><input type="text" name="itemName" id="itemName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>是否为基本工资？</td>
					<td>是<input type="radio" name="basic" value="1" checked="checked"/>&nbsp;&nbsp;否<input type="radio" name="basic" value="0"/></td>
				</tr>
			</table>
			<a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok">提交</a>
		</form>
	</div>
</body>
</html>