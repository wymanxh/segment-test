package com.wyman.segment;

import java.math.BigDecimal;
import java.util.Set;

import com.wyman.query.mongo.dto.Customer;
import com.wyman.query.mongo.dto.NumberProperty;

public class NumberField extends Field<Number> {

	private NumberField(String key, Number value, AssignmentOp op) {
		super(key, value, op);
	}

	public static NumberField equals(String key, Number value) {
		return new NumberField(key, value, AssignmentOp.Equals);
	}

	public static NumberField lessThan(String key, Number value) {
		return new NumberField(key, value, AssignmentOp.LessThan);
	}

	public static NumberField greaterThan(String key, Number value) {
		return new NumberField(key, value, AssignmentOp.GreaterThan);
	}

	@Override
	protected boolean match(Customer customer) {
		Set<NumberProperty> props = customer.getNumberProps();
		if (!props.isEmpty()) {
			switch (op) {
			case Equals:
				return props.stream().anyMatch(
						p -> key.equals(p.getId()) && new BigDecimal(p.getValue().toString()).compareTo(new BigDecimal(value.toString())) == 0);
			case GreaterThan:
				return props.stream().anyMatch(
						p -> key.equals(p.getId()) && new BigDecimal(p.getValue().toString()).compareTo(new BigDecimal(value.toString())) == 1);

			case LessThan:
				return props.stream().anyMatch(
						p -> key.equals(p.getId()) && new BigDecimal(p.getValue().toString()).compareTo(new BigDecimal(value.toString())) == -1);
			default:
				return false;
			}
		}
		return false;
	}
}
