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
//			[1] 입력	파라미터(pw, npw) + 세션(id - login)
			String id = (String) request.getSession().getAttribute("login");
			String pw = request.getParameter("pw");
			String npw = request.getParameter("npw");
			
//			[2] 처리	로그인 -> 비밀번호 변경
//			[3] 출력
			
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
