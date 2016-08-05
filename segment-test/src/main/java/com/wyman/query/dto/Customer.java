package com.wyman.query.dto;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.ImmutableSet;

@Document(collection = "segment_customer")
public class Customer {

	// basic information
	@Id
	private String id;
	private String externalId;
	private String firstName;
	private String lastName;
	private String email;
	private String dbId;

	// customize information
	private Set<TextProperty> textProps = ImmutableSet.of();
	private Set<DateProperty> dateProps = ImmutableSet.of();
	private Set<NumberProperty> numberProps = ImmutableSet.of();
	private Set<SetProperty> setProps = ImmutableSet.of();
	private Set<MultiSetProperty> multisetProps = ImmutableSet.of();

	public void addTextProperties(TextProperty... props) {
		this.textProps = new ImmutableSet.Builder<TextProperty>().addAll(textProps).add(props).build();
	}

	public void addDateProperties(DateProperty... props) {
		this.dateProps = new ImmutableSet.Builder<DateProperty>().addAll(dateProps).add(props).build();
	}

	public void addNumberProperties(NumberProperty... props) {
		this.numberProps = new ImmutableSet.Builder<NumberProperty>().addAll(numberProps).add(props).build();
	}

	public void addSetProperties(SetProperty... props) {
		this.setProps = new ImmutableSet.Builder<SetProperty>().addAll(setProps).add(props).build();
	}

	public void addSetProperties(MultiSetProperty... props) {
		this.multisetProps = new ImmutableSet.Builder<MultiSetProperty>().addAll(multisetProps).add(props).build();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}

	public void setTextProps(Set<TextProperty> textProps) {
		this.textProps = ImmutableSet.copyOf(textProps);
	}

	public void setDateProps(Set<DateProperty> dateProps) {
		this.dateProps = ImmutableSet.copyOf(dateProps);
	}

	public void setNumberProps(Set<NumberProperty> numberProps) {
		this.numberProps = ImmutableSet.copyOf(numberProps);
	}

	public Set<SetProperty> getSetProps() {
		return ImmutableSet.copyOf(setProps);
	}

	public void setSetProps(Set<SetProperty> setProps) {
		this.setProps = ImmutableSet.copyOf(setProps);
	}

	public Set<MultiSetProperty> getMultisetProps() {
		return ImmutableSet.copyOf(multisetProps);
	}

	public void setMultisetProps(Set<MultiSetProperty> multisetProps) {
		this.multisetProps = ImmutableSet.copyOf(multisetProps);
	}

	public Set<TextProperty> getTextProps() {
		return ImmutableSet.copyOf(textProps);
	}

	public Set<DateProperty> getDateProps() {
		return ImmutableSet.copyOf(dateProps);
	}

	public Set<NumberProperty> getNumberProps() {
		return ImmutableSet.copyOf(numberProps);
	}
}
