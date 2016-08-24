package com.wyman.segment;

import com.wyman.query.mongo.dto.Customer;

public abstract class Criteria {

	protected static final int MAX_DEPTH = 2;

	protected int depth;

	abstract boolean match(Customer customer);

	public int getDepth() {
		return depth;
	}
}
