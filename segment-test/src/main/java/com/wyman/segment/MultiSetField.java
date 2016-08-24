package com.wyman.segment;

import java.util.Set;

import com.google.common.collect.Sets;
import com.wyman.query.mongo.dto.Customer;
import com.wyman.query.mongo.dto.MultiSetProperty;

public class MultiSetField extends Field<Set<String>> {

	private MultiSetField(String key, Set<String> values, AssignmentOp op) {
		super(key, values, op);
	}

	public static MultiSetField contains(String key, Set<String> values) {
		return new MultiSetField(key, values, AssignmentOp.Contains);
	}

	@Override
	protected boolean match(Customer customer) {
		Set<MultiSetProperty> props = customer.getMultisetProps();
		if (props.isEmpty()) {
			return false;
		} else {
			return props.stream().anyMatch(p -> key.equals(p.getId()) && Sets.intersection(value, p.getValues()).size() > 0);
		}
	}
}
