package com.wyman.query.mongo.dto;

public class TextProperty extends Property {

	private String value;

	public TextProperty() {
	}

	public TextProperty(String id, String value) {
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