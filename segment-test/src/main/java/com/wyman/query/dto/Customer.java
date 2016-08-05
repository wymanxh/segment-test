package com.wyman.query.dto;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class Customer {

	private Set<TextProperty> textProps = ImmutableSet.of();
	private Set<DateProperty> dateProps = ImmutableSet.of();

	public void addTextProperties(TextProperty... props) {
		this.textProps = new ImmutableSet.Builder<TextProperty>().addAll(textProps).add(props).build();
	}

	public Set<TextProperty> getTextProps() {
		return ImmutableSet.copyOf(textProps);
	}

	public void addDateProperties(DateProperty... props) {
		this.dateProps = new ImmutableSet.Builder<DateProperty>().addAll(dateProps).add(props).build();
	}

	public Set<DateProperty> getDateProps() {
		return ImmutableSet.copyOf(dateProps);
	}

}
