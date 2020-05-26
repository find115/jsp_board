package home.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {
	
	//이미 context.xml에 자원을 등록해 두었습니다.
	//불러와서 쓸 수 있도록 참조를 생성해야 합니다.
	private static DataSource source;
	static {//static 전용 초기화 구문
		try {
			Context context = new InitialContext();
			source = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws Exception{
//		Class.forName("oracle.jdbc.OracleDriver");
//		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sw6", "sw6");
		return source.getConnection();
	}

	public int insert(BoardDto bdto) throws Exception {
		Connection con = getConnection();

//		[1] 번호를 미리 뽑아서
		String sql = "select board_seq.nextval from dual";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int no = rs.getInt(1);
		
		rs.close();
		ps.close();//연속 명령 전송시
		
		if(bdto.getGno() == 0) {//새글이면
			bdto.setGno(no);		//gno를 no로 설정
		}
		if(bdto.getStatus()==null) bdto.setStatus("일반");
		if(bdto.getUserfile()==null) {
			bdto.setUserfile(""); bdto.setServerfile(""); bdto.setFilesize(0);
		}
		
//		[2] 등록 처리
		sql = "insert into board values(?, ?, ?, ?, ?, sysdate, 0, ?, 0, ?, ?, ?, ?, ?, ?)";
		ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.setString(2, bdto.getHead());
		ps.setString(3, bdto.getSubject());
		ps.setString(4, bdto.getWriter());
		ps.setString(5, bdto.getContent());
		ps.setString(6, bdto.getStatus());
		ps.setInt(7, bdto.getGno());
		ps.setInt(8, bdto.getParent());
		ps.setInt(9, bdto.getDepth());
		ps.setString(10, bdto.getUserfile());
		ps.setString(11, bdto.getServerfile());
		ps.setLong(12, bdto.getFilesize());
		ps.execute();
		
		con.close();
		return no;
	}
	
	
	public BoardDto find(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from board where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ResultSet rs = ps.executeQuery();//1개 또는 0개
		
		BoardDto bdto = new BoardDto();
		if(rs.next()) {
			bdto.setResultSet(rs);
		}
		
		con.close();
		return bdto;
	}
	
	public void plusRead(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "update board set read=read+1 where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.execute();
		
		con.close();
	}
	
	public List<BoardDto> search(String type, String keyword, int start, int finish) throws Exception{
		Connection con = getConnection();
		
		String sql = 
				"select * from ("
					+ "select rownum rn, A.* from ("
							+ "select * from board where "+type+" like '%'||?||'%' "
							+ "start with parent=0 "
							+ "connect by prior no=parent "
							+ "order siblings by gno desc, no asc"
					+ ")A"
			+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		ps.setInt(2, start);
		ps.setInt(3, finish);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> list = new ArrayList<>();
		while(rs.next()) {
			BoardDto bdto = new BoardDto();
			bdto.setResultSet(rs);
			list.add(bdto);
		}
		
		con.close();
		return list;
	}
	
	public List<BoardDto> list(int start, int finish) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from ("
									+ "select rownum rn, A.* from ("
											+ "select * from board "
											+ "start with parent=0 "
											+ "connect by prior no=parent "
											+ "order siblings by gno desc, no asc"
									+ ")A"
							+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, start);
		ps.setInt(2, finish);
		ResultSet rs = ps.executeQuery();//0 to many
		List<BoardDto> list = new ArrayList<>();
		while(rs.next()) {
			BoardDto bdto = new BoardDto();
			bdto.setResultSet(rs);
			list.add(bdto);
		}
		
		con.close();
		return list;
	}
	
	public List<BoardDto> noticeList() throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from board where status='공지' order by no desc";//최신순
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();//0 to many
		List<BoardDto> list = new ArrayList<>();
		while(rs.next()) {
			BoardDto bdto = new BoardDto();
			bdto.setResultSet(rs);
			list.add(bdto);
		}
		
		con.close();
		return list;
	}
	
	public boolean delete(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "delete board where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		int result = ps.executeUpdate();
		
		con.close();
		return result > 0;
	}

	public void edit(BoardDto bdto) throws Exception{
		Connection con = getConnection();
		
		String sql = "update board set "
				+ "subject=?, content=?, head=?, status=?, userfile=?, serverfile=?, filesize=? "
				+ "where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, bdto.getSubject());
		ps.setString(2, bdto.getContent());
		ps.setString(3, bdto.getHead());
		ps.setString(4, bdto.getStatus());
		ps.setString(5, bdto.getUserfile());
		ps.setString(6, bdto.getServerfile());
		ps.setLong(7, bdto.getFilesize());
		ps.setInt(8, bdto.getNo());
		ps.execute();
		
		con.close();
	}
	
	public int getCount(String type, String keyword) throws Exception{
		boolean search = type != null && keyword != null;
		
		String sql = "select count(*) from board";
		
		if(search) 
			sql += " where "+type+" like '%'||?||'%'";
		
		System.out.println(sql);
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		if(search) {
			ps.setString(1, keyword);
		}
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		con.close();
		return count;
	}

	public void setCount(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "update board set "
								+ "reply=(select count(*) from reply where origin=?) "
								+ "where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.setInt(2, no);
		ps.execute();
		
		con.close();
	}
}










