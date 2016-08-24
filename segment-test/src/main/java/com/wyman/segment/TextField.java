package com.wyman.segment;

import java.util.Set;

import com.wyman.query.mongo.dto.Customer;
import com.wyman.query.mongo.dto.TextProperty;

public class TextField extends Field<String> {

	private TextField(String key, String value, AssignmentOp op) {
		super(key, value, op);
	}

	public static TextField equal(String key, String value) {
		return new TextField(key, value, AssignmentOp.Equals);
	}

	@Override
	protected boolean match(Customer customer) {
		Set<TextProperty> props = customer.getTextProps();
		if (props.isEmpty()) {
			return false;
		} else {
			return props.stream().anyMatch(p -> key.equals(p.getId()) && value.equals(p.getValue()));
		}
	}
}
