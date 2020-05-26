package home.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyDto {
	private int no;
	private String writer;
	private String content;
	private String reg;
	private int origin;
	public void setResultSet(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setWriter(rs.getString("writer"));
		setContent(rs.getString("content"));
		setReg(rs.getString("reg"));
		setOrigin(rs.getInt("origin"));
	}
	@Override
	public String toString() {
		return "ReplyDto [no=" + no + ", writer=" + writer + ", content=" + content + ", reg=" + reg + ", origin="
				+ origin + "]";
	}
	public ReplyDto() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
}
