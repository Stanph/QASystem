package com.j2ee.po;

public class StarAnswer {
	private int answerID;
	private String userID;
	private int starTime;
	private Answer answer;

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public int getAnswerID() {
		return answerID;
	}

	public void setAnswerID(int answerID) {
		this.answerID = answerID;
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

	public void setStarTime(int starTime) {
		this.starTime = starTime;
	}
}
