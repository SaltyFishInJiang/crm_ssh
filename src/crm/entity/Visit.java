package crm.entity;

import java.util.Date;

public class Visit {
	private Integer vid;//拜访记录id
	private Date visitDate;//拜访时间
	private String visitAddress;//拜访地址
	private	String visitContent;//拜访内容
	private User user;//由哪位用户拜访
	private Customer customer;//拜访哪位客户
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getVisitAddress() {
		return visitAddress;
	}
	public void setVisitAddress(String visitAddress) {
		this.visitAddress = visitAddress;
	}
	public String getVisitContent() {
		return visitContent;
	}
	public void setVisitContent(String visitContent) {
		this.visitContent = visitContent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	
}
