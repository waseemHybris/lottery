package com.esignlive.lottery.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LotteryUtilTest
{
	@InjectMocks
	LotteryUtil utilUnderTest = new LotteryUtil();

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
