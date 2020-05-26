package home.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.bean.MemberDto;
import home.controller.Controller;

public class AdminEditController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			String id = request.getParameter("id");
			MemberDao mdao = new MemberDao();
			MemberDto mdto = mdao.find(id);
			
			request.setAttribute("mdto", mdto);
			
			return "/WEB-INF/jsp/admin/edit.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")){
//			[1] �Է�		�Ķ������ id, nick, birth, tel, email, post, addr1, addr2, authority
			MemberDto mdto = new MemberDto();
			mdto.setId(request.getParameter("id"));
			mdto.setNick(request.getParameter("nick"));
			mdto.setBirth(request.getParameter("birth"));
			mdto.setTel(request.getParameter("tel"));
			mdto.setEmail(request.getParameter("email"));
			mdto.setPost(request.getParameter("post"));
			mdto.setAddr1(request.getParameter("addr1"));
			mdto.setAddr2(request.getParameter("addr2"));
			mdto.setAuthority(request.getParameter("authority"));
			
//			[2] ó��		MemberDao.changeInformation()
			MemberDao mdao = new MemberDao();
			mdao.changeInformationAdmin(mdto);
			
			
//			[3] ���
			return "redirect:view.do?id="+mdto.getId();
		}
		return null;
	}

}
