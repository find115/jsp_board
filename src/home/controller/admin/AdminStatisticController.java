package home.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.bean.CountDto;
import home.controller.Controller;

public class AdminStatisticController implements Controller{

	@Override
	public String work(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, CountDto> map = (Map<String, CountDto>)request.getServletContext().getAttribute("map");//어플리케이션에서 꺼냄
		
		request.setAttribute("map", map);//여기 하다 끝남.
		
		
		return "/WEB-INF/jsp/admin/statistic.jsp";
	}

}
