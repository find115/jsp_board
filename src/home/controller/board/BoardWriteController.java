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
//			[1] 입력 		파라미터(head, status, subject, content) , 작성자(session - login)
//			multipart/form-data 방식을 처리하도록 설계
			String path = request.getServletContext().getRealPath("/board/file");
//			폴더가 없다면 폴더 생성
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
			
//			업로드된 파일이 있다면 정보를 추가로 설정
			if(mRequest.getFilesystemName("file") != null) {
				File file = mRequest.getFile("file");
				bdto.setUserfile(mRequest.getOriginalFileName("file"));
				bdto.setServerfile(mRequest.getFilesystemName("file"));
				bdto.setFilesize(file.length());
			}
			
//			[2] 처리		BoardDao		(입력 : bdto , 출력 : 게시글 번호(int))
			BoardDao bdao = new BoardDao();
			int no = bdao.insert(bdto);
			
//			[3] 출력
			return "redirect:content.do?no="+no;
		}
		return null;
	}

}
