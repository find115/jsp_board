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
//			[1] 입력 - multipart/form-data로 변경됨
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
//			업로드된 파일이 있다면 정보를 추가로 설정(기존 파일 삭제)
			BoardDto origin = bdao.find(bdto.getNo());
//			여기서 강사님꺼랑 db가 달라서 오류남 따로 처리해주어야함.
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
				
				//기존에 있는 파일을 삭제
				if(origin.getFilesize() > 0) {
					File target = new File(path, origin.getServerfile());
					target.delete();
				}
			}
//			업로드된 파일이 없다면 기존의 정보로 유지하도록 설정
			else {
				bdto.setUserfile(origin.getUserfile());
				bdto.setServerfile(origin.getServerfile());
				bdto.setFilesize(origin.getFilesize());
			}
			
//			[2] 처리
			bdao.edit(bdto);
			
//			[3] 출력
			return "redirect:content.do?no="+bdto.getNo();
		}
		return null;
	}

}
