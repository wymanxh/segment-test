package com.wyman.segment;

import org.apache.commons.lang3.StringUtils;

import com.wyman.query.mongo.dto.Customer;

public class BehaviorField extends Criteria {

	private Behavior behavior;
	private String campaignId;

	public static BehaviorField sent(String campaignid) {
		return new BehaviorField(campaignid, Behavior.SENT);
	}

	public static BehaviorField notSent(String campaignid) {
		return new BehaviorField(campaignid, Behavior.NOTSENT);
	}

	public static BehaviorField opened(String campaignid) {
		return new BehaviorField(campaignid, Behavior.OPENED);
	}

	public static BehaviorField notOpened(String campaignid) {
		return new BehaviorField(campaignid, Behavior.NOTOPEN);
	}

	public static BehaviorField clicked(String campaignid) {
		return new BehaviorField(campaignid, Behavior.CLICKED);
	}

	public static BehaviorField notClicked(String campaignid) {
		return new BehaviorField(campaignid, Behavior.NOTCLICKED);
	}

	private BehaviorField(String campaignId, Behavior behavior) {
		this.campaignId = campaignId;
		this.behavior = behavior;
	}

	@Override
	boolean match(Customer customer) {
		switch (behavior) {
		case CLICKED:
			return customer.getActivities().stream().anyMatch(a -> StringUtils.equals(campaignId, a.getCampaignId()) && a.isClicked());
		case NOTCLICKED:
			return customer.getActivities().stream().allMatch(a -> !StringUtils.equals(campaignId, a.getCampaignId()))
					|| customer.getActivities().stream().anyMatch(a -> StringUtils.equals(campaignId, a.getCampaignId()) && !a.isClicked());
		case NOTOPEN:
			return customer.getActivities().stream().allMatch(a -> !StringUtils.equals(campaignId, a.getCampaignId()))
					|| customer.getActivities().stream().anyMatch(a -> StringUtils.equals(campaignId, a.getCampaignId()) && !a.isOpened());
		case NOTSENT:
			return customer.getActivities().stream().allMatch(a -> !StringUtils.equals(campaignId, a.getCampaignId()));
		case OPENED:
			return customer.getActivities().stream().anyMatch(a -> StringUtils.equals(campaignId, a.getCampaignId()) && a.isOpened());
		case SENT:
			return customer.getActivities().stream().anyMatch(a -> StringUtils.equals(campaignId, a.getCampaignId()));
		default:
			return false;
		}
	}

	public enum Behavior {
		SENT, NOTSENT, OPENED, CLICKED, NOTOPEN, NOTCLICKED
	}

}
