package com.esignlive.lottery.repositories;

import java.util.HashSet;
import java.util.Set;

import com.esignlive.lottery.domain.Ticket;
import org.springframework.stereotype.Repository;


@Repository
public class TicketsDAO
{
	private Set<Ticket> tickets;

	TicketsDAO()
	{
		generateTickets();
	}

	public void generateTickets()
	{
		tickets = new HashSet<>();
		for (int i = 1; i < 51; i++)
		{
			Ticket ticket = new Ticket((long) i, null, false);
			tickets.add(ticket);
		}
	}

	public Set<Ticket> getTickets()
	{
		return tickets;
	}
}
