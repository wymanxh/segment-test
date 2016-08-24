package com.wyman.query.mongo.dto;

public class Activity {

	private String campaignId;
	private boolean sent;
	private boolean opened;
	private boolean clicked;

	public static Activity sent(String campaignId) {
		Activity ac = new Activity();
		ac.setCampaignId(campaignId);
		ac.setSent(true);
		return ac;
	}

	public static Activity opened(String campaignId) {
		Activity ac = new Activity();
		ac.setCampaignId(campaignId);
		ac.setSent(true);
		ac.setOpened(true);
		return ac;
	}

	public static Activity clicked(String campaignId) {
		Activity ac = new Activity();
		ac.setCampaignId(campaignId);
		ac.setSent(true);
		ac.setOpened(true);
		ac.setClicked(true);
		return ac;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campaignId == null) ? 0 : campaignId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (campaignId == null) {
			if (other.campaignId != null)
				return false;
		} else if (!campaignId.equals(other.campaignId))
			return false;
		return true;
	}
}
