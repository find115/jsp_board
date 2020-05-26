package home.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.MemberDao;
import home.bean.MemberDto;
import home.controller.Controller;

public class MemberRegistController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/jsp/member/regist.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
//			[1] 입력	파라미터...9개(id, pw, nick, birth, tel, email, post, addr1, addr2)
//			request.setCharacterEncoding("UTF-8");
			MemberDto mdto = new MemberDto();
			mdto.setId(request.getParameter("id"));
			mdto.setPw(request.getParameter("pw"));
			mdto.setNick(request.getParameter("nick"));
			mdto.setBirth(request.getParameter("birth"));
			mdto.setTel(request.getParameter("tel"));
			mdto.setEmail(request.getParameter("email"));
			mdto.setPost(request.getParameter("post"));
			mdto.setAddr1(request.getParameter("addr1"));
			mdto.setAddr2(request.getParameter("addr2"));
			
//			[2] 처리	JDBC - DAO, DTO
			MemberDao mdao = new MemberDao();
			mdao.register(mdto);
			
//			[3] 출력	redirect.. (/member/regist_success.jsp)
//			response.sendRedirect("regist_success.do");
//			next = "redirect:/member/regist_success.do";	//절대
			return "redirect:regist_success.do";				//상대
		}
		return null;
	}
	
}
