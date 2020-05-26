package home.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("time", new Date());
		return "/WEB-INF/jsp/test.jsp";
	}

}
