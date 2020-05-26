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
import javax.servlet.http.HttpSession;

import home.bean.BoardDao;
import home.bean.BoardDto;

/**
 *	게시글 수정 / 삭제시 관리자이거나 현재 게시글의 작성자인지 검사하는 필터 
 */
@WebFilter(urlPatterns= {
		"/board/delete.do",
		"/board/edit.do",//multipart/form-data로 변하여 처리하기가 곤란함(pass)
}, filterName="zzzz")
public class BoardSecretFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println(request.getContentType());
		String type = request.getContentType();
		if(type != null && type.startsWith("multipart/form-data")) {
			chain.doFilter(request, response);
			return;
		}
		
//		no와 id, power를 추출하여 검사 후 통과하지 못하면 403 방출
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		try {
			
			int no = Integer.parseInt(req.getParameter("no"));
			String id = (String)session.getAttribute("login");
			String power = (String)session.getAttribute("power");
			
			BoardDao bdao = new BoardDao();
			BoardDto bdto = bdao.find(no);
			
			if(power.equals("관리자") || bdto.getWriter().equals(id)) {
				chain.doFilter(request, response);
			}
			else {
				throw new Exception();
			}
		}catch(Exception e) {
			resp.sendError(403);
		}
	}

}









