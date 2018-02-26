package com.esignlive.lottery.domain;

/**
 * winner is a buyer with a prize and a place
 */

public class Winner extends Buyer
{
	private long prize;
	private Place place;

	public Winner(final String buyerName, final Ticket ticket, final long prize, final Place place)
	{
		super(buyerName, ticket);
		this.prize = prize;
		this.place = place;
	}

	public double getPrize()
	{
		return prize;
	}

	public void setPrize(final long prize)
	{
		this.prize = prize;
	}

	public Place getPlace()
	{
		return place;
	}

	public void setPlace(Place place)
	{
		this.place = place;
	}

	@Override
	public String toString()
	{
		return "Winner{" +
				"prize=" + prize + "$"+
				", place=" + place +
				'}';
	}

	public enum Place
	{
		FIRST, SECOND, THIRD
	}
}
