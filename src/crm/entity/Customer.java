package crm.entity;

import java.util.HashSet;
import java.util.Set;

import crm.entity.LinkMan;

public class Customer {

	private Integer cid;// 客户id
	private String custName;// 客户姓名
	private DictCustLevel dictCustLevel;// 客户级别
	private DictCustSource dictCustSource; // 客户来源
	private String custPhone; // 客户电话
	private String custMobile;// 客户手机
	//多个联系人
	private Set<LinkMan> linkMans=new HashSet<LinkMan>();
	//多条拜访记录
	private Set<Visit> visits=new HashSet<Visit>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
	public Set<Visit> getVisits() {
		return visits;
	}
	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	public DictCustLevel getDictCustLevel() {
		return dictCustLevel;
	}
	public void setDictCustLevel(DictCustLevel dictCustLevel) {
		this.dictCustLevel = dictCustLevel;
	}
	public DictCustSource getDictCustSource() {
		return dictCustSource;
	}
	public void setDictCustSource(DictCustSource dictCustSource) {
		this.dictCustSource = dictCustSource;
	}
}
