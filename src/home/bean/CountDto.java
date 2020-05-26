package home.bean;

import java.io.Serializable;

public class CountDto implements Serializable{
	private String day;
	private int request;
	private int people;
	@Override
	public String toString() {
		return "CountDto [day=" + day + ", request=" + request + ", people=" + people + "]";
	}
	public CountDto() {
		super();
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getRequest() {
		return request;
	}
	public void setRequest(int request) {
		this.request = request;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public void increaseRequest() {
		this.request++;
	}
	public void increasePeople() {
		this.people++;
	}
	public void reset(String today) {
		setDay(today);
		setRequest(0);
		setPeople(0);
	}
}
