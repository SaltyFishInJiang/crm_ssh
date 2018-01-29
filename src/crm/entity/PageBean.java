package crm.entity;

import java.util.List;

public class PageBean<T> {
	private int pc;// 当前页码,servlet负责
//	private int tp;// 总页数
	private int tr;// 总记录数，dao查询，service封装
	private int ps;// 每页记录数，service负责
	private List<T> beanList;// 页面数据，dao负责，使用limt子句,service封装
	private String url;//就是url后的条件，Servlet负责---/项目名/Servlet路径?参数字符串
	
	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getTp() {
		int tp = tr / ps;
		tp= (tr%ps == 0 ? tp : tp + 1);
		return tp;
	}

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
