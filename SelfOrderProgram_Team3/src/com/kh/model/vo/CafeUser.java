package com.kh.model.vo;

import java.sql.Date;

public class CafeUser {
    private String userId; //USER_ID VARCHAR2(20) PRIMARY KEY,
    private String userPwd; //USER_PWD VARCHAR2(20) NOT NULL,
    private String userName; //USER_NAME VARCHAR2(20) NOT NULL,
    private String phone; //PHONE CHAR(11) NOT NULL,
    private Date joinDate; //JOIN_DATE DATE DEFAULT SYSDATE NOT NULL,
    private String userType; //USER_TYPE CHAR(1) DEFAULT 'C' NOT NULL, (S는 직원, C는 손님)
    private int wallet; //WALLET NUMBER DEFAULT 0 NOT NULL
    
	public CafeUser() {
		super();
	}

	public CafeUser(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	public CafeUser(String userId, String userPwd, String userName, String phone, Date joinDate, String userType,
			int wallet) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.joinDate = joinDate;
		this.userType = userType;
		this.wallet = wallet;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getWallet() {
		return wallet;
	}
	public void setWallet(int wallet) {
		this.wallet = wallet;
	}
	
	@Override
	public String toString() {
		return "CafeUser [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", phone=" + phone
				+ ", joinDate=" + joinDate + ", userType=" + userType + ", wallet=" + wallet + "]";
	}

    
}
