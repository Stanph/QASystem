package com.j2ee.po;

public class StarQuestion {
	private int questionID;
	private String userID;
	private int starTime;
	private Question question;
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
	public int getStarTime() {
		return starTime;
	}
	public void setStarTime(int startTime) {
		this.starTime = startTime;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
