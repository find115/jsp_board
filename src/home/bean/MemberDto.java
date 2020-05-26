package home.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberDto {
	private String id, pw, nick, birth, tel, email, post, addr1, addr2, reg, authority;

	public MemberDto() {
		super();
	}
	
	public MemberDto(String id, String pw, String nick, String birth, String tel, String email, String post,
			String addr1, String addr2) {
		super();
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.birth = birth;
		this.tel = tel;
		this.email = email;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}



	public void setResultSet(ResultSet rs) throws SQLException {
		setId(rs.getString("id"));
		setPw(rs.getString("pw"));
		setNick(rs.getString("nick"));
		setBirth(rs.getString("birth"));
		setTel(rs.getString("tel"));
		setEmail(rs.getString("email"));
		setPost(rs.getString("post"));
		setAddr1(rs.getString("addr1"));
		setAddr2(rs.getString("addr2"));
		setReg(rs.getString("reg"));
		setAuthority(rs.getString("authority"));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 이 메소드는 null이 나갈 수 있다... 안나가도록 수정		null ---> ""
	 * @return
	 */
	public String getEmail() {
//		if(email != null)
//			return email;
//		else
//			return "";
		return email==null?"":email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPost() {
		return post==null?"":post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddr1() {
		return addr1==null?"":addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2==null?"":addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	/**
	 * 날짜를 사용자가 보기 좋게 편집하여 반환하는 기능
	 * @return 보기좋게 편집된 날짜 객체
	 * @throws ParseException 못바꾸면 발생
	 */
	public String getDate() throws ParseException {
//		return reg.substring(0, 10);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date d = f.parse(reg);
		SimpleDateFormat f2 = new SimpleDateFormat("y년 M월 d일");
		return f2.format(d);
	}
	
	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pw=" + pw + ", nick=" + nick + ", birth=" + birth + ", tel=" + tel
				+ ", email=" + email + ", post=" + post + ", addr1=" + addr1 + ", addr2=" + addr2 + ", reg=" + reg
				+ ", authority=" + authority + "]";
	}
	
}
