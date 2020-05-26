package home.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

public class CountDao {
	
//	public Connection getConnection() throws Exception{
//		Class.forName("oracle.jdbc.OracleDriver");
//		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sw6", "sw6");
//	}
//	
//	public void insert(CountDto cdto) throws Exception{
//		Connection con = getConnection();
//		
//		String sql = "insert into statistic values(sysdate, ?, ?)";
//		PreparedStatement ps = con.prepareStatement(sql);
//		ps.setInt(1, cdto.getRequest());
//		ps.setInt(2, cdto.getPeople());
//		ps.execute();
//		
//		con.close();
//	}
//	
//	public List<CountDto> list() throws Exception{
//		Connection con = getConnection();
//		
//		String sql = "select "
//										+ "to_char(day, 'YYYY-MM-DD') as ��¥, "
//										+ "sum(request) as ��û��, "
//										+ "sum(people) as ����ڼ� "
//							+ "from statistic "
//										+ "group by to_char(day, 'YYYY-MM-DD') "
//										+ "order by to_char(day, 'YYYY-MM-DD')";
//		PreparedStatement ps = con.prepareStatement(sql);
//		ResultSet rs = ps.executeQuery();
//		
//		List<CountDto> list = new ArrayList<>();
//		while(rs.next()) {
//			CountDto cdto = new CountDto();
//			cdto.setDay(rs.getString("��¥"));
//			cdto.setRequest(rs.getInt("��û��"));
//			cdto.setPeople(rs.getInt("����ڼ�"));
//			list.add(cdto);
//		}
//		
//		con.close();
//		return list;
//	}
//
//	public CountDto get(String today) throws Exception{
//		Connection con = getConnection();
//		
//		String sql = "select "
//										+ "to_char(day, 'YYYY-MM-DD') as ��¥, "
//										+ "sum(request) as ��û��, "
//										+ "sum(people) as ����ڼ� "
//							+ "from statistic "
//										+ "group by to_char(day, 'YYYY-MM-DD') "
//										+ "having to_char(day, 'YYYY-MM-DD') = ?";
//		PreparedStatement ps = con.prepareStatement(sql);
//		ps.setString(1, today);
//		ResultSet rs = ps.executeQuery();
//		
//		CountDto cdto = new CountDto();
//		cdto.setDay(today);
//		if(rs.next()) {
//			cdto.setRequest(rs.getInt("��û��"));
//			cdto.setPeople(rs.getInt("����ڼ�"));
//		}
//		
//		con.close();
//		return cdto;
//	}
	
//	���� �̿��ϵ��� ����(statistic.db)
	private File target = new File(System.getProperty("user.dir"), "statistic.db");
	
	public void insert(Map<String, CountDto> map) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(target));){
			out.writeObject(map);
		}catch(Exception e) {}
	}
	
	public Map<String, CountDto> map(){
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(target));){
			Map<String, CountDto> map = (Map<String, CountDto>) in.readObject();
			return map;
		}catch(Exception e) {
			return new TreeMap<>();
		}
	}
}



