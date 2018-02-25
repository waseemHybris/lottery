package com.esignlive.lottery.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class MoneyPot
{
	private static final Logger LOGGER = LoggerFactory.getLogger(MoneyPot.class);
	private static MoneyPot ourInstance;
	private static long pot = 200;

	private MoneyPot()
	{
	}

	public static synchronized MoneyPot getInstance()
	{
		if (ourInstance == null)
		{
			ourInstance = new MoneyPot();
		}
		return ourInstance;
	}

	public static void increaseMoneyPot(final long increaseAmount)
	{
		pot += increaseAmount;
		LOGGER.debug("MoneyPot was increased by {}", increaseAmount);
	}

	public static void decreaseMoneyPot(final long decreaseAmount)
	{
		pot -= decreaseAmount;
		LOGGER.debug("MoneyPot was decreased by {}", decreaseAmount);
	}

	public static long getPot()
	{
		return pot;
	}

	public static void reset()
	{
		pot = 200;
	}
}
