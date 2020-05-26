package home.controller.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.BoardDao;
import home.bean.BoardDto;
import home.controller.Controller;

public class BoardDeleteController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		[1] �Է�	�Ķ���� : no , ���� : id, power
		int no = Integer.parseInt(request.getParameter("no"));
		String id = (String) request.getSession().getAttribute("login");
		String power = (String) request.getSession().getAttribute("power");
		
//		[2] ó��
		BoardDao bdao = new BoardDao();
		BoardDto origin = bdao.find(no);
		bdao.delete(no);
		
		//������ �ִ� ������ ����
		if(origin.getFilesize() > 0) {
			String path = request.getServletContext().getRealPath("/board/file");
			File target = new File(path, origin.getServerfile());
			target.delete();
		}
		
//		[3] ���
		return "redirect:list.do";
	}
	
}
