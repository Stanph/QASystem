package com.j2ee.po;


import java.text.SimpleDateFormat;

/**
 * @author haopan
 */
public class Answer {
	private int answerID;
	private int questionID;
	private String userID;
	private String userName;
	private int createTime;
	private String showTime;
	private String answer;
	private int star;
	private int starOrNot;

	public int getStarOrNot() {
		return starOrNot;
	}

	public void setStarOrNot(int starOrNot) {
		this.starOrNot = starOrNot;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public int getAnswerID() {
		return answerID;
	}

	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}
}
