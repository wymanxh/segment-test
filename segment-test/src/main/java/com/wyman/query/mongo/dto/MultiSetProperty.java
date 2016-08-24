package com.wyman.query.mongo.dto;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class MultiSetProperty extends Property {

	private Set<String> values = ImmutableSet.of();

	public MultiSetProperty() {
	}

	public MultiSetProperty(String id, String... values) {
		super(id);
		this.values = ImmutableSet.copyOf(values);
	}

	public Set<String> getValues() {
		return ImmutableSet.copyOf(values);
	}

	public void setValues(Set<String> values) {
		this.values = ImmutableSet.copyOf(values);
	}

}