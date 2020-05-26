package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.controller.Controller;

public class MemberCheckController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/jsp/member/check.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
			request.setCharacterEncoding("UTF-8");
			String id = (String) request.getSession().getAttribute("login");
			String pw = request.getParameter("pw");
			String go = request.getParameter("go");
			
//			[2] 처리
			MemberDao mdao = new MemberDao();
			boolean result = mdao.login(id, pw);
			
//			[3] 출력
			if(result) {
//				통과했다면 인증값을 세션에 첨부
				request.getSession().setAttribute("config", "password");
				return "redirect:"+go;
			}
			else {
				request.setAttribute("go", go); //이거 세션으로 넘기면 jsp에서 파라미터로 받아서 문제생김
				return "redirect:check_fail.do";
			}
		}
		return null;
	}

}
