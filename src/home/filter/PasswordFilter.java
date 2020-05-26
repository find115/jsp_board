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

/**
 *	비밀번호를 입력하지 않은 채로 허가가 필요한 페이지에 접근하는 것을 차단
 *	- 필요한 기능 : 세션을 검사하여 인증값이 있는지 처리(인증값은 check.do에서 부여 - config=password)
 *	- 적용 : /admin/edit.do, /admin/drop.do, 
 *				/member/edit.do, /member/drop.do
 */
@WebFilter(urlPatterns= {
		"/admin/edit.do",//GET/POST 두개니깐 POST일 때만 삭제
		"/admin/drop.do",//GET
		"/member/edit.do",//GET/POST 두개니깐 POST일 때만 삭제
		"/member/drop.do"//GET
})
public class PasswordFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String config = (String)req.getSession().getAttribute("config");
		if(config != null && config.equals("password")) {
			if(req.getRequestURI().contains("drop") 
					|| req.getRequestURI().contains("edit") 
					&& req.getMethod().equalsIgnoreCase("post")) {
				req.getSession().removeAttribute("config");
			}
			chain.doFilter(request, response);
		}
		else {
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendError(403);
		}
	}
}







