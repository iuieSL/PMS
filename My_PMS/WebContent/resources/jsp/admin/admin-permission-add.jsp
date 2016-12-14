<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String  contextpath=application.getContextPath();
    %>
<!DOCTYPE HTML>
<html>
<head>
 <jsp:include page="../index/header.jsp"></jsp:include>
<title>新建网站角色 - 管理员管理 - H-ui.admin v2.3</title>
</head>
<body>
<article class="page-container">
	<form   method="post" class="form form-horizontal" id="form-admin-permission-add" >
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="name" name="name" datatype="*4-16" nullmsg="用户账户不能为空">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="value" name="value">
			</div>
		</div>
		 
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button type="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 提交</button>
			</div>
		</div>
	</form>
</article>

<jsp:include page="../index/footer.jsp"/> 
<script type="text/javascript" src="<%=contextpath %>/resources/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script> 

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
$(function(){
	  $("#form-admin-permission-add").validate({
		rules:{
			name:{
				required:true,
			},
			value:{
				required:true,
			}
		},
	messages:{
    	name:{
    		required:"请输入权限名称",
    	},
    	value:{
    		required:"请输入这个权限的描述"
    	}
    },
    submitHandler:function(form){
    	var name=$('#name').val();
     	  var value=$('#value').val();
   	  $.ajax({
   		  url:"<%=contextpath %>/add.do",
   		  type:"post",
   		  data:"name="+name+"&value="+value,
   		  success:function(data){		
   				 if(data.trim()=="error"){
   					   layer.msg('该权限已经存在');
   					 }else{
   						 //默认是3s
   					    layer.msg('添加成功',{time:2000, icon: 1},function(){
   					    	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
   			              	   parent.window.table.ajax.reload();
   			                    parent.layer.close(index); 
   					    });
   					 } 
   				
   			  }
   	});
    }
	});  
});
 
</script>
 
</body>
</html>