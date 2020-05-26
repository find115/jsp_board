package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.bean.MemberDto;
import home.controller.Controller;

public class MemberInforController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = (String)request.getSession().getAttribute("login");
		MemberDao mdao = new MemberDao();
		MemberDto mdto = mdao.find(id);
		
		request.setAttribute("mdto", mdto);
		
		return "/WEB-INF/jsp/member/information.jsp";
	}

}
