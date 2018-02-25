package com.esignlive.lottery.domain;


public class Buyer
{
	private String buyerName;
	private Ticket ticket;

	public Buyer(final String buyerName, final Ticket ticket)
	{
		this.buyerName = buyerName;
		this.ticket = ticket;
	}

	public String getBuyerName()
	{
		return buyerName == null ? "NO WINNER" : buyerName;
	}

	public void setBuyerName(final String buyerName)
	{
		this.buyerName = buyerName;
	}

	public Ticket getTicket()
	{
		return ticket;
	}

	public void setTicket(final Ticket ticket)
	{
		this.ticket = ticket;
	}

	@Override
	public String toString()
	{
		return "Buyer{" +
				"buyerName='" + buyerName + '\'' +
				", ticket=" + ticket +
				'}';
	}
}
