package com.wyman.segment;

import com.wyman.query.mongo.dto.Customer;

public class Matcher {

	private Criteria include = new EmptyGroup(true);
	private Criteria exclude = new EmptyGroup(false);

	public Matcher() {
	}

	public Matcher include(Criteria include) {
		this.include = include;
		return this;
	}

	public Matcher exclude(Criteria exclude) {
		this.exclude = exclude;
		return this;
	}

	public boolean match(Customer customer) {
		return include.match(customer) && !exclude.match(customer);
	}
}
