package home.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import home.bean.BoardDao;
import home.bean.BoardDto;

/**
 * �Խ��� ��ϰ� ���õ� ���� ����� ó���ϴ� Ŭ����
 *	- ������ ���� �ݵ�� request�� �ʿ�
 *	- �˻����� / ����¡ / �Խñ� ���:�˻� / �������� / ���� ���
 */
public class PageManager {
	private boolean search;						//�˻� ���
	private int count;									//�� �Խñ� ��
	private int start, finish;						//�Խñ� ����
	private int size = 10;								//1p�� �Խñ� ����
	private int startPage, finishPage;		//������ ǥ�� ����
	private int pageSize = 10;						//������ ǥ�� ����
	private int pageNo;
	private String pageStr;
	private String type, keyword;			//�˻��з� / Ű����
	private int pagecount;
	
	private List<BoardDto> notice;
	private List<BoardDto> list;
	public PageManager(HttpServletRequest request) throws Exception {
		BoardDao bdao = new BoardDao();

		//���������� �ҷ��ɴϴ�
		notice = bdao.noticeList(); 

		//�˻��з�(type)�� �˻���(keyword)�� ����
		type = request.getParameter("type");
		keyword = request.getParameter("keyword");
		search = type != null && keyword != null;
		
		//�� �Խñ� ���� ���� ���Ѵ�
		count = bdao.getCount(getType() , keyword); 
		
		//���۹�ȣ(start)�� �����ȣ(finish)�� ���
		pageStr = request.getParameter("page");
		try{
			pageNo = Integer.parseInt(pageStr);
			if(pageNo <= 0) throw new Exception();
		}catch(Exception e){
			pageNo = 1;
		}
		finish = pageNo * size;
		start = finish - (size - 1);
		if(finish > count) finish = count;//�Ѿ ����
		
		//�ϴ� ��ȣ ���(startPage ~ finishPage)
		pagecount = (count - 1) / size + 1;
		startPage = (pageNo - 1) / size * size + 1;
		finishPage = startPage + (size - 1);
		if(finishPage > pagecount) finishPage = pagecount;//������ �Ѿ ����
		
		if(search){
			list = bdao.search(getType() , keyword, start, finish); 
		}else{
			list = bdao.list(start, finish); 
		}
	}
	public boolean isSearch() {
		return search;
	}
	public int getCount() {
		return count;
	}
	public int getStart() {
		return start;
	}
	public int getFinish() {
		return finish;
	}
	public int getSize() {
		return size;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getFinishPage() {
		return finishPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public String getPageStr() {
		return pageStr;
	}
	public String getType() throws UnsupportedEncodingException {
		if(type == null)
			return null;
		else
			return URLDecoder.decode(type, "UTF-8");
	}
	public String getKeyword() {
		return keyword==null?"":keyword;
	}
	public int getPagecount() {
		return pagecount;
	}
	public List<BoardDto> getNotice() {
		return notice;
	}
	public List<BoardDto> getList() {
		return list;
	}
	
	public boolean isFirst() {
		return startPage == 1;
	}
	public boolean isLast() {
		return finishPage == pagecount;
	}
	public int getPrevpage() {
		return startPage - 1;
	}
	public int getNextpage() {
		return finishPage + 1;
	}
	
	public String getQueryString() throws UnsupportedEncodingException {
		String queryString;
		if(isSearch())
			queryString = "&type="+URLEncoder.encode("subject||content", "UTF-8")+"&keyword="+keyword;
		else
			queryString = "";
		return queryString;
	}
	
	public boolean isTitleMode() {
		return search && type.equals("subject");
	}
	public boolean isWriterMode() {
		return search && type.equals("writer");
	}
	public boolean isDualMode() throws UnsupportedEncodingException {
		return search && type.equals(URLEncoder.encode("subject||content", "UTF-8"));
	}
}







