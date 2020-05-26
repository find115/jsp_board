package home.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.bean.MemberDto;
import home.controller.Controller;

public class AdminSearchController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
//	 	[1] ������ ����
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		boolean search = type != null  && keyword != null;
		
		MemberDao mdao = new MemberDao();
//	 	List<MemberDto> list = �˻��϶� or ����϶�;
		List<MemberDto> list = new ArrayList<>();
		if(search){
			list = mdao.search(type, keyword);
		}
		else{
			list = mdao.list();
		}
		
		request.setAttribute("list", list);
		
		return "/WEB-INF/jsp/admin/search.jsp";
	}

}
