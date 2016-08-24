package com.wyman.segment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

import com.wyman.query.mongo.dto.Activity;
import com.wyman.query.mongo.dto.Customer;
import com.wyman.query.mongo.dto.DateProperty;
import com.wyman.query.mongo.dto.TextProperty;

public class GroupTest {

	public static void main(String[] args) {
		System.out.println(Group.andGroup(TextField.equal("gender", "MALE")).getDepth());
		System.out.println(Group.andGroup(TextField.equal("gender", "MALE"), Group.andGroup(TextField.equal("gender", "MALE"))).getDepth());
	}

	@Test
	public void maxDepth() {
		try {
			Group.andGroup(TextField.equal("gender", "MALE"));
		} catch (Exception e) {
			fail();
		}
		try {
			Group.andGroup(TextField.equal("gender", "MALE"), Group.andGroup(TextField.equal("gender", "MALE")));
		} catch (Exception e) {
			fail();
		}
		try {
			Group.andGroup(Group.andGroup(Group.andGroup(TextField.equal("gender", "MALE"))));
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void match_text_and() {
		Customer customer = new Customer();
		TextProperty gender = new TextProperty("gender", "MALE");
		customer.addTextProperties(gender);
		assertTrue(Group.andGroup(TextField.equal("gender", "MALE")).match(customer));
		assertTrue(Group.andGroup(TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE")).match(customer));
		assertFalse(Group.andGroup(TextField.equal("gender", "FMALE"), TextField.equal("gender", "MALE")).match(customer));
	}

	@Test
	public void match_text_or() {
		Customer customer = new Customer();
		TextProperty gender = new TextProperty("gender", "MALE");
		customer.addTextProperties(gender);
		assertTrue(Group.orGroup(TextField.equal("gender", "MALE")).match(customer));
		assertTrue(Group.orGroup(TextField.equal("gender", "MALE"), TextField.equal("gender", "MALE")).match(customer));
		assertFalse(Group.orGroup(TextField.equal("gender", "FMALE"), TextField.equal("gender", "OTHER")).match(customer));
	}

	@Test
	public void match_date_and() {
		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Date yestoday = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Date tomorrow = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Customer customer = new Customer();
		DateProperty bd = new DateProperty("bd", today);
		customer.addDateProperties(bd);
		assertTrue(Group.andGroup(DateField.equals("bd", today)).match(customer));
		assertTrue(Group.andGroup(DateField.after("bd", yestoday)).match(customer));
		assertTrue(Group.andGroup(DateField.before("bd", tomorrow)).match(customer));
		assertTrue(Group.andGroup(DateField.before("bd", tomorrow), DateField.after("bd", yestoday)).match(customer));
		assertFalse(Group.andGroup(DateField.before("bd", tomorrow), DateField.before("bd", yestoday)).match(customer));
	}

	@Test
	public void match_date_or() {
		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Date yestoday = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Date tomorrow = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Customer customer = new Customer();
		DateProperty bd = new DateProperty("bd", today);
		customer.addDateProperties(bd);
		assertTrue(Group.orGroup(DateField.equals("bd", today)).match(customer));
		assertTrue(Group.orGroup(DateField.after("bd", yestoday)).match(customer));
		assertTrue(Group.orGroup(DateField.before("bd", tomorrow)).match(customer));
		assertTrue(Group.orGroup(DateField.before("bd", tomorrow), DateField.after("bd", yestoday)).match(customer));
		assertFalse(Group.orGroup(DateField.after("bd", tomorrow), DateField.before("bd", yestoday)).match(customer));
	}

	@Test
	public void match_behavior() {
		String camp1 = "camp1";
		String camp2 = "camp2";
		String camp3 = "camp3";
		String camp4 = "camp4";

		Customer customer = new Customer();
		customer.addActivity(Activity.sent(camp1));
		customer.addActivity(Activity.opened(camp2));
		customer.addActivity(Activity.clicked(camp3));

		assertTrue(Group.andGroup(BehaviorField.sent(camp1)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.sent(camp2)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.sent(camp3)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.opened(camp2)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.opened(camp3)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.notOpened(camp1)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.notClicked(camp1)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.clicked(camp3)).match(customer));

		assertTrue(Group.andGroup(BehaviorField.notSent(camp4)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.notClicked(camp4)).match(customer));
		assertTrue(Group.andGroup(BehaviorField.notOpened(camp4)).match(customer));
	}

	@Test
	public void match_mix() {
		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Date yestoday = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		Date tomorrow = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("GMT+8")).toInstant());
		TextProperty gender = new TextProperty("gender", "MALE");
		Customer customer = new Customer();
		DateProperty bd = new DateProperty("bd", today);
		customer.addDateProperties(bd);
		customer.addTextProperties(gender);
		assertTrue(Group.andGroup(Group.orGroup(DateField.before("bd", tomorrow), DateField.equals("bd", yestoday)), TextField.equal("gender", "MALE")).match(
				customer));
		assertTrue(Group.orGroup(Group.andGroup(DateField.before("bd", tomorrow), DateField.after("bd", yestoday)), TextField.equal("gender", "FMALE")).match(
				customer));
	}
}
