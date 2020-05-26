package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.controller.Controller;

public class MemberPw_SuccessController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/WEB-INF/jsp/member/pw_success.jsp";
	}

}
