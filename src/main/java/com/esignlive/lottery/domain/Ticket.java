package com.esignlive.lottery.domain;

public class Ticket
{
	private boolean purchased;
	private String nameOfBuyer;
	private long ticketNumber;

	public Ticket(final long ticketNumber, final String name, final boolean isPurchased)
	{
		this.purchased = isPurchased;
		this.ticketNumber = ticketNumber;
		this.nameOfBuyer = name;
	}

	public boolean isPurchased()
	{
		return purchased;
	}

	public void setPurchased(final boolean purchased)
	{
		this.purchased = purchased;
	}

	public String getNameOfBuyer()
	{
		return nameOfBuyer;
	}

	public void setNameOfBuyer(final String nameOfBuyer)
	{
		this.nameOfBuyer = nameOfBuyer;
	}

	public long getTicketNumber()
	{
		return ticketNumber;
	}

	public void setTicketNumber(final long ticketNumber)
	{
		this.ticketNumber = ticketNumber;
	}

	@Override
	public String toString()
	{
		return "Ticket{" +
				"purchased=" + purchased +
				", nameOfBuyer='" + nameOfBuyer + '\'' +
				", ticketNumber=" + ticketNumber +
				'}';
	}
}
