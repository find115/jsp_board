package home.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyDao {
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
//			Class.forName("oracle.jdbc.OracleDriver");
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sw6", "sw6");
		return source.getConnection();
	}
	
	public void insert(ReplyDto rdto) throws Exception{
		Connection con = getConnection();
		
		String sql = "insert into reply values(reply_seq.nextval, ?, ?, sysdate, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, rdto.getWriter());
		ps.setString(2, rdto.getContent());
		ps.setInt(3, rdto.getOrigin());
		ps.execute();
		
		con.close();
	}
	
	public List<ReplyDto> list(int origin) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from reply where origin=? order by no asc";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, origin);
		ResultSet rs = ps.executeQuery();
		List<ReplyDto> list = new ArrayList<>();
		while(rs.next()) {
			ReplyDto rdto = new ReplyDto();
			rdto.setResultSet(rs);
			list.add(rdto);
		}
		
		con.close();
		return list;
	}


	public int delete(int no) throws Exception{
		Connection con = getConnection();
		
		String sql = "select origin from reply where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int origin = rs.getInt(1);
		
		rs.close();
		ps.close();
		
		sql = "delete reply where no=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.execute();
		
		con.close();
		return origin;
	}
}












