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
//			[1] 입력	파라미터 : id, pw, [save]
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String save = request.getParameter("save");//on 또는 null
			
//			[2] 처리	MemberDao
			MemberDao mdao = new MemberDao();
			boolean login = mdao.login(id, pw);
			
//			[3] 출력
			if(login) {
//				앞으로 로그인 정보를 유지시키기 위하여 세션에 인증되었음을 저장
//				이름 : login , 값 : 사용자의 ID
//				session.setAttribute("login", id);
				HttpSession session = request.getSession();
				session.setAttribute("login", id);
				
//				이름 : power, 값 : 사용자의 권한?
				MemberDto mdto = mdao.find(id);
				session.setAttribute("power", mdto.getAuthority());
				
//				쿠키와 관련된 처리
				if(save == null) {		//체크 안했다면	존재하는 쿠키를 삭제
					Cookie c = new Cookie("saveId", id);
					c.setMaxAge(0);
					response.addCookie(c);
				}
				else {							//체크 했다면		4주짜리 쿠키 발행
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
