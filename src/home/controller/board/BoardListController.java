package home.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.controller.Controller;
import home.util.PageManager;

public class BoardListController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageManager pm = new PageManager(request);
		
		request.setAttribute("pm", pm);
		
		return "/WEB-INF/jsp/board/list.jsp";
	}

}
