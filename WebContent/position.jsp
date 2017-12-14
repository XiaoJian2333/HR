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
	var url;
	function openInsOneAddDlg(){
		$("#dlg1").dialog("open").dialog("setTitle","添加职位类型");
		url="AddPosition?add=1";
	}
	function openInsTwoAddDlg(){
		$("#dlg2").dialog("open").dialog("setTitle","添加职位");
		url="AddPosition?add=3";
	}
	function closeDialog(){
		$("#dlg1").dialog("close");
		$("#dlg2").dialog("close");
		resetValue();
	}
	function resetValue(){
		$("#posTpye").val("");
		$("#posName").val("");
	}
	function savePosType(){
		$("#fm1").form("submit",{
			url:url,
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
					$("#dlg1").dialog("close");
					$("#posTypeId").combobox("reload");
				}
			}
		});
	}
	function savePosName(){
		$("#fm2").form("submit",{
			url:url,
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
					$("#dlg2").dialog("close");
				}
			}
		});
	}
</script>
</head>
<body>
	<div style="text-align:center">
		<a href="javascript:openInsOneAddDlg()" class="easyui-linkbutton" iconCls="icon-add">添加职位类型</a><br><br><br>
		<a href="javascript:openInsTwoAddDlg()" class="easyui-linkbutton" iconCls="icon-add">&nbsp;&nbsp;&nbsp;添加职位&nbsp;&nbsp;&nbsp;</a><br><br><br>
	</div>
	
	<div id="dlg1" class="easyui-dialog" style="width: 300px;height: 280px;line-height:140px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons1">
		<form id="fm1" method="post">
			<table>
				<tr>
					<td>职位类型：</td>
					<td><input type="text" name="posType" id="posTpye" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons1">
		<a href="javascript:savePosType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<div id="dlg2" class="easyui-dialog" style="width: 300px;height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons2">
		<form id="fm2" method="post">
			<table>
				<tr>
					<td>职位类型：</td>
					<td><input class="easyui-combobox" id="posTypeId" name="posTypeId" size="16" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'AddPosition?add=2'"/></td>
				</tr>
				<tr>
					<td>职位名称：</td>
					<td><input type="text" name="posName" id="posName" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons2">
		<a href="javascript:savePosName()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>