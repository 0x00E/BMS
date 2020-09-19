package bms.action;

import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import bms.util.BMS;
import bms.util.JDBC;

public class Export extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String content;
	private String search;
	private Integer limit0;
	private Integer limit1;

	public String getSearch() {
		return search;
	}



	public void setSearch(String search) {
		this.search = search;
	}



	public Integer getLimit0() {
		return limit0;
	}



	public void setLimit0(Integer limit0) {
		this.limit0 = limit0;
	}



	public Integer getLimit1() {
		return limit1;
	}



	public void setLimit1(Integer limit1) {
		this.limit1 = limit1;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public InputStream getInputStream() {
		if(content.equals("all")){
			return BMS.objectExportTxt(JDBC.executeQueryC(("SELECT 编号,书名,出版社,类别,作者,数量,定价,日期,备注 FROM 图书管理表")));
		}else if(content.equals("search")){
			return BMS.objectExportTxt(JDBC.executeQueryC(("SELECT 编号,书名,出版社,类别,作者,数量,定价,日期,备注 FROM 图书管理表  WHERE 书名 LIKE ? OR 作者 LIKE ?"),"%"+search+"%","%"+search+"%"));
		}else{
			return BMS.objectExportTxt(JDBC.executeQueryC(("SELECT 编号,书名,出版社,类别,作者,数量,定价,日期,备注 FROM 图书管理表  WHERE 书名 LIKE ? OR 作者 LIKE ? LIMIT ?,?"),"%"+search+"%","%"+search+"%",limit0,limit1));
		}
		
	}



	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
