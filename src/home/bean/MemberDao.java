package home.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	public Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "test");
	}

	public void register(MemberDto mdto) throws Exception{
		Connection con = getConnection();
		
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, '일반회원')";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mdto.getId());
		ps.setString(2, mdto.getPw());
		ps.setString(3, mdto.getNick());
		ps.setString(4, mdto.getBirth());
		ps.setString(5, mdto.getTel());
		ps.setString(6, mdto.getEmail());
		ps.setString(7, mdto.getPost());
		ps.setString(8, mdto.getAddr1());
		ps.setString(9, mdto.getAddr2());
		ps.execute();
		
		con.close();
	}

	public boolean login(MemberDto mdto) throws Exception{
		return login(mdto.getId(), mdto.getPw());
	}
	public boolean login(String id, String pw) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from member where id=? and pw=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		ResultSet rs = ps.executeQuery();
		boolean result = rs.next();
		
		con.close();
		return result;
	}

	public List<MemberDto> list() throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from member order by id asc";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<MemberDto> list = new ArrayList<>();
		while(rs.next()) {
			MemberDto mdto = new MemberDto();
			mdto.setResultSet(rs);
			list.add(mdto);
		}
		
		con.close();
		return list;
	}

	public List<MemberDto> search(String type, String key) throws Exception {
		Connection con = getConnection();
		
		String sql = "select * from member where "+type+" like '%'||?||'%' order by "+type+" asc";
//		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		ResultSet rs = ps.executeQuery();
		
		List<MemberDto> list = new ArrayList<>();
		while(rs.next()) {
			MemberDto mdto = new MemberDto();
			mdto.setResultSet(rs);
			list.add(mdto);
		}
		
		con.close();
		return list;
	}

	public MemberDto find(String id) throws Exception {
		Connection con = getConnection();
		
		String sql = "select * from member where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		MemberDto mdto = new MemberDto();
		if(rs.next()) {
			mdto.setResultSet(rs);
		}
		
		con.close();
		return mdto;
	}

	public void changePassword(String id, String npw) throws Exception {
		Connection con = getConnection();
		
		String sql = "update member set pw=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, npw);
		ps.setString(2, id);
		ps.execute();
		
		con.close();
	}

	public void changeInformation(MemberDto mdto) throws Exception {
		Connection con = getConnection();
		
		String sql = "update member set nick=?, email=?, tel=?, post=?, addr1=?, addr2=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mdto.getNick());
		ps.setString(2, mdto.getEmail());
		ps.setString(3, mdto.getTel());
		ps.setString(4, mdto.getPost());
		ps.setString(5, mdto.getAddr1());
		ps.setString(6, mdto.getAddr2());
		ps.setString(7, mdto.getId());
		ps.execute();
		
		con.close();
	}

	public void drop(String id) throws Exception {
		Connection con = getConnection();
		
		String sql = "delete member where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.execute();
		
		con.close();
	}

	public void changeInformationAdmin(MemberDto mdto) throws Exception{
		Connection con = getConnection();
		
		String sql = "update member set nick=?, birth=?, tel=?, email=?, post=?, addr1=?, addr2=?, authority=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mdto.getNick());
		ps.setString(2, mdto.getBirth());
		ps.setString(3, mdto.getTel());
		ps.setString(4, mdto.getEmail());
		ps.setString(5, mdto.getPost());
		ps.setString(6, mdto.getAddr1());
		ps.setString(7, mdto.getAddr2());
		ps.setString(8, mdto.getAuthority());
		ps.setString(9, mdto.getId());
		ps.execute();
		
		con.close();
	}
	
}
























