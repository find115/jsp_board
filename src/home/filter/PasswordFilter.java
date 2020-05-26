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
 *	��й�ȣ�� �Է����� ���� ä�� �㰡�� �ʿ��� �������� �����ϴ� ���� ����
 *	- �ʿ��� ��� : ������ �˻��Ͽ� �������� �ִ��� ó��(�������� check.do���� �ο� - config=password)
 *	- ���� : /admin/edit.do, /admin/drop.do, 
 *				/member/edit.do, /member/drop.do
 */
@WebFilter(urlPatterns= {
		"/admin/edit.do",//GET/POST �ΰ��ϱ� POST�� ���� ����
		"/admin/drop.do",//GET
		"/member/edit.do",//GET/POST �ΰ��ϱ� POST�� ���� ����
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







