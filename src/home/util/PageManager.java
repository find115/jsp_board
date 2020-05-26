package home.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import home.bean.BoardDao;
import home.bean.BoardDto;

/**
 * 게시판 목록과 관련된 각종 계산을 처리하는 클래스
 *	- 생성을 위해 반드시 request가 필요
 *	- 검색여부 / 페이징 / 게시글 목록:검색 / 공지사항 / 등을 계산
 */
public class PageManager {
	private boolean search;						//검색 모드
	private int count;									//총 게시글 수
	private int start, finish;						//게시글 구간
	private int size = 10;								//1p당 게시글 개수
	private int startPage, finishPage;		//페이지 표시 구간
	private int pageSize = 10;						//페이지 표시 개수
	private int pageNo;
	private String pageStr;
	private String type, keyword;			//검색분류 / 키워드
	private int pagecount;
	
	private List<BoardDto> notice;
	private List<BoardDto> list;
	public PageManager(HttpServletRequest request) throws Exception {
		BoardDao bdao = new BoardDao();

		//공지사항을 불러옵니다
		notice = bdao.noticeList(); 

		//검색분류(type)와 검색어(keyword)를 수신
		type = request.getParameter("type");
		keyword = request.getParameter("keyword");
		search = type != null && keyword != null;
		
		//총 게시글 수를 먼저 구한다
		count = bdao.getCount(getType() , keyword); 
		
		//시작번호(start)와 종료번호(finish)를 계산
		pageStr = request.getParameter("page");
		try{
			pageNo = Integer.parseInt(pageStr);
			if(pageNo <= 0) throw new Exception();
		}catch(Exception e){
			pageNo = 1;
		}
		finish = pageNo * size;
		start = finish - (size - 1);
		if(finish > count) finish = count;//넘어감 방지
		
		//하단 번호 계산(startPage ~ finishPage)
		pagecount = (count - 1) / size + 1;
		startPage = (pageNo - 1) / size * size + 1;
		finishPage = startPage + (size - 1);
		if(finishPage > pagecount) finishPage = pagecount;//페이지 넘어감 방지
		
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







