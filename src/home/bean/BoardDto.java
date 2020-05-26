package home.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDto {
	private int no;
	private String head;
	private String subject;
	private String writer;
	private String content;
	private String reg;
	private int read;
	private String status;
	
	//추가 항목 정의
	private int reply;
	
	private int gno;
	private int parent;
	private int depth;
	
	private String userfile;
	private String serverfile;
	private long filesize;
	
	public void setResultSet(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setHead(rs.getString("head"));
		setSubject(rs.getString("subject"));
		setWriter(rs.getString("writer"));
		setContent(rs.getString("content"));
		setReg(rs.getString("reg"));
		setRead(rs.getInt("read"));
		setStatus(rs.getString("status"));
		setReply(rs.getInt("reply"));
		setGno(rs.getInt("gno"));
		setParent(rs.getInt("parent"));
		setDepth(rs.getInt("depth"));
		setUserfile(rs.getString("userfile"));
		setServerfile(rs.getString("serverfile"));
		setFilesize(rs.getLong("filesize"));
	}
	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", head=" + head + ", subject=" + subject + ", writer=" + writer + ", content="
				+ content + ", reg=" + reg + ", read=" + read + ", status=" + status + ", reply=" + reply + ", gno="
				+ gno + ", parent=" + parent + ", depth=" + depth + ", userfile=" + userfile + ", serverfile="
				+ serverfile + ", filesize=" + filesize + "]";
	}
	public String getUserfile() {
		return userfile;
	}
	public void setUserfile(String userfile) {
		this.userfile = userfile;
	}
	public String getServerfile() {
		return serverfile;
	}
	public void setServerfile(String serverfile) {
		this.serverfile = serverfile;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public int getReply() {
		return reply;
	}
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	//웹용 content 반환 : 띄어쓰기를 &nbsp;로, 엔터를 <br>로 변환하여 반환
	public String getWeb() {
		return content.replace("\\s", "&nbsp;").replace("\n", "<br>");
	}
	//일반 content 반환
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
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//시간 반환 getter
	public String getTime() {
		return reg.substring(11, 16);
	}
	//날짜 반환 getter
	public String getDate() {
		return reg.substring(0, 10);
	}
	//자동 반환 getter
	public String getAuto() {
//		Date d = new Date();
//		Format f = new SimpleDateFormat("yyyy-MM-dd");
//		String today = f.format(d);
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(today.equals(getDate()))
			return getTime();
		else
			return getDate();
	}
	
	public BoardDto() {
		super();
	}
	
	public boolean isBlind() {
		return !isNormal() && status.equals("블라인드");
	}
	public boolean isNotice() {
		return !isNormal() && status.equals("공지");
	}
	public boolean isNormal() {
		return status == null;
	}
}










