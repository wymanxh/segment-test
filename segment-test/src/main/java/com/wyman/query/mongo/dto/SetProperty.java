package com.wyman.query.mongo.dto;

public class SetProperty extends Property {

	private String value;

	public SetProperty() {
	}

	public SetProperty(String id, String value) {
		super(id);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}