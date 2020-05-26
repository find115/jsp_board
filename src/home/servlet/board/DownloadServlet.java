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
//			[1] ����ڰ� �ٿ�ε��ϰ� ���� �Խñ��� ��ȣ�� �޴´�.
			int no = Integer.parseInt(request.getParameter("no"));
			
//			[2] ��ȣ�� �̿��Ͽ� ���� ������ �ҷ��´�
			BoardDao bdao = new BoardDao();
			BoardDto bdto = bdao.find(no);
			
			String path = request.getServletContext().getRealPath("/board/file");
			String name = bdto.getServerfile();
			File target = new File(path, name);
			
//			����ڰ� ���� �̸�(�ѱ� ó��)
			String sendName = URLEncoder.encode(bdto.getUserfile(), "UTF-8");
			
//			[3] ����ڿ��� �����Ѵ�(���� : application/octet-stream, ������ byte�� ����)
//				- ���ϸ�, ����ũ��, �ο����� ���� ������ �� �ִ�
//				- �׳� �����ϸ� ���� ������ �� �𸣴ϱ� ����ڿ��� �ٿ�ε�� �˷��ְڴ�
			response.reset();//���Ŵ� �ؾ��... ���Ӱ� �¾��
			response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="+sendName);
			response.setHeader("Content-Length", String.valueOf(target.length()));
//			response.setHeader("Content-Description", "�ο�����");
			
			byte[] buffer = new byte[1024];
			try(
				InputStream in = new FileInputStream(target);		//���� �Է� ��Ʈ��
				OutputStream out = response.getOutputStream();	//����ڿ��� ������ ��Ʈ��
			) {
				while(true) {
					int size = in.read(buffer);	//buffer�� �о��
					if(size == -1) break;				//EOF�� ��������
					out.write(buffer, 0, size);	//buffer�� �����͸� 0������ size����ŭ �����ϼ���
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}


