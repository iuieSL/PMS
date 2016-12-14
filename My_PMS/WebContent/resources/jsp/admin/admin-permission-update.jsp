<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String  contextpath=application.getContextPath();
    %>
 <!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=contextpath %>/resources/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=contextpath %>/resources/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=contextpath %>/resources/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=contextpath %>/resources/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="<%=contextpath %>/resources/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=contextpath %>/resources/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>新建网站角色 - 管理员管理 - H-ui.admin v2.3</title>
<meta name="keywords" content="H-ui.admin v2.3,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v2.3，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form   method="post" class="form form-horizontal" id="form-admin-role-update" >
	     <input  type="hidden"   name="id"   value="${sessionScope.Privilege.id}"   id="id"/>  
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  value="${sessionScope.Privilege.name }"   id="roleName" name="name" datatype="*4-16" nullmsg="用户账户不能为空">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"    id="value" name="value"   value="${sessionScope.Privilege.value }">
			</div>
		</div>
		 
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button type="button" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 提交</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<jsp:include page="../index/footer.jsp"/>

<script type="text/javascript" src="<%=contextpath %>/resources/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="<%=contextpath %>/resources/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="<%=contextpath %>/resources/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=contextpath %>/resources/static/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
$(function(){
	$(".permission-list dt input:checkbox").click(function(){
		$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
	});
	$(".permission-list2 dd input:checkbox").click(function(){
		var l =$(this).parent().parent().find("input:checked").length;
		var l2=$(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
		if($(this).prop("checked")){
			$(this).closest("dl").find("dt input:checkbox").prop("checked",true);
			$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
		}
		else{
			if(l==0){
				$(this).closest("dl").find("dt input:checkbox").prop("checked",false);
			}
			if(l2==0){
				$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
			}
		}
	});
	
	/* $("#form-admin-role-add").validate({
		rules:{
			roleName:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	}); */
});

$("#admin-role-save").click(function(){
	  var id=$("#id").val();
	  var name=$('#roleName').val();
	  var value=$('#value').val();
	  $.ajax({
		  type:"post",
		  url:"<%=contextpath %>/update.do",
		  data:"name="+name+"&value="+value+"&id="+id,
		  success:function(data){
				 if(data.trim()!="error"){
					 layer.msg('修改成功');
					 parent.window.table.ajax.reload();
					  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					  parent.layer.close(index);	
					 }else{
						  layer.msg('修改失败');
					 } 
				
			  },
		error:function(){
			 layer.msg('修改失败');
		}
	});
		  
});

</script>
 
</body>
</html>