package com.wyman.segment;

import com.wyman.query.dto.Customer;

public class EmptyGroup extends Group {

	private boolean match;

	public EmptyGroup(boolean match) {
		this.match = match;
	}

	@Override
	protected boolean match(Customer customer) {
		return match;
	}
}
