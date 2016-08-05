package com.wyman.segment;

import java.util.Date;
import java.util.Set;

import com.wyman.query.dto.Customer;
import com.wyman.query.dto.DateProperty;

public class DateField extends Field<Date> {

	private DateField(String key, Date value, AssignmentOp op) {
		super(key, value, op);
	}

	public static DateField equals(String key, Date value) {
		return new DateField(key, value, AssignmentOp.Equals);
	}

	public static DateField before(String key, Date value) {
		return new DateField(key, value, AssignmentOp.LessThan);
	}

	public static DateField after(String key, Date value) {
		return new DateField(key, value, AssignmentOp.GreaterThan);
	}

	@Override
	protected boolean match(Customer customer) {
		Set<DateProperty> props = customer.getDateProps();
		if (!props.isEmpty()) {
			switch (op) {
			case Equals:
				return props.stream().anyMatch(p -> key.equals(p.getId()) && value.equals(p.getValue()));
			case GreaterThan:
				return props.stream().anyMatch(p -> key.equals(p.getId()) && value.before(p.getValue()));
			case LessThan:
				return props.stream().anyMatch(p -> key.equals(p.getId()) && value.after(p.getValue()));
			default:
				return false;
			}
		}
		return false;
	}
}
