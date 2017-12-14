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
		$("#dlg1").dialog("open").dialog("setTitle","添加一级机构");
		url="addInsServlet?add=1";
	}
	function openInsTwoAddDlg(){
		$("#dlg2").dialog("open").dialog("setTitle","添加二级机构");
		url="addInsServlet?add=3";
	}
	function openInsThreeAddDlg(){
		$("#dlg3").dialog("open").dialog("setTitle","添加三级机构");
		url="addInsServlet?add=5";
	}
	function closeInsOneDialog(){
		$("#dlg1").dialog("close");
		$("#dlg2").dialog("close");
		$("#dlg3").dialog("close");
		resetValue();
	}
	function resetValue(){
		$("#insName1").val("");
		$("#insName2").val("");
		$("#insName3").val("");
	}
	function saveIns(){
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
					$("#insId21").combobox("reload");
					$("#insId31").combobox("reload");
				}
			}
		});
	}
	function saveIns2(){
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
					$("#insId32").combobox("reload");
				}
			}
		});
	}
	function saveIns3(){
		$("#fm3").form("submit",{
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
					$("#dlg3").dialog("close");
				}
			}
		});
	}
</script>
</head>
<body>
	<div style="text-align:center">
		<a href="javascript:openInsOneAddDlg()" class="easyui-linkbutton" iconCls="icon-add">添加一级机构</a><br><br><br>
		<a href="javascript:openInsTwoAddDlg()" class="easyui-linkbutton" iconCls="icon-add">添加二级机构</a><br><br><br>
		<a href="javascript:openInsThreeAddDlg()" class="easyui-linkbutton" iconCls="icon-add">添加三级机构</a> 
	</div>
	
	
	<div id="dlg1" class="easyui-dialog" style="width: 300px;height: 280px;line-height:140px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons1">
		<form id="fm1" method="post">
			<table>
				<tr>
					<td>机构名称：</td>
					<td><input type="text" name="insName1" id="insName1" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons1">
		<a href="javascript:saveIns()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeInsOneDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<div id="dlg2" class="easyui-dialog" style="width: 300px;height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons2">
		<form id="fm2" method="post">
			<table>
				<tr>
					<td>一级机构：</td>
					<td><input class="easyui-combobox" id="insId21" name="insId1" size="16" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'insName',url:'addInsServlet?add=2'"/></td>
				</tr>
				<tr>
					<td>机构名称：</td>
					<td><input type="text" name="insName2" id="insName2" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons2">
		<a href="javascript:saveIns2()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeInsOneDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<div id="dlg3" class="easyui-dialog" style="width: 300px;height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons3">
		<form id="fm3" method="post">
			<table>
				<tr>
					<td>一级机构：</td>
					<td><input class="easyui-combobox" id="insId31" name="insId1" size="16" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'insName',url:'addInsServlet?add=2',onSelect:function(rec){var url = 'addInsServlet?add=4&id='+rec.id;$('#insId32').combobox('clear');$('#insId32').combobox('reload', url);}"/></td>
				</tr>
				<tr>
					<td>二级机构：</td>
					<td><input class="easyui-combobox" id="insId32" name="insId2" size="16" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'insName'"/></td>
				</tr>
				<tr>
					<td>机构名称：</td>
					<td><input type="text" name="insName3" id="insName3" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons3">
		<a href="javascript:saveIns3()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeInsOneDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>