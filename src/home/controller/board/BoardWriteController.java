package home.controller.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import home.bean.BoardDao;
import home.bean.BoardDto;
import home.controller.Controller;

public class BoardWriteController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/jsp/board/write.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
//			[1] �Է� 		�Ķ����(head, status, subject, content) , �ۼ���(session - login)
//			multipart/form-data ����� ó���ϵ��� ����
			String path = request.getServletContext().getRealPath("/board/file");
//			������ ���ٸ� ���� ����
			File dir = new File(path);
			if(!dir.exists()) dir.mkdirs();
			int max = 10 * 1024 * 1024;
			String encoding = "UTF-8";
			DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
			MultipartRequest mRequest = new MultipartRequest(request, path, max, encoding, policy);
			
			BoardDto bdto = new BoardDto();
			bdto.setHead(mRequest.getParameter("head"));
			bdto.setStatus(mRequest.getParameter("status"));
			bdto.setSubject(mRequest.getParameter("subject"));
			bdto.setContent(mRequest.getParameter("content"));
			bdto.setWriter((String) request.getSession().getAttribute("login"));
			
			if(mRequest.getParameter("gno") != null && mRequest.getParameter("gno") != " ") {
				System.out.println("gno = "+mRequest.getParameter("gno"));
				
				bdto.setGno(Integer.parseInt(mRequest.getParameter("gno")));
				bdto.setParent(Integer.parseInt(mRequest.getParameter("parent")));
				bdto.setDepth(Integer.parseInt(mRequest.getParameter("depth")));
			}
			
//			���ε�� ������ �ִٸ� ������ �߰��� ����
			if(mRequest.getFilesystemName("file") != null) {
				File file = mRequest.getFile("file");
				bdto.setUserfile(mRequest.getOriginalFileName("file"));
				bdto.setServerfile(mRequest.getFilesystemName("file"));
				bdto.setFilesize(file.length());
			}
			
//			[2] ó��		BoardDao		(�Է� : bdto , ��� : �Խñ� ��ȣ(int))
			BoardDao bdao = new BoardDao();
			int no = bdao.insert(bdto);
			
//			[3] ���
			return "redirect:content.do?no="+no;
		}
		return null;
	}

}
