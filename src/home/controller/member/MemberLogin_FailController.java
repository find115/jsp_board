package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.controller.Controller;

public class MemberLogin_FailController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/WEB-INF/jsp/member/login_fail.jsp";
	}

}
