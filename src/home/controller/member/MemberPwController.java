package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.controller.Controller;

public class MemberPwController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/jsp/member/pw.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
//			[1] �Է�	�Ķ����(pw, npw) + ����(id - login)
			String id = (String) request.getSession().getAttribute("login");
			String pw = request.getParameter("pw");
			String npw = request.getParameter("npw");
			
//			[2] ó��	�α��� -> ��й�ȣ ����
//			[3] ���
			
			MemberDao mdao = new MemberDao();
			if(mdao.login(id, pw)) {
				mdao.changePassword(id, npw);
				return "redirect:pw_success.do";
			}
			else {
				return "redirect:pw_fail.do";
			}
		}
		return null;
	}

}
