package home.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.BoardDao;
import home.bean.BoardDto;
import home.bean.ReplyDao;
import home.bean.ReplyDto;
import home.controller.Controller;

public class BoardContentController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//������ �ҷ�����
		//[1] ��ȣ Ȯ��
		int no = Integer.parseInt(request.getParameter("no"));

		//[2] Dao�� �ҷ��� �Խñ� �ε�
		BoardDao bdao = new BoardDao();
		bdao.plusRead(no); 
		BoardDto bdto = bdao.find(no); 
		
		//[3] ��� ����� �ҷ��´�
		ReplyDao rdao = new ReplyDao();
		List<ReplyDto> replyList = rdao.list(no);
		
		request.setAttribute("bdto", bdto);
		request.setAttribute("replyList", replyList);
		
		return "/WEB-INF/jsp/board/content.jsp";
	}

}
