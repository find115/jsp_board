package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.bean.MemberDto;
import home.controller.Controller;

public class MemberEditController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			String id = (String)request.getSession().getAttribute("login");
			MemberDao mdao = new MemberDao();
			MemberDto mdto = mdao.find(id);
			
			request.setAttribute("mdto", mdto);
			
			return "/WEB-INF/jsp/member/edit.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
//			[1] 입력		세션의 id + 파라미터의 nick, tel, email, post, addr1, addr2
//			request.setCharacterEncoding("UTF-8");
			MemberDto mdto = new MemberDto();
			mdto.setId((String)request.getSession().getAttribute("login"));
			mdto.setNick(request.getParameter("nick"));
			mdto.setTel(request.getParameter("tel"));
			mdto.setEmail(request.getParameter("email"));
			mdto.setPost(request.getParameter("post"));
			mdto.setAddr1(request.getParameter("addr1"));
			mdto.setAddr2(request.getParameter("addr2"));
			
//			[2] 처리		MemberDao.changeInformation()
			MemberDao mdao = new MemberDao();
			mdao.changeInformation(mdto);
			
//			[3] 출력
			return "redirect:information.do";
		}
		return null;
	}

}
