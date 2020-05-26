package home.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * UTF8 ó���� ����ϴ� ����
 */
@WebFilter(urlPatterns="/*")
public class UTF8Filter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		chain.doFilter(req, resp);
	}
}
