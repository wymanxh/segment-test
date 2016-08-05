package com.wyman.segment;

import com.wyman.query.dto.Customer;

public class Matcher {

	private Group include = new EmptyGroup(true);
	private Group exclude = new EmptyGroup(false);

	public Matcher() {
	}

	public Matcher include(Group include) {
		this.include = include;
		return this;
	}

	public Matcher exclude(Group exclude) {
		this.exclude = exclude;
		return this;
	}

	public boolean match(Customer customer) {
		return include.match(customer) && !exclude.match(customer);
	}
}
