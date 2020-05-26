package home.controller.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.controller.Controller;

public class EventHomeController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/WEB-INF/jsp/event/home.jsp";
	}
}
