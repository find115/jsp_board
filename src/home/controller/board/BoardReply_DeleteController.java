package home.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.BoardDao;
import home.bean.ReplyDao;
import home.controller.Controller;

public class BoardReply_DeleteController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		
		ReplyDao rdao = new ReplyDao();
		int origin = rdao.delete(no);
		BoardDao bdao = new BoardDao();
		bdao.setCount(origin);
		
		return "redirect:content.do?no="+origin;
	}

}
