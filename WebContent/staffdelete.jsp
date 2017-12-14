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
//图片上传预览    IE是用了滤镜。
function previewImage(file)
{
  var MAXWIDTH  = 90; 
  var MAXHEIGHT = 90;
  var div = document.getElementById('preview');
  if (file.files && file.files[0])
  {
      div.innerHTML ='<img id=imghead onclick=$("#previewImg").click()>';
      var img = document.getElementById('imghead');
      img.onload = function(){
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        img.width  =  rect.width;
        img.height =  rect.height;
//         img.style.marginLeft = rect.left+'px';
        img.style.marginTop = rect.top+'px';
      }
      var reader = new FileReader();
      reader.onload = function(evt){img.src = evt.target.result;}
      reader.readAsDataURL(file.files[0]);
  }
  else //兼容IE
  {
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
    file.select();
    var src = document.selection.createRange().text;
    div.innerHTML = '<img id=imghead>';
    var img = document.getElementById('imghead');
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
  }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight ){
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        
        if( rateWidth > rateHeight ){
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else{
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}

</script>
<script type="text/javascript">
	var url;
	
	function searchStaff(){
		$('#dg').datagrid('load',{
			 s_Ins1Id:$('#s_Ins1Id').combobox("getValue"),
			s_Ins2Id:$('#s_Ins2Id').combobox("getValue"),
			s_Ins3Id:$('#s_Ins3Id').combobox("getValue"),
			s_btime:$('#s_btime').datebox("getValue"),
			s_etime:$('#s_etime').datebox("getValue"),
			s_state:$('#s_state').combobox("getValue"),
			s_Pos1Id:$('#s_Pos1Id').combobox("getValue"),
			s_Pos2Id:$('#s_Pos2Id').combobox("getValue"), 
		});
	}	
	function deleteStaff(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的档案！");
			return;
		}
		for(var i=0;i<selectedRows.length;i++){
			if(selectedRows[i].state=="已删除"){
				$.messager.alert("系统提示","请选择待复核或正常状态的档案！");
				return;
			}
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条档案吗？",function(r){
			if(r){
				$.post("staff?add=5",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条档案！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	function recoverStaff(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要恢复的档案！");
			return;
		}
		for(var i=0;i<selectedRows.length;i++){
			if(selectedRows[i].state!="已删除"){
				$.messager.alert("系统提示","请选择已删除的档案！");
				return;
			}
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要恢复这<font color=red>"+selectedRows.length+"</font>条档案吗？",function(r){
			if(r){
				$.post("staff?add=6",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功恢复<font color=red>"+result.delNums+"</font>条档案！");
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
	<div style="display:none">
		<p id="username">${currentUser.userName }</p>
	</div>
	<table id="dg" title="员工信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="staff?add=2" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="50" align="center">编号</th>
				<th field="staffNum" width="150" align="center">档案编号</th>
				<th field="staffName" width="100" align="center">姓名</th>
				<th field="sex" width="50" align="center">性别</th>
				<th field="Ins1Id" width="150" align="center">一级机构</th>
				<th field="Ins2Id" width="150" align="center">二级机构</th>
				<th field="Ins3Id" width="150" align="center">三级机构</th>
				<th field="Pos2Id" width="150" align="center">职位名称</th>
				<th field="state" width="50" align="center">状态</th>
				
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:deleteStaff()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除档案</a>
			<a href="javascript:recoverStaff()" class="easyui-linkbutton" iconCls="icon-undo" plain="true" id="ckmxbtn">恢复档案</a>
		</div>
		<div>
		&nbsp;一级机构：&nbsp;<input class="easyui-combobox" id="s_Ins1Id" name="Ins1Id" size="15"
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'insName',
										  url:'addInsServlet?add=2',
										  onSelect:function(rec){
										  	var url = 'addInsServlet?add=4&id='+rec.id;
										  	$('#s_Ins2Id').combobox('clear');
										  	$('#s_Ins2Id').combobox('reload', url);
										  }"/>
		&nbsp;二级机构：&nbsp;<input class="easyui-combobox" id="s_Ins2Id" name="Ins2Id" size="15"
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'insName',
										   onSelect:function(rec){
										  	var url = 'addInsServlet?add=4&id='+rec.id;
										  	$('#s_Ins3Id').combobox('clear');
										  	$('#s_Ins3Id').combobox('reload', url);
										  }"/>
		&nbsp;三级机构：&nbsp;<input class="easyui-combobox" id="s_Ins3Id" name="Ins3Id"  size="15"
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'insName'"/>
		&nbsp;状态：&nbsp;<select class="easyui-combobox" id="s_state" name="s_state" editable="false" panelHeight="auto" size="15">
					    <option value="">请选择...</option>
						<option value="待复核">待复核</option>
						<option value="正常">正常</option>
						<option value="已删除">已删除</option>
						</select>
		</div>
		<div>
		&nbsp;职位分类：&nbsp;<input class="easyui-combobox" id="s_Pos1Id" name="Pos1Id" size="15" 
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'name',
										  url:'AddPosition?add=2',
										  onSelect:function(rec){
										  var url = 'AddPosition?add=4&id='+rec.id;
										  $('#s_Pos2Id').combobox('clear');
										  $('#s_Pos2Id').combobox('reload', url);}"/>
		&nbsp;职位名称：&nbsp;<input class="easyui-combobox" id="s_Pos2Id" name="Pos2Id"  size="15" 
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'name'"/>
		&nbsp;建档时间：&nbsp;<input class="easyui-datebox" name="s_btime" id="s_btime" editable="false" size="15"/>
		-><input class="easyui-datebox" name="s_etime" id="s_etime" editable="false" size="15"/>
		    
		<a href="javascript:searchStaff()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
</body>
</html>