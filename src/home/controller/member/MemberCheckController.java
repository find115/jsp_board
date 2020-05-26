package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.controller.Controller;

public class MemberCheckController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/jsp/member/check.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
			request.setCharacterEncoding("UTF-8");
			String id = (String) request.getSession().getAttribute("login");
			String pw = request.getParameter("pw");
			String go = request.getParameter("go");
			
//			[2] ó��
			MemberDao mdao = new MemberDao();
			boolean result = mdao.login(id, pw);
			
//			[3] ���
			if(result) {
//				����ߴٸ� �������� ���ǿ� ÷��
				request.getSession().setAttribute("config", "password");
				return "redirect:"+go;
			}
			else {
				request.setAttribute("go", go); //�̰� �������� �ѱ�� jsp���� �Ķ���ͷ� �޾Ƽ� ��������
				return "redirect:check_fail.do";
			}
		}
		return null;
	}

}
