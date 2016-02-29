package com.defense.entity;

import java.util.Date;

public class ReminderExcelEntity {

	private int sNo;
	private Date recievedOn;
	private String letterNo;
	private String subject;
	private String from;
	private String section;
	private Date dateOfAction;

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public Date getRecievedOn() {
		return recievedOn;
	}

	public void setRecievedOn(Date recievedOn) {
		this.recievedOn = recievedOn;
	}

	public String getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Date getDateOfAction() {
		return dateOfAction;
	}

	public void setDateOfAction(Date dateOfAction) {
		this.dateOfAction = dateOfAction;
	}
	
	@Override
	public String toString() {
		return "S.No. : " + this.getsNo() + "S.No. : " + this.getsNo() + "Recieved On : " + this.getRecievedOn()
				+ "Letter No. : " + this.getLetterNo() + "Subject : " + this.getSubject() + "From : " + this.getFrom()
				+ "Section : " + this.getSection() + "Date Of Action : " + this.getDateOfAction();
	}
}
