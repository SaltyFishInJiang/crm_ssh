package crm.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Integer uid;
	private String  username;
	private String  password;
	private String  address;
	//¶àÌõ°İ·Ã¼ÇÂ¼
	private Set<Visit> visits=new HashSet<Visit>();
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Visit> getVisits() {
		return visits;
	}
	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	
}
