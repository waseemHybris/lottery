package com.esignlive.lottery.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.esignlive.lottery.domain.Buyer;
import com.esignlive.lottery.domain.Ticket;
import com.esignlive.lottery.domain.Winner;
import com.esignlive.lottery.exceptions.OverBuyingException;
import com.esignlive.lottery.repositories.MoneyPot;
import com.esignlive.lottery.repositories.TicketsDAO;
import com.esignlive.lottery.utils.LotteryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LotteryService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(LotteryService.class);
	@Autowired
	TicketsDAO tickets;

	Set<Winner> currentWinners;


	public Buyer purchaseTicket(final String buyerName)
	{
		Optional<Ticket> ticket = tickets.getTickets().stream().filter(t -> !t.isPurchased()).findAny();
		if (ticket.isPresent())
		{
			ticket.get().setPurchased(true);
			ticket.get().setNameOfBuyer(buyerName);
			MoneyPot.increaseMoneyPot(10);
			Buyer buyer = new Buyer(buyerName, ticket.get());
			LOGGER.info("{} bought a ticket number {}", buyerName, ticket.get().getTicketNumber());
			return buyer;
		}
		else
		{
			LOGGER.warn("No more tickets to sell");
			throw new OverBuyingException("We have just finished selling our last ticket! Try to be faster next time :)");
		}

	}

	public Set<Winner> generousDraw()
	{
		LOGGER.info("The Lottery Game is drawing for winners");
		Ticket winningTicket1 = tickets.getTickets().stream().findAny().get();
		Ticket winningTicket2 = tickets.getTickets().stream().filter(t -> t.getTicketNumber() != winningTicket1.getTicketNumber())
				.findAny()
				.get();
		Ticket winningTicket3 = tickets.getTickets().stream()
				.filter(t -> t.getTicketNumber() != winningTicket1.getTicketNumber() && t.getTicketNumber() != winningTicket2
						.getTicketNumber()).findAny()
				.get();

		Winner firstWinner = new Winner(winningTicket1.getNameOfBuyer(), winningTicket1,
				winningTicket1.isPurchased() ? LotteryUtil.calculatePrize(1) : 0, Winner.Place.FIRST);
		Winner secondWinner = new Winner(winningTicket2.getNameOfBuyer(), winningTicket2,
				winningTicket2.isPurchased() ? LotteryUtil.calculatePrize(2) : 0, Winner.Place.SECOND);
		Winner thirdWinner = new Winner(winningTicket3.getNameOfBuyer(), winningTicket3,
				winningTicket3.isPurchased() ? LotteryUtil.calculatePrize(3) : 0, Winner.Place.THIRD);

		LinkedHashSet result = new LinkedHashSet<>(Arrays.asList(firstWinner, secondWinner, thirdWinner));
		populateCurrentWinners(result);
		LotteryUtil.decreasePotAfterWin(result);
		LotteryUtil.logWinners(result);
		LOGGER.info("Draw is complete");
		LOGGER.info("The Lottery Game is resetting the Tickets");
		tickets.getTickets().clear();
		tickets.generateTickets();

		return result;
	}

	protected void populateCurrentWinners(final LinkedHashSet currentWinners)
	{
		this.currentWinners = new LinkedHashSet<>();
		this.currentWinners.addAll(currentWinners);

	}

	public Set<Winner> randomDraw()
	{
		LOGGER.info("The Lottery Game is drawing for winners");
		List<Integer> randomList = new ArrayList();
		ThreadLocalRandom.current().ints(1, 50).distinct().limit(3).forEach(randomList::add);
		Ticket winningTicket1 = (Ticket) tickets.getTickets().toArray()[randomList.get(0)];
		Ticket winningTicket2 = (Ticket) tickets.getTickets().toArray()[randomList.get(1)];
		Ticket winningTicket3 = (Ticket) tickets.getTickets().toArray()[randomList.get(2)];

		Winner firstWinner = new Winner(winningTicket1.getNameOfBuyer(), winningTicket1,
				winningTicket1.isPurchased() ? LotteryUtil.calculatePrize(1) : 0, Winner.Place.FIRST);
		Winner secondWinner = new Winner(winningTicket2.getNameOfBuyer(), winningTicket2,
				winningTicket2.isPurchased() ? LotteryUtil.calculatePrize(2) : 0, Winner.Place.SECOND);
		Winner thirdWinner = new Winner(winningTicket3.getNameOfBuyer(), winningTicket3,
				winningTicket3.isPurchased() ? LotteryUtil.calculatePrize(3) : 0, Winner.Place.THIRD);

		LinkedHashSet result = new LinkedHashSet<>(Arrays.asList(firstWinner, secondWinner, thirdWinner));
		populateCurrentWinners(result);
		LotteryUtil.decreasePotAfterWin(result);
		LotteryUtil.logWinners(result);
		LOGGER.info("Draw is complete");
		LOGGER.info("The Lottery Game is resetting the Tickets");
		tickets.getTickets().clear();
		tickets.generateTickets();

		return result;
	}

	public void resetProcess()
	{
		tickets.getTickets().clear();
		tickets.generateTickets();
		currentWinners.clear();
		MoneyPot.reset();
		LOGGER.info("The Lottery Game is reset");
	}

	public Set<Ticket> getAllTickets()
	{
		return tickets.getTickets();
	}


	public Set<Ticket> getPurchasedTickets()
	{
		return tickets.getTickets().stream().filter(Ticket::isPurchased).collect(Collectors.toSet());
	}

	public Set<Winner> getCurrentWinners()
	{
		Set<Winner> result = this.currentWinners;
		if (result != null && !result.isEmpty())
		{
			LOGGER.info("Winners from the latest draw were: ");
			LotteryUtil.logWinners(this.currentWinners);
		}
		else
		{
			LOGGER.info("There are no winners before a draw");
		}

		return result;
	}
}

