package com.wyman.query.mongo.dto;

public class NumberProperty extends Property {

	private Number value;

	public NumberProperty() {
	}

	public NumberProperty(String id, Number value) {
		super(id);
		this.value = value;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}
}