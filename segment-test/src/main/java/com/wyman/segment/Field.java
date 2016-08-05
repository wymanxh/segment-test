package com.wyman.segment;

public abstract class Field<T extends Object> extends Criteria {

	protected AssignmentOp op;
	protected String key;
	protected T value;

	Field(String key, T value, AssignmentOp op) {
		super();
		this.op = op;
		this.key = key;
		this.value = value;
		this.depth = 0;
	}

	public enum AssignmentOp {
		Equals, GreaterThan, LessThan, Contains
	}
}
