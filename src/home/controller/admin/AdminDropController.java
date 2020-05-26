package home.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.controller.Controller;

public class AdminDropController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		[1] �Է�		�Ķ���� : id
		String id = request.getParameter("id");
		
//		[2] ó��
		MemberDao mdao = new MemberDao();
		mdao.drop(id);
		
//		[3] ���
//		response.sendRedirect("search.jsp");
		return "redirect:search.do";
	}

}
