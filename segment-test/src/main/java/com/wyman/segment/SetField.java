package com.wyman.segment;

import java.util.Set;

import com.wyman.query.dto.Customer;
import com.wyman.query.dto.SetProperty;

public class SetField extends Field<String> {

	private SetField(String key, String value, AssignmentOp op) {
		super(key, value, op);
	}

	public static SetField equal(String key, String value) {
		return new SetField(key, value, AssignmentOp.Equals);
	}

	@Override
	protected boolean match(Customer customer) {
		Set<SetProperty> props = customer.getSetProps();
		if (props.isEmpty()) {
			return false;
		} else {
			return props.stream().anyMatch(p -> key.equals(p.getId()) && value.equals(p.getValue()));
		}
	}
}
