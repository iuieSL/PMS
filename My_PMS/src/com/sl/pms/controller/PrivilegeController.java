package com.sl.pms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sl.pms.entity.Privilege;
import com.sl.pms.service.PrivilegeService;

@RequestMapping("/")
@Controller
public class PrivilegeController {
	
	@Autowired
	private PrivilegeService privilegeService;
	
	
	@RequestMapping(value="add" , method=RequestMethod.POST)
	public void add(Privilege privilege,HttpServletResponse response) throws IOException{
		if(!privilegeService.insert(privilege)){  //添加成功
			response.getWriter().write("error");
		}else{
			response.getWriter().write("success");
		}
	}

	//全部查询出来
/*	@RequestMapping("privilegeIndex")
	public String privilegeIndex(HttpSession session){
		
		Privilege p=new Privilege();
		
		List<Privilege> result=privilegeService.findAllPrivilege(p);
		
		session.setAttribute("privilegeList", result);
		
		return "admin/admin-permission";
		 
	}*/
	
	@RequestMapping("privilegeIndex")
	@ResponseBody
	public String privilegeIndex(HttpSession session,HttpServletRequest request,@RequestParam String aoData){
		JSONArray jsonarray = JSONArray.parseArray(aoData);
		 return this.privilegeService.findAllPage(jsonarray);
	}
	
	
	@RequestMapping(value="del",method=RequestMethod.POST)
	public void delete(Privilege privilege,HttpServletResponse response) throws IOException{
		if(this.privilegeService.delete(privilege)){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	
	@RequestMapping(value="batchDelete",method=RequestMethod.POST)
	public void batchDelete(HttpServletResponse response,HttpServletRequest request) throws IOException{
		 String str=request.getParameter("id");
		 
		if(this.privilegeService.batchDelete(str)){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	
	@RequestMapping(value="inUpdate")
	public String inUpdate(HttpServletRequest request){
		String sid=request.getParameter("id");
		Privilege	P=this.privilegeService.findById(sid);
		request.getSession().setAttribute("Privilege", P);
		return  "admin/admin-permission-update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public void update(Privilege privilege,HttpServletResponse response) throws IOException{
		PrintWriter writer=	response.getWriter();
		if(this.privilegeService.update(privilege)){
			writer.write("success");
		}else{
			writer.write("error");
		}
	    	writer.flush();
	}
	
	@RequestMapping(value="serach")
	public String serach(HttpServletRequest request){
		String name=request.getParameter("name").trim();
		List<Privilege> result=this.privilegeService.searchByName(name);
		request.getSession().setAttribute("privilegeList", result);
		return "admin/admin-permission" ;
	}
	
	
}
