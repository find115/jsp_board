package home.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns= {
//	"/member/regist.do",
//	"/member/regist_success.do",
//	"/member/login.do",
	"/member/logout.do",
	"/member/information.do",
//	"/member/login_fail.do",
	"/member/check.do",
	"/member/check_fail.do",
	"/member/pw.do",
	"/member/pw_success.do",
	"/member/pw_fail.do",
	"/member/edit.do",
//	"/member/goodbye.do",
	"/admin/*",
	"/board/*"
})
public class MemberFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		������ �����ϰڽ��ϴ�. id�� power�� �����ʴϱ�?
		HttpServletRequest req = (HttpServletRequest)request;
		String id = (String) req.getSession().getAttribute("login");
		String power = (String) req.getSession().getAttribute("power");
		
//		������ �α��� �������� ���� �̵���Ű�ڽ��ϴ�.
		if(id == null || power == null) {
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect(req.getContextPath()+"/member/login.do");
		}
//		�ƴϸ� ������ ���ʽÿ�.
		else {
			chain.doFilter(request, response);
		}
	}
}








