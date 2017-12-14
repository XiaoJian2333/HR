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
	function search(){
		$('#dg').datagrid('load',{
			posName:$('#posName').combobox("getValue")
		});
	}
	function update(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if($('#posName').combobox("getValue")==""){
			$.messager.alert("系统提示","请选择要薪酬项目的设置的职位！");
			return;
		}
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择至少一项！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要设置这<font color=red>"+selectedRows.length+"</font>条项目吗？",function(r){
			if(r){
				$.post("SalaryStandardDetail",{updateIds:ids,posName:$('#posName').combobox("getValue")},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功设置<font color=red>"+result.rsNums+"</font>条项目！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
</script>
</head>
<body style="margin: 5px;">
<table id="dg" title="" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="SalaryItem?add=2" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="20">编号</th>
				<th field="itemName" width="50">薪酬项目</th>
				<th field="isBasic" width="20">是否为基本项目</th>
				<th field="isChose" width="20">是否设置</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			&nbsp;职位类型：&nbsp;
			<input class="easyui-combobox" id="posTypeId" name="posTypeId" size="16" 
			data-options="panelHeight:'auto',
			editable:false,
			valueField:'id',
			textField:'name',
			url:'AddPosition?add=2',
			onSelect:function(rec){
			var url = 'AddPosition?add=4&id='+rec.id;
			$('#posName').combobox('clear');
			$('#posName').combobox('reload', url);}"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职位名称：&nbsp;
			<input name="posName" id="posName" class="easyui-combobox" size="16" 
				data-options="panelHeight:'auto',
				editable:false,
				valueField:'id',
				textField:'name'"/>
			<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			<a href="javascript:update()" class="easyui-linkbutton" style="display:inline-block;float:right">设置</a>
		</div>
	</div>
</body>
</html>