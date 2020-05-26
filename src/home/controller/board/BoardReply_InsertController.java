package home.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.BoardDao;
import home.bean.ReplyDao;
import home.bean.ReplyDto;
import home.controller.Controller;

public class BoardReply_InsertController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReplyDto rdto = new ReplyDto();
		rdto.setWriter(request.getParameter("writer"));
		rdto.setContent(request.getParameter("content"));
		rdto.setOrigin(Integer.parseInt(request.getParameter("origin")));
		
		ReplyDao rdao = new ReplyDao();
		rdao.insert(rdto);
		BoardDao bdao = new BoardDao();
		bdao.setCount(rdto.getOrigin());
		
		return "redirect:content.do?no="+rdto.getOrigin();
	}

}
