package com.wyman.segment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

import com.wyman.query.mongo.dto.Customer;
import com.wyman.query.mongo.dto.DateProperty;
import com.wyman.query.mongo.dto.TextProperty;

public class MatcherTest {

	@Test
	public void match_field() {
		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Date yestoday = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.of("GMT+8")).toInstant());

		Customer cus1 = new Customer();
		cus1.addTextProperties(new TextProperty("gender", "MALE"));
		cus1.addDateProperties(new DateProperty("bd", today));

		Customer cus2 = new Customer();
		cus2.addTextProperties(new TextProperty("gender", "MALE"));
		cus2.addDateProperties(new DateProperty("bd", yestoday));

		assertTrue(new Matcher().include(TextField.equal("gender", "MALE")).match(cus1));
		assertFalse(new Matcher().exclude(TextField.equal("gender", "MALE")).match(cus1));
	}

	@Test
	public void match_group() {
		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Date yestoday = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.of("GMT+8")).toInstant());

		Customer cus1 = new Customer();
		cus1.addTextProperties(new TextProperty("gender", "MALE"));
		cus1.addDateProperties(new DateProperty("bd", today));

		Customer cus2 = new Customer();
		cus2.addTextProperties(new TextProperty("gender", "MALE"));
		cus2.addDateProperties(new DateProperty("bd", yestoday));

		assertTrue(new Matcher().include(Group.andGroup(TextField.equal("gender", "MALE"))).match(cus1));
		assertFalse(new Matcher().exclude(Group.andGroup(TextField.equal("gender", "MALE"))).match(cus1));

		assertFalse(new Matcher().include(Group.andGroup(TextField.equal("gender", "MALE"))).exclude(Group.andGroup(DateField.after("bd", yestoday)))
				.match(cus1));
		assertTrue(new Matcher().include(Group.andGroup(TextField.equal("gender", "MALE"))).exclude(Group.andGroup(DateField.after("bd", yestoday)))
				.match(cus2));

		assertTrue(new Matcher()
				.include(
						Group.andGroup(TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"),
								TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"),
								TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"),
								TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"),
								TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE"))).exclude(Group.andGroup(DateField.after("bd", yestoday)))
				.match(cus2));
	}
}
