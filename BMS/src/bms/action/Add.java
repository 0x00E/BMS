package bms.action;

import com.opensymphony.xwork2.ActionSupport;

import bms.util.BMS;
import bms.util.JDBC;

public class Add extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String name;
	private String category;
	private String chubanshe;
	private String author;
	private String number;
	private String price;
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	private String date;
	private String image;
	private String ext;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getChubanshe() {
		return chubanshe;
	}

	public void setChubanshe(String chubanshe) {
		this.chubanshe = chubanshe;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String execute() throws Exception {
		if(BMS.checkGuestBool()){
			return ERROR;
		}
		JDBC.executeUpdate("INSERT INTO 图书管理表 (书名,出版社,类别,作者,数量,定价,日期,备注) VALUES (?,?,?,?,?,?,?,?)",
				name,
				chubanshe,
				category,
				author,
				number,
				price,
				date,
				ext	
		);
		return SUCCESS;
	}

}
