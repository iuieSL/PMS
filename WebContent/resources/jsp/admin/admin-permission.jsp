<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%
   String  contextpath=application.getContextPath();
    %>
<!DOCTYPE HTML>
<html>
<head>
 <jsp:include page="../index/header.jsp"></jsp:include>
<title>权限管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 权限管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	<a href="javascript:;" onclick="permission.admin_permission_datadel()" class="btn btn-danger radius">
	   <i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
	  <a href="javascript:;" onclick="permission.admin_permission_add('添加权限节点','<%=contextpath %>/resources/jsp/admin/admin-permission-add.jsp','','310')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加权限节点</a></span> </div>
	 <input type="search"  disabled="disabled"   style="display:none"  class="input-text " aria-controls="myTable"/>
 	<table class="table table-border table-bordered table-bg table-sort table-hover"   id="myTable">
		<thead>
			<tr>
				<th scope="col" colspan="7">权限节点</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""  id="chkall"></th>
				<th width="40">ID</th>
				<th width="200">权限名称</th>
				<th>备注</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		 <%-- <c:forEach  var="privilege"  items="${sessionScope.privilegeList}"  >
	 
	       <tr class="text-c">
				<td><input type="checkbox" value="${privilege.id }" name="privilege"></td>
				<td data-id="${privilege.id }">${privilege.id }</td>
				<td data-name="${privilege.name }">${privilege.name }</td>
				<td data-value="${privilege.value }">${privilege.value }</td>
				<td>
				<a title="编辑" href="javascript:;" onclick="admin_permission_edit('角色编辑','inUpdate.do?id=${privilege.id }','1','','310')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
				<a title="删除" href="javascript:;" onclick="admin_permission_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>  
		</c:forEach> --%>
		
		</tbody>
	</table>
</div>
<jsp:include page="../index/footer.jsp"/>
<script type="text/javascript" src="<%=contextpath %>/resources/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="<%=contextpath %>/resources/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>  
<script type="text/javascript" src="<%=contextpath %>/resources/static/me/Comment.js"></script>  
<script type="text/javascript" src="<%=contextpath %>/resources/static/me/permission.js"></script>  
 
</body>
</html>