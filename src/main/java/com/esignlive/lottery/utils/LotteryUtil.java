package com.esignlive.lottery.utils;

import java.util.Set;

import com.esignlive.lottery.domain.Winner;
import com.esignlive.lottery.repositories.MoneyPot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LotteryUtil
{
	private static final Logger LOGGER = LoggerFactory.getLogger(LotteryUtil.class);
	/**
	 * prints the winners
	 *
	 * @param winners
	 */
	public static void logWinners(Set<Winner> winners)
	{
		int i = 1;
		for (Winner winner : winners)
		{
			LOGGER.info("-------------------------------------------------------");
			LOGGER.info("winner{} is: {}", i, winner.getBuyerName());
			LOGGER.info("ticket number: {}", winner.getTicket().getTicketNumber());
			LOGGER.info("prize is: {} $", winner.getTicket().isPurchased() ? winner.getPrize() : 0);
			LOGGER.info("-------------------------------------------------------");
			i++;
		}
	}

	/**
	 * Calculate the prize on half of the money pot
	 * 75 % for sequence 1
	 * 15 % for sequence 2
	 * 10 % for sequence 3
	 *
	 * @param i
	 * 		the winner sequence
	 * @return the prize amount
	 */
	public static long calculatePrize(int i)
	{
		double result = 0.0;
		double halfOfThePot = MoneyPot.getPot() / 2.0;
		if (i == 1)
			result = Math.floor(halfOfThePot * 0.75);
		if (i == 2)
			result = Math.floor(halfOfThePot * 0.15);
		if (i == 3)
			result = Math.floor(halfOfThePot * 0.10);
		return Math.round(result);
	}

	/**
	 * Decrease the pot amount by the prize(s) amount only if the ticket(s) is purchased
	 *
	 * @param winners
	 * 		the 3 winners
	 */
	public static void decreasePotAfterWin(final Set<Winner> winners)
	{
		for (Winner winner : winners)
			if (winner.getTicket().isPurchased())
				MoneyPot.decreaseMoneyPot((long) winner.getPrize());
	}
}
