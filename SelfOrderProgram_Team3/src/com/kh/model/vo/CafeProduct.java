package com.kh.model.vo;

public class CafeProduct {
	private int productId; //PRODUCT_ID NUMBER PRIMARY KEY,
	private String productName; //PRODUCT_NAME VARCHAR2(30) NOT NULL,
	private String detail; //DETAIL VARCHAR2(100),
	private int price; //PRICE NUMBER NOT NULL,
	private String tempType; //TEMPTYPE CHAR(1)
	
	public CafeProduct() {
		super();
	}
	
	public CafeProduct(String productName, String detail, int price, String tempType) {
		super();
		this.productName = productName;
		this.detail = detail;
		this.price = price;
		this.tempType = tempType;
	}

	public CafeProduct(int productId, String productName, String detail, int price, String tempType) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.detail = detail;
		this.price = price;
		this.tempType = tempType;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTempType() {
		return tempType;
	}
	public void setTempType(String tempType) {
		this.tempType = tempType;
	}
	
	@Override
	public String toString() {
		String tempTypeDetail ="";
		if(tempType.equals("C")) tempTypeDetail = "(COLD)";
		else if(tempType.equals("H")) tempTypeDetail = "(HOT)";
		
		return "상품번호: " + productId + ", 상품명: " + productName + tempTypeDetail + ", 가격: " + price;
	}
	
}
