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
	
	function ckmxStaff(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要查看的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","查看员工信息");
		$("#fm").form("load",row);
		$("#fm").find('input,textarea,select').attr('readonly',true);

		$('#save').linkbutton('disable');
		
	}

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
	
	function getNowFormatDate() {//设置日期
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
	    return currentdate;
	} 
	function openStaffAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加员工信息");
		myDate = new Date();
		$("#registrationTime").val(getNowFormatDate());
		$("#registrant").val($("#username").text());
		url="staff?add=1";
	}
	
	function saveStaff(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($('#Ins1Id').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择一级机构");
					return false;
				}
				if($('#Pos1Id').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择职位类型");
					return false;
				}
				if($('#Pos2Id').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择职位名称");
					return false;
				}
				if($('#TitleId').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择职称");
					return false;
				}
				if($('#sex').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择性别");
					return false;
				}
				return $(this).form("validate");
			},
			success:function(result){
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function resetValue(){
		$("#birthDate").datebox("setValue","");
		$("#Ins1Id").combobox("setValue","");
		$("#Pos1Id").combobox("setValue","");
		$("#TitleId").combobox("setValue","");
		$("#sex").combobox("setValue","");
		$("#nationality").combobox("setValue","");
		$("#ethnicity").combobox("setValue","");
		$("#education").combobox("setValue","");
		$("#educationYear").combobox("setValue","");
		$("#educationMajor").combobox("setValue","");
		$("#standardId").combobox("setValue","");
		$('#Ins2Id').combobox('clear');
		$('#Ins3Id').combobox('clear');
		$('#Pos2Id').combobox('clear');
		$("#staffName").val("");
		$("#email").val("");
		$("#phone").val("");
		$("#qq").val("");
		$("#mobile").val("");
		$("#address").val("");
		$("#zipCode").val("");
		$("#birthPlace").val("");
		$("#religiousBelief").val("");
		$("#politicalAffiliation").val("");
		$("#idCard").val("");
		$("#socialSecurityNumber").val("");
		$("#age").val("");
		$("#bank").val("");
		$("#bankNum").val("");
		$("#specialty").val("");
		$("#hobby").val("");
		$("#personalResume").val("");
		$("#familyRelationships").val("");
		$("#remark").val("");
		
		
	}
	
	function closeStaffDialog(){
		$("#dlg").dialog("close");
		$("#fm").find('input,textarea,select').attr('readonly',false);
		$("#registrant").attr('readonly',true);
		$("#registrationTime").attr('readonly',true);
		$('#save').linkbutton('enable');
		$("#Ins1Id").combobox('readonly',false);
		$("#Ins2Id").combobox('readonly',false);
		$("#Ins3Id").combobox('readonly',false);
		$("#Pos1Id").combobox('readonly',false);
		$("#Pos2Id").combobox('readonly',false);
		$("#TitleId").combobox('readonly',false);
		resetValue();
	}
	
	function openStaffModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		if(row.state=="已删除"){
			$.messager.alert("系统提示","请选择一条未复核或正常状态的数据！");
			return;
		}
		$("#dlg").dialog("open").dialog("setTitle","编辑员工信息");
		$("#fm").form("load",row);
		$("#Ins1Id").combobox('readonly',true);
		$("#Ins2Id").combobox('readonly',true);
		$("#Ins3Id").combobox('readonly',true);
		$("#Pos1Id").combobox('readonly',true);
		$("#Pos2Id").combobox('readonly',true);
		$("#TitleId").combobox('readonly',true);
		url="staff?add=3&id="+row.id+"&state=1";
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
			<a href="javascript:openStaffAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openStaffModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:ckmxStaff()" class="easyui-linkbutton" iconCls="icon-tip" plain="true" id="ckmxbtn">查看明细</a>
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
	
	<div id="dlg" class="easyui-dialog" style="width: 970px;height: 450px" closable="false"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>I级机构：</td>
					<td><input class="easyui-combobox" id="Ins1Id" name="Ins1Id" 
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'insName',
										  url:'addInsServlet?add=2',
										  onSelect:function(rec){
										  	var url = 'addInsServlet?add=4&id='+rec.id;
										  	$('#Ins2Id').combobox('clear');
										  	$('#Ins2Id').combobox('reload', url);
										  }"/>
					</td>
					<td>II级机构：</td>
					<td><input class="easyui-combobox" id="Ins2Id" name="Ins2Id" 
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'insName',
										   onSelect:function(rec){
										  	var url = 'addInsServlet?add=4&id='+rec.id;
										  	$('#Ins3Id').combobox('clear');
										  	$('#Ins3Id').combobox('reload', url);
										  }"/>
					</td>
					<td>III级机构：</td>
					<td colspan="2"><input style="width:245px" class="easyui-combobox" id="Ins3Id" name="Ins3Id"  
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'insName'"/></td>
										  
					<td rowspan="5" colspan="1"><div class="input-group row">
                <div class="col-sm-9 big-photo" style="text-align:center">
                	<div id="preview">
                        <img id="imghead" border="0" src="img/photo_icon.png" width="90" height="90" onclick="$('#previewImg').click();">
                     </div>         
                    <input type="file" name="img" onchange="previewImage(this)" style="display: none;" id="previewImg">
                	<!--<input id="uploaderInput" class="uploader__input" style="display: none;" type="file" accept="" multiple="">-->
                </div>
            </div>
</td>
				</tr>
				<tr>
					<td>职位分类：</td>
					<td><input class="easyui-combobox" id="Pos1Id" name="Pos1Id"  
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'name',
										  url:'AddPosition?add=2',
										  onSelect:function(rec){
										  var url = 'AddPosition?add=4&id='+rec.id;
										  $('#Pos2Id').combobox('clear');
										  $('#Pos2Id').combobox('reload', url);}"/></td>
					<td>职位名称：</td>
					<td><input class="easyui-combobox" id="Pos2Id" name="Pos2Id"  
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'name'"/></td>
					<td>职称：</td>
					<td colspan="2"><input style="width:245px" class="easyui-combobox" id="TitleId" name="TitleId" 
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'name',
										  url:'Title'"/>
					</td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="staffName" id="staffName" class="easyui-validatebox" required="true"/></td>
					<td>性别：</td>
					<td><select class="easyui-combobox" id="sex" name="sex" editable="false" panelHeight="auto" style="width: 147px">
					    <option value="">请选择...</option>
						<option value="男">男</option>
						<option value="女">女</option>
						</select>
					</td>
					<td>Email：</td>
					<td colspan="2"><input style="width:239px" type="text" name="email" id="email" class="easyui-validatebox" data-options="required:true,validType:'email'"/></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" name="phone" id="phone"/></td>
					<td>QQ：</td>
					<td><input type="text" name="qq" id="qq"/></td>
					<td>手机：</td>
					<td colspan="2"><input style="width:239px" type="text" name="mobile" id="mobile" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>住址：</td>
					<td colspan="3"><input type="text" name="address" id="address" style="width:356px"/></td>
					<td>邮编：</td>
					<td colspan="2"><input style="width:239px" type="text" name="zipCode" id="zipCode"/></td>
				</tr>
				<tr>
					<td>国籍：</td>
					<td>
						<select class="easyui-combobox" id="nationality" name="nationality" editable="false" panelHeight="auto" style="width: 147px">
					    <option value="">请选择...</option>
						<option value="中国">中国</option>
						<option value="美国">美国</option>
						<option value="英国">英国</option>
						<option value="法国">法国</option>
						</select>
					</td>
					<td>出生地：</td>
					<td><input type="text" name="birthPlace" id="birthPlace"/></td>
					<td>生日：</td>
					<td><input class="easyui-datebox" name="birthDate" id="birthDate" editable="false"/></td>
					<td>民族：</td>
					<td>
						<select class="easyui-combobox" id="ethnicity" name="ethnicity" editable="false" panelHeight="auto" style="width: 147px">
					    <option value="">请选择...</option>
						<option value="汉族">汉族</option>
						<option value="壮族">壮族</option>
						<option value="藏族">藏族</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>宗教信仰：</td>
					<td>
						<select class="easyui-combobox" id="religiousBelief" name="religiousBelief" editable="false" panelHeight="auto" style="width: 147px">
					    <option value="">请选择...</option>
						<option value="佛教">佛教</option>
						<option value="伊斯兰教">伊斯兰教</option>
						<option value="基督教">基督教</option>
						<option value="其他">其他</option>
						</select>
					</td>
					<td>政治面貌：</td>
					<td>
						<select class="easyui-combobox" id="politicalAffiliation" name="politicalAffiliation" editable="false" panelHeight="auto" style="width: 147px">
					    <option value="">请选择...</option>
						<option value="群众">群众</option>
						<option value="共青团员">共青团员</option>
						<option value="党员">党员</option>
						<option value="其他">其他</option>
						</select>
					</td>
					<td>身份证号码：</td>
					<td><input type="text" name="idCard" id="idCard" /></td>
					<td>社会保障号码：</td>
					<td><input type="text" name="socialSecurityNumber" id="socialSecurityNumber" /></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input type="text" name="age" id="age"/></td>
					<td>学历：</td>
					<td>
						<select class="easyui-combobox" id="education" name="education" editable="false" panelHeight="auto" style="width: 147px">
					    <option value="">请选择...</option>
						<option value="小学">小学</option>
						<option value="初中">初中</option>
						<option value="高中">高中</option>
						<option value="中专">中专</option>
						<option value="大专">大专</option>
						<option value="本科">本科</option>
						<option value="研究生">研究生</option>
						</select>
					</td>
					<td>教育年限：</td>
					<td>
						<select class="easyui-combobox" id="educationYear" name="educationYear" editable="false" panelHeight="auto" style="width: 147px">
					    <option value="">请选择...</option>
						<option value="9">9</option>
						<option value="12">12</option>
						<option value="14">14</option>
						<option value="16">16</option>
						<option value="18">18</option>
						</select>
					</td>
					<td>学历专业：</td>
					<td>
						<select class="easyui-combobox" id="educationMajor" name="educationMajor" editable="false" panelHeight="auto" style="width: 147px">
					    <option value="">请选择...</option>
						<option value="计算机">计算机</option>
						<option value="软件">软件</option>
						<option value="信管">信管</option>
						<option value="英语">英语</option>
						<option value="电气">电气</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>薪酬标准：</td>
					<td><input class="easyui-combobox" id="standardId" name="standardId" 
							data-options="panelHeight:'auto',
										  editable:false,
										  valueField:'id',
										  textField:'name',
										  url:''"/></td>
					<td>开户行：</td>
					<td><input type="text" name="bank" id="bank"/></td>
					<td>银行账号：</td>
					<td><input type="text" name="bankNum" id="bankNum"/></td>
					<td>登记人：</td>
					<td><input type="text" name="registrant" id="registrant" readOnly="true"  value="" /></td>
				</tr>
				<tr>
					<td>建档时间：</td>
					<td><input type="text" name="registrationTime" id="registrationTime" readOnly="true"/></td>
					<td>特长：</td>
					<td><input type="text" name="specialty" id="specialty"/></td>
					<td>爱好：</td>
					<td><input type="text" name="hobby" id="hobby"/></td>
				</tr>
				<tr>
					<td valign="top">个人履历：</td>
					<td colspan="8"><textarea rows="3" cols="100" name="personalResume" id="personalResume"></textarea></td>
				</tr>
				<tr>
					<td valign="top">家庭关系信息：</td>
					<td colspan="8"><textarea rows="3" cols="100" name="familyRelationships" id="familyRelationships"></textarea></td>
				</tr>
				<tr>
					<td valign="top">备注：</td>
					<td colspan="8"><textarea rows="3" cols="100" name="remark" id="remark"></textarea></td>
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