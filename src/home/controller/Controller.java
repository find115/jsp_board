package home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *	컨트롤러의 원형 (최상위 계층) 
 */
public interface Controller {
	/**
	 * 실제 컨트롤러가 일하는 메소드
	 * @param request		요청 정보
	 * @param response	응답 정보
	 * @return					다음 목적지 정보
	 * @throws Exception	발생하는 모든 예외
	 */
	String work(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
