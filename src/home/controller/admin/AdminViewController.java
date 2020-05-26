package home.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.bean.MemberDto;
import home.controller.Controller;

public class AdminViewController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MemberDao mdao = new MemberDao();
		MemberDto mdto = mdao.find(id);
		
		request.setAttribute("mdto", mdto);
		
		return "/WEB-INF/jsp/admin/view.jsp";
	}

}
