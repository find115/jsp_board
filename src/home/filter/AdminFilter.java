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

@WebFilter(urlPatterns="/admin/*")
public class AdminFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		power�� �������ô�
		HttpServletRequest req = (HttpServletRequest)request;
		String power = (String) req.getSession().getAttribute("power");
		
//		�������ΰ���?
		if(power != null && power.equals("������")) {
			chain.doFilter(request, response);//������ ������
		}
//		�ƴѰ���?
		else {
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendError(403);//403(���� ����)�� �����մϴ�
		}
	}
	
}





