<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>薪酬标准信息</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	var array = new Array();
	
	function saveStaff(){										//保存按钮
		var totalmoney=0;
		for(var i=0;i<array.length;i++){
			if(array[i]===5||array[i]===6||array[i]===7||array[i]===8){
				
			totalmoney=totalmoney-Number($("#salary"+array[i]).numberbox('getValue'));
			}else{
				totalmoney=totalmoney+Number($("#salary"+array[i]).numberbox('getValue'));
			}
		}
		totalmoney=totalmoney.toFixed(2);
		$('#totalSalary').numberbox('setValue', totalmoney);
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($('#positionId').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择职位");
					return false;
				}
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
					$("#dlg").dialog("close");
					$("#positionId").combobox('readonly',false);
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function resetValue(){										//重置函数
		$("#flag").nextAll().remove();
		$('#positionId').combobox('clear');
		$("#staffName").val("");
		$("#name").val("");
		$("#maker").val("");
		$('#totalSalary').numberbox('setValue', 0.00);
	}
	
	function closeStaffDialog(){									//关闭按钮
		$("#dlg").dialog("close");
		$("#flag").nextAll().remove();
		$("#fm").find('input,textarea,select').attr('readonly',false);
		$("#registrant").attr('readonly',true);
		$("#registrationTime").attr('readonly',true);
		$('#save').linkbutton('enable');
		$("#positionId").combobox('readonly',false);
		resetValue();
	}
	
	function openStaffModifyDialog(){								//复核按钮
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要复核的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","复核薪酬标准");
		$("#fm").form("load",row);
		$.ajax({
            type: "post",
            url: "standard?add=1",
            data: "positionId=" + $("#positionId").combobox('getValue'),
            success: function (result) {
            	var result = eval('(' + result + ')');
            	
            	$.each(result,function(idx, obj){
            		array[idx] = obj.itemid;
            		$("#i_table").append("<tr>"+"<tr><td colspan='2'>——————————</td></tr>"+
            							      "<td colspan='2'>"+"<input type='text' id='itemid"+obj.itemid+"' name='itemid' readonly value='"+obj.itemid+"'/></td>"+
            							      "<td colspan='2'>"+"<input type='text' id='itemName"+obj.itemid+"' name='itemName' readonly value='"+obj.itemName+"'/></td>"+
            							      "<td colspan='2'>"+"<input type='text' id='salary"+obj.itemid+"' name='salary' class='easyui-numberbox' data-options='min:0,precision:2,value:0.00' value='"+obj.salary+"'  /></td>"+
            							      "</tr>");            		
            	$(function(){
            		$("#salary"+obj.itemid).numberbox({    
            			
            		});
            	});
            	});
            	$(function(){
            		$('#salary1').numberbox({    
            			onChange:function(newValue,oldValue){
            				var money1=$('#salary1').val();
            			
            				if(money1!=0.00){
            					//保留2位小数
            					$('#salary5').numberbox('setValue', (money1*0.08).toFixed(2));
            					$('#salary6').numberbox('setValue', (money1*0.02+3).toFixed(2));
            					$('#salary7').numberbox('setValue', (money1*0.005).toFixed(2));
            					$('#salary8').numberbox('setValue', (money1*0.08).toFixed(2));
            			
            				}
            				
            			}
            		});
            	});
            	
            }
        });
		$("#positionId").combobox('readonly',true);
		url="standard?add=4&id="+row.id+"&state=2";
	}
	function f(){
		$("#flag").nextAll().remove();
		$.ajax({
            type: "post",
            url: "standard?add=1",
            data: "positionId=" + $("#positionId").combobox('getValue'),
            success: function (result) {
            	var result = eval('(' + result + ')');
            	
            	$.each(result,function(idx, obj){
            		array[idx] = obj.itemid;
            		$("#i_table").append("<tr>"+"<tr><td colspan='2'>——————————</td></tr>"+
            							      "<td colspan='2'>"+"<input type='text' id='itemid"+obj.itemid+"' name='itemid' readonly value='"+obj.itemid+"'/></td>"+
            							      "<td colspan='2'>"+"<input type='text' id='itemName"+obj.itemid+"' name='itemName' readonly value='"+obj.itemName+"'/></td>"+
            							      "<td colspan='2'>"+"<input type='text' id='salary"+obj.itemid+"' name='salary' class='easyui-numberbox' data-options='min:0,precision:2,value:0.00' /></td>"+
            							      "</tr>");            		
            	$(function(){
            		$("#salary"+obj.itemid).numberbox({    
            			
            		});
            	});
            	});
            	$(function(){
            		$('#salary1').numberbox({    
            			onChange:function(newValue,oldValue){
            				var money1=$('#salary1').val();
            			
            				if(money1!=0.00){
            					//保留2位小数
            					$('#salary5').numberbox('setValue', (money1*0.08).toFixed(2));
            					$('#salary6').numberbox('setValue', (money1*0.02+3).toFixed(2));
            					$('#salary7').numberbox('setValue', (money1*0.005).toFixed(2));
            					$('#salary8').numberbox('setValue', (money1*0.08).toFixed(2));
            			
            				}
            				
            			}
            		});
            	});
            	
            }
        });
	}
	
</script>
</head>
<body>
	<div style="display:none">
		<p id="username">${currentUser.userName }</p>
	</div>
	<table id="dg" title="薪酬标准信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="standard?add=3" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="50" align="center">编号</th>
				<th field="name" width="100" align="center">标准名称</th>
				<th field="maker" width="50" align="center">制定人</th>
				<th field="registrant" width="150" align="center">登记人</th>
				<th field="registrationTime" width="150" align="center">登记时间</th>
				<th field="totalSalary" width="150" align="center">总额</th>
				<th field="positionId" width="150" align="center" >职位ID</th>
				<th field="state" width="50" align="center">状态</th>
				<th field="divice" width="50" align="center"  hidden="true">建议</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:openStaffModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">复核</a>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog" style="width: 970px;height: 450px" closable="false"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table id="i_table">
				<tr>
					<td>薪酬标准名称：</td>
					<td ><input type="text" name="name" id="name" class="easyui-validatebox" required="true"/></td>
					<td>职位：</td>
					<td >
						<input class="easyui-combobox" id="positionId" name="positionId"  
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'name',
										  url:'AddPosition?add=5',
										  onSelect:function(rec){
										  	f();}"/>
					</td>
					<td>薪酬总额：</td>
					<td><input type="text" class="easyui-numberbox"  data-options="min:0,precision:2" name="totalSalary" id="totalSalary" readonly></td>
				</tr>
				<tr id="flag">
					<td>制定人：</td>
					<td><input type="text" name="maker" id="maker" class="easyui-validatebox" required="true"/></td>
					<td>登记人：</td>
					<td><input type="text" name="registrant" id="registrant" readOnly="true"  value="" /></td>
					<td>建档时间：</td>
					<td><input type="text" name="registrationTime" id="registrationTime" readOnly="true"/></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:saveStaff()" class="easyui-linkbutton" iconCls="icon-ok" id="save">保存</a>
		<a href="javascript:closeStaffDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>