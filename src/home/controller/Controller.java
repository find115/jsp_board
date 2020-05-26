package home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *	��Ʈ�ѷ��� ���� (�ֻ��� ����) 
 */
public interface Controller {
	/**
	 * ���� ��Ʈ�ѷ��� ���ϴ� �޼ҵ�
	 * @param request		��û ����
	 * @param response	���� ����
	 * @return					���� ������ ����
	 * @throws Exception	�߻��ϴ� ��� ����
	 */
	String work(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
