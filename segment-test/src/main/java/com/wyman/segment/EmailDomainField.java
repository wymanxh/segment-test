package com.wyman.segment;

import org.apache.commons.lang3.StringUtils;

import com.wyman.query.dto.Customer;

public class EmailDomainField extends Field<String> {

	private static final String KEY = "N/A";

	private EmailDomainField(String value, AssignmentOp op) {
		super(KEY, value, op);
	}

	public static EmailDomainField equal(String value) {
		return new EmailDomainField(value, AssignmentOp.Equals);
	}

	@Override
	protected boolean match(Customer customer) {
		String email = customer.getEmail();
		if (StringUtils.isNotBlank(email)) {
			String[] split = StringUtils.split(email, "@");
			if (split.length != 2) {
				return false;
			} else {
				return value.equalsIgnoreCase(split[1]);
			}
		}
		return false;
	}
}
