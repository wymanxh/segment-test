package com.wyman.query.dto;

import java.util.Date;

public class DateProperty extends Property {

	private Date date;

	public DateProperty() {
	}

	public DateProperty(String id, Date date) {
		super(id);
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
