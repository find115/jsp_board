package home.filter;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import home.bean.CountDao;
import home.bean.CountDto;

/**
 * ������ �� ��û(request) ���ڿ� ������(user) ���� �����ִ� ����
 */
@WebFilter(urlPatterns="/*")
public class CountFilter implements Filter{

	private ServletContext context;
	
	public String getToday() {
		Date d = new Date();
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		String today = f.format(d);
		return today;
	}
	
	public Map<String, CountDto> getMap(){
		return (Map<String, CountDto>) context.getAttribute("map");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		�ʱ�ȭ
		context = filterConfig.getServletContext();
		
		try {
			CountDao cdao = new CountDao();
			Map<String, CountDto> map = cdao.map();
			context.setAttribute("map", map);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert() {
		try {
			CountDao cdao = new CountDao();
			Map<String, CountDto> map = getMap();
			cdao.insert(map);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		insert();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		�����ڴ� ��� ��û���� ����ó��
		Map<String, CountDto> map = getMap();
		
		CountDto cdto = map.get(getToday());
		if(cdto != null)
			cdto.increaseRequest();
		else {
			cdto = new CountDto();
			cdto.setDay(getToday());
			cdto.setRequest(1);
			map.put(getToday(), cdto);
		}
		
//		����ڴ� �ű� ������ ��츸 ó��
		HttpServletRequest req = (HttpServletRequest)request;
		if(req.getSession().isNew())
			cdto.increasePeople();
		
//		System.out.println(cdto);
		chain.doFilter(request, response);
	}

}


