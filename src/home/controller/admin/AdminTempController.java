package home.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.controller.Controller;
import home.util.PasswordManager;

public class AdminTempController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
//	 	[1] ���̵� �Է�
		String id = request.getParameter("id");
		
//	 	[2] ���� ��й�ȣ ����
		String pw = PasswordManager.tempPassword();
		
		MemberDao mdao = new MemberDao();
		mdao.changePassword(id, pw);
		
		request.setAttribute("pw", pw);
		
		return "/WEB-INF/jsp/admin/temp.jsp";
	}

}
