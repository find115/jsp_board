package home.controller.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import home.bean.BoardDao;
import home.bean.BoardDto;
import home.controller.Controller;

public class BoardEditController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao bdao = new BoardDao();
			BoardDto bdto = bdao.find(no);
			request.setAttribute("bdto", bdto);
			
			return "/WEB-INF/jsp/board/edit.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
//			[1] �Է� - multipart/form-data�� �����
			String path = request.getServletContext().getRealPath("/board/file");
			int max = 10 * 1024 * 1024;
			String encoding = "UTF-8";
			DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
			MultipartRequest mRequest = new MultipartRequest(request, path, max, encoding, policy);
			
			BoardDto bdto = new BoardDto();
			bdto.setHead(mRequest.getParameter("head"));
			bdto.setSubject(mRequest.getParameter("subject"));
			bdto.setContent(mRequest.getParameter("content"));
			bdto.setNo(Integer.parseInt(mRequest.getParameter("no")));
			
			BoardDao bdao = new BoardDao();
//			���ε�� ������ �ִٸ� ������ �߰��� ����(���� ���� ����)
			BoardDto origin = bdao.find(bdto.getNo());
//			���⼭ ����Բ��� db�� �޶� ������ ���� ó�����־����.
			if(mRequest.getParameter("status") == null) {
				bdto.setStatus(origin.getStatus());
			}else {
				bdto.setStatus(mRequest.getParameter("status"));
			}
			if(mRequest.getFilesystemName("file") != null) {
				File file = mRequest.getFile("file");
				bdto.setUserfile(mRequest.getOriginalFileName("file"));
				bdto.setServerfile(mRequest.getFilesystemName("file"));
				bdto.setFilesize(file.length());
				
				//������ �ִ� ������ ����
				if(origin.getFilesize() > 0) {
					File target = new File(path, origin.getServerfile());
					target.delete();
				}
			}
//			���ε�� ������ ���ٸ� ������ ������ �����ϵ��� ����
			else {
				bdto.setUserfile(origin.getUserfile());
				bdto.setServerfile(origin.getServerfile());
				bdto.setFilesize(origin.getFilesize());
			}
			
//			[2] ó��
			bdao.edit(bdto);
			
//			[3] ���
			return "redirect:content.do?no="+bdto.getNo();
		}
		return null;
	}

}
