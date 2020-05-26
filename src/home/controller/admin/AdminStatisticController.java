package home.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.CountDto;
import home.controller.Controller;

public class AdminStatisticController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, CountDto> map = (Map<String, CountDto>)request.getServletContext().getAttribute("map");//���ø����̼ǿ��� ����
		
		request.setAttribute("map", map);//���� �ϴ� ����.
		
		
		return "/WEB-INF/jsp/admin/statistic.jsp";
	}

}
