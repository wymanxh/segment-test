package com.wyman.query.mongo.dto;

import java.util.Date;

public class DateProperty extends Property {

	private Date value;

	public DateProperty() {
	}

	public DateProperty(String id, Date value) {
		super(id);
		this.value = value;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}
}
