package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.controller.Controller;

public class MemberLogoutController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("login");
		request.getSession().removeAttribute("power");
		return "redirect:/index.do";
	}
	
}
