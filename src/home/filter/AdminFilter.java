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
//		power를 꺼내봅시다
		HttpServletRequest req = (HttpServletRequest)request;
		String power = (String) req.getSession().getAttribute("power");
		
//		관리자인가요?
		if(power != null && power.equals("관리자")) {
			chain.doFilter(request, response);//가던길 가세요
		}
//		아닌가요?
		else {
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendError(403);//403(권한 없음)을 선언합니다
		}
	}
	
}






