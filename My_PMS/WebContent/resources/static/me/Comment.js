/**
 * 工具类
 */

var Comment={
		getContextPath:function(){
			var pathName = document.location.pathname;
		    var index = pathName.substr(1).indexOf("/");
		    var result = pathName.substr(0,index+1);
		    return result;
		},

      checkboxInit:function(){
    	  $('#myTable  input').on('ifChecked', function(event){
		       $(this).attr("checked", true);
		    }).on('ifUnchecked',function(event){
		    	$(this).attr("checked", false);
		    }).iCheck({
		      checkboxClass: 'icheckbox_square-blue',
		      radioClass: 'iradio_square-blue',
		      increaseArea: '20%'
		    });
      },
      checkboxAll:function(){
    	  $('#chkall').on('ifChecked', function(event){
    		    $('#myTable  input').iCheck('check');
    		});
    		$('#chkall').on('ifUnchecked', function(event){
    		    $('#myTable  input').iCheck('uncheck');
    		});
      },
      //对确定弹出框的封装
      confirm:function(context,url,type,data,successContext,failContext){
    	  layer.confirm(context,function(){
				$.ajax({
					 url:url,
					 type:type,
					 data:data,
					 success:function(data){
						  if(data.trim()=="success"){
							  layer_close();
							  layer.msg(successContext,{icon:1,time:2000});
							  table.ajax.reload();
						  }else{
							  layer_close();
							  layer.msg(failContext);
						  }
					 }	 	 
				 })
			});
      }
}