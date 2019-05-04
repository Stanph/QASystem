package com.j2ee.po;

public class User {
	private String userID;
	private int userType;
	private String name;
	private String pwd;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString(){
		return "User [userID="+userID+",pwd="+pwd+"]";
	}
}
