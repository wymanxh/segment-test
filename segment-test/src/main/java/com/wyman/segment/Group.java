package com.wyman.segment;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.wyman.query.dto.Customer;

public class Group extends Criteria {

	private LogicalOp op;
	private List<Criteria> criterias = ImmutableList.of();

	Group() {
	}

	private static int getDepth(Criteria... criterias) {
		int maxDepth = Arrays.asList(criterias).stream().mapToInt(c -> c.getDepth()).max().getAsInt();
		if (maxDepth >= MAX_DEPTH) {
			throw new IllegalArgumentException(String.format("Reach maximum depth %s", MAX_DEPTH));
		} else {
			return maxDepth;
		}
	}

	public static Group andGroup(Criteria... criterias) {
		Group group = new Group();
		group.op = LogicalOp.AND;
		group.criterias = ImmutableList.copyOf(criterias);
		group.depth = getDepth(criterias) + 1;
		return group;
	}

	public static Group orGroup(Criteria... criterias) {
		Group group = new Group();
		group.op = LogicalOp.OR;
		group.criterias = ImmutableList.copyOf(criterias);
		group.depth = getDepth(criterias) + 1;
		return group;
	}

	@Override
	protected boolean match(Customer customer) {
		if (!criterias.isEmpty()) {
			switch (op) {
			case AND:
				for (Criteria c : criterias) {
					if (!c.match(customer)) {
						return false;
					} else {
						continue;
					}
				}
			case OR:
				for (Criteria c : criterias) {
					if (c.match(customer)) {
						return true;
					} else {
						continue;
					}
				}
			}
		}
		return false;
	}

	public enum LogicalOp {
		AND, OR;
	}

}
