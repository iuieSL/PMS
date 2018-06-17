/**
 * 权限管理
 * 创建日期：2016/12/10 
 */
var table;
var permission={
		
		$ctx:Comment.getContextPath(),
		//初始化表格
		initTable:function(){
			table=$('#myTable').DataTable({
				   "aoColumnDefs": [{ "bSortable": false, "aTargets": [1,2,3,4]}],
					"bStateSave": true,//状态保存
					"bProcessing":true,  //数据加载的时候显示进度提示
					"bServerSide":true,   //启用服务器端数据导入
					"aLengthMenu": [5,10, 25, 50, "All"],
					"iDisplayLength" : 5, //默认显示的记录数 
					"bPaginate" : true, //是否显示（应用）分页器
					"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数
					"bFilter" : true, //是否启动过滤、搜索功能  bFilter" : true, //是否启动过滤、搜索功能  
					
					"sAjaxSource":permission.$ctx+"/privilegeIndex.do?rand="+Math.random(),
				    //设置操作列的值
				    "fnRowCallback":function(nRow, aData, iDisplayIndex){
				    	
				    	op_update="<a title='编辑' href='javascript:;' onclick=\"permission.admin_permission_edit(this,'权限编辑','inUpdate.do','1','','310')\" class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a> ";
				    	op_del = "<a title='删除' href='javascript:;' onclick=\"permission.admin_permission_del(this,'1')\" class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>";  
			            op_check="<input type='checkbox'   name='privilege'>"
				    	op_html =op_del+op_update;
			            $('td:eq(4)', nRow).html(op_html);  
			            $('td:eq(0)',nRow).html(op_check);
			            return nRow;  
				    },
				    //服务器端的回调
				    "fnServerData":function(sSource, aDataSet, fnCallback){
				    	$.ajax({  
				            "dataType" : 'json',  
				            "type" : "post",  
				            "url" : sSource,  
				            data : {"aoData":JSON.stringify(aDataSet)},
				            "success" : function(resp){  
				                fnCallback(resp);  
				                Comment.checkboxInit();
				                Comment.checkboxAll();
				            }  
				        });  
				    }    
				});		
		},
		/*
		参数解释：
		title	标题
		url		请求的url
		id		需要操作的数据id
		w		弹出层宽度（缺省调默认值）
		h		弹出层高度（缺省调默认值）
	   */
	    /*管理员-权限-添加*/
		admin_permission_add:function(title,url,w,h){
			layer_show(title,url,w,h);
		},
		admin_permission_edit:function(obj,title,url,id,w,h){
			var id=$(obj).parent().parent("tr").children().eq(1).html().trim();
			var relUrl=permission.$ctx+"/"+url+"?id="+id;
			layer_show(title,relUrl,w,h);
		},
		admin_permission_del:function(obj,id){
			var name=$(obj).parent().parent("tr").children().eq(2).html().trim();
			var value=$(obj).parent().parent("tr").children().eq(3).html().trim();
			var id=$(obj).parent().parent("tr").children().eq(1).html().trim();
			Comment.confirm('权限删除须谨慎，确认要删除吗?',permission.$ctx+"/del.do","post","name="+name+"&value="+value+"&id="+id,"已删除","删除失败");
		},
		admin_permission_datadel:function(){
			var list=$("input[name='privilege']:checked");
			var array=new Array();
			$.each(list,function(i, n){
				 var id=$(n).parent().parent().next().html().trim();
				 array.push(id);
			});
			if(!array.length){
				layer.msg("请添加选项");
				return;
			}
		Comment.confirm('权限删除须谨慎，确认要删除吗?',permission.$ctx+"/batchDelete.do","post","id="+array,"已删除","删除失败");
		
		}
}

$(function(){
	/*  分页显示 */
	permission.initTable();
	
});