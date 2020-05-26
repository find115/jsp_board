package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.controller.Controller;

public class MemberDropController implements Controller{
	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		[1] �Է�		������id
		String id = (String)request.getSession().getAttribute("login");
		
//		[2] ó��
		request.getSession().removeAttribute("login");
		request.getSession().removeAttribute("power");
		
		MemberDao mdao = new MemberDao();
		mdao.drop(id);
		
		return "redirect:goodbye.do";
	}
}
