package home.controller.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import home.bean.MemberDao;
import home.bean.MemberDto;
import home.controller.Controller;

public class MemberLoginController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/jsp/member/login.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
//			[1] �Է�	�Ķ���� : id, pw, [save]
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String save = request.getParameter("save");//on �Ǵ� null
			
//			[2] ó��	MemberDao
			MemberDao mdao = new MemberDao();
			boolean login = mdao.login(id, pw);
			
//			[3] ���
			if(login) {
//				������ �α��� ������ ������Ű�� ���Ͽ� ���ǿ� �����Ǿ����� ����
//				�̸� : login , �� : ������� ID
//				session.setAttribute("login", id);
				HttpSession session = request.getSession();
				session.setAttribute("login", id);
				
//				�̸� : power, �� : ������� ����?
				MemberDto mdto = mdao.find(id);
				session.setAttribute("power", mdto.getAuthority());
				
//				��Ű�� ���õ� ó��
				if(save == null) {		//üũ ���ߴٸ�	�����ϴ� ��Ű�� ����
					Cookie c = new Cookie("saveId", id);
					c.setMaxAge(0);
					response.addCookie(c);
				}
				else {							//üũ �ߴٸ�		4��¥�� ��Ű ����
					Cookie c = new Cookie("saveId", id);
					c.setMaxAge(4 * 7  * 24 * 60 * 60);
					response.addCookie(c);
				}
				
				return "redirect:/index.do";
			}
			else {
				return "redirect:login_fail.do";
			}
		}
		return null;
	}

}
