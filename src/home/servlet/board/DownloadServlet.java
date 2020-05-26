package home.servlet.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.BoardDao;
import home.bean.BoardDto;

@WebServlet(urlPatterns="/board/download.do")
public class DownloadServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			[1] 사용자가 다운로드하고 싶은 게시글의 번호를 받는다.
			int no = Integer.parseInt(request.getParameter("no"));
			
//			[2] 번호를 이용하여 파일 정보를 불러온다
			BoardDao bdao = new BoardDao();
			BoardDto bdto = bdao.find(no);
			
			String path = request.getServletContext().getRealPath("/board/file");
			String name = bdto.getServerfile();
			File target = new File(path, name);
			
//			사용자가 받을 이름(한글 처리)
			String sendName = URLEncoder.encode(bdto.getUserfile(), "UTF-8");
			
//			[3] 사용자에게 전송한다(형태 : application/octet-stream, 전송은 byte로 수행)
//				- 파일명, 파일크기, 부연설명 등을 전송할 수 있다
//				- 그냥 전송하면 뭘로 인지할 지 모르니까 사용자에게 다운로드라 알려주겠다
			response.reset();//과거는 잊어라... 새롭게 태어나라
			response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="+sendName);
			response.setHeader("Content-Length", String.valueOf(target.length()));
//			response.setHeader("Content-Description", "부연설명");
			
			byte[] buffer = new byte[1024];
			try(
				InputStream in = new FileInputStream(target);		//파일 입력 스트림
				OutputStream out = response.getOutputStream();	//사용자에게 전송할 스트림
			) {
				while(true) {
					int size = in.read(buffer);	//buffer에 읽어라
					if(size == -1) break;				//EOF면 나가세요
					out.write(buffer, 0, size);	//buffer의 데이터를 0번부터 size개만큼 전송하세요
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}


