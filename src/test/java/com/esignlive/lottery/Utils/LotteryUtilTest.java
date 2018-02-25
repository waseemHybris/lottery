package com.esignlive.lottery.Utils;

import java.util.HashSet;

import com.esignlive.lottery.domain.Ticket;
import com.esignlive.lottery.domain.Winner;
import com.esignlive.lottery.models.MoneyPot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LotteryUtilTest
{
	@InjectMocks
	LotteryUtil utilUnderTest;
	@Spy
	MoneyPot moneyPot;


	@Test
	public void decreasePotAfterWinTest()
	{
	}

	@Test
	public void logWinnersTest(){

	}
	@Test
	public void calculatePrizeTest()
	{
		Assert.assertTrue(utilUnderTest.calculatePrize(1) == 75);
		Assert.assertTrue(utilUnderTest.calculatePrize(2) == 15);
		Assert.assertTrue(utilUnderTest.calculatePrize(3) == 10);
	}



	@Test
	public void calculatePrizeZeroTest()
	{
		Assert.assertTrue(utilUnderTest.calculatePrize(0) == 0);
	}
}
