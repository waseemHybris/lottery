package com.esignlive.lottery.models;

import com.esignlive.lottery.repositories.MoneyPot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MoneyPotTest
{
	@Before
	public void init()
	{
		MoneyPot.reset();
	}

	@Test
	public void increaseMoneyPot()
	{
		MoneyPot.increaseMoneyPot(10);
		Assert.assertTrue(MoneyPot.getPot() == 210);
	}

	@Test
	public void decreaseMoneyPot()
	{
		MoneyPot.decreaseMoneyPot(10);
		Assert.assertTrue(MoneyPot.getPot() == 190);
	}

	@Test
	public void getPot()
	{
		Assert.assertTrue(MoneyPot.getPot() == 200);
	}
}
