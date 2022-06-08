package com.kh.model.vo;

public class Cart {
	private int cartId; //CART_ID NUMBER PRIMARY KEY,
	private String userId; //USER_ID VARCHAR2(20),
	private int productId; //PRODUCT_ID NUMBER,
	private int amount; //AMOUNT NUMBER NOT NULL,
	private String orderYn; //ORDER_YN CHAR(1) DEFAULT 'N' NOT NULL
	private String productName;
	
	public Cart() {
		super();
	}

	public Cart(int cartId, String userId, int productId, int amount, String orderYn) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.orderYn = orderYn;
	}

	 public Cart(int cartId, String userId, int productId, int amount, String orderYn, String productName) {
	      super();
	      this.cartId = cartId;
	      this.userId = userId;
	      this.productId = productId;
	      this.amount = amount;
	      this.orderYn = orderYn;
	      this.productName = productName;
	   }

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getOrderYn() {
		return orderYn;
	}

	public void setOrderYn(String orderYn) {
		this.orderYn = orderYn;
	}
	
	public String getProductName() {
	    return productName;
	}

	public void setProductName(String productName) {
	    this.productName = productName;
	}



	 @Override
	   public String toString() {
	      if(productName == null) productName = "(판매 종료된 상품)";
	      
	      return "카트번호: " + cartId + ", 주문자: " + userId
	            + ", 주문메뉴: " + productName + ", 주문수량: " + amount +"개";
	   }
	
}
