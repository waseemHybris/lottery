package com.esignlive.lottery.services;

import javax.annotation.Resource;

import java.util.Set;
import java.util.function.Predicate;

import com.esignlive.lottery.LotteryApplication;
import com.esignlive.lottery.domain.Ticket;
import com.esignlive.lottery.domain.Winner;
import com.esignlive.lottery.exceptions.OverBuyingException;
import com.esignlive.lottery.repositories.MoneyPot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LotteryApplication.class)
public class LotteryServiceIntegrationTest
{
	public static final String BUYERNAME = "Waseem";
	@Resource
	private LotteryService serviceUnderTest;

	@After
	public void setUp()
	{
		serviceUnderTest.resetProcess();
	}

	@Test
	public void drawWithNoWinnersTest()
	{
		Set<Winner> winners = serviceUnderTest.randomDraw();
		Assert.assertEquals(3, winners.size());
	}

	@Test
	public void drawWithManyPurchasesTest()
	{
		for (int i = 0; i < 30; i++)
		{
			serviceUnderTest.purchaseTicket(String.valueOf(i));
		}
		// The Money Pot should be 500 after buying 30 tickets
		Assert.assertTrue(MoneyPot.getPot() == 200 + 10 * 30);
		Set<Winner> winners = serviceUnderTest.randomDraw();
		Assert.assertEquals(3, winners.size());
		//Assure that Money pot is still more or equal than half of it before randomDraw
		// (500 / 2 =  250  )
		Assert.assertTrue(MoneyPot.getPot() >= 250);
	}

	@Test
	public void winnersTest()
	{
		for (int i = 0; i < 50; i++)
		{
			serviceUnderTest.purchaseTicket(String.valueOf(i));
		}
		Assert.assertTrue(MoneyPot.getPot() == 200 + 10 * 50);
		Set<Winner> winners = serviceUnderTest.randomDraw();
		Set<Winner> currentWinners = serviceUnderTest.getCurrentWinners();
		Assert.assertEquals(3, winners.size());
		Assert.assertTrue(currentWinners.size()>1);


	}

	@Test
	public void drawWithSoldOutTickets()
	{
		for (int i = 0; i < 50; i++)
		{
			serviceUnderTest.purchaseTicket(String.valueOf(i));
		}
		// The Money Pot should be 700 after buying 50 tickets
		Assert.assertTrue(MoneyPot.getPot() == 200 + 10 * 50);
		Set<Winner> winners = serviceUnderTest.randomDraw();
		Assert.assertEquals(3, winners.size());
		Assert.assertTrue(((Winner)winners.toArray()[0]).getTicket().isPurchased());
		Assert.assertTrue(((Winner)winners.toArray()[1]).getTicket().isPurchased());
		Assert.assertTrue(((Winner)winners.toArray()[2]).getTicket().isPurchased());
		//Assure that Money pot is still more or equal than half of it before randomDraw
		// (700 / 2 =  350  )
		Assert.assertTrue(MoneyPot.getPot() >= 350);
	}

	@Test
	public void buyATicketTest()
	{
		Long before = MoneyPot.getPot();
		serviceUnderTest.purchaseTicket(BUYERNAME);
		Long after = MoneyPot.getPot();
		Assert.assertTrue(after == (before + 10));
		Assert.assertTrue(
				serviceUnderTest.getAllTickets().stream().anyMatch(ticket -> ticket.getNameOfBuyer().equals(BUYERNAME)));
	}

	@Test
	public void buying50TicketsTest()
	{
		for (int i = 1; i < 51; i++)
		{
			serviceUnderTest.purchaseTicket(BUYERNAME + i);
		}
		// 50 tickets with price 10 each + the original 200
		Assert.assertTrue(MoneyPot.getPot() == 700);
		Assert.assertTrue(serviceUnderTest.getAllTickets().stream().allMatch(Ticket::isPurchased));
	}

	@Test(expected = OverBuyingException.class)
	public void buying51TicketsTest()
	{
		for (int i = 0; i < 51; i++)
		{
			serviceUnderTest.purchaseTicket(BUYERNAME + i);
		}
	}

	@Test
	public void getAllTicketsTest()
	{
		Predicate<Ticket> fifty = i -> i.getTicketNumber() == 50;
		Predicate<Ticket> one = i -> i.getTicketNumber() == 1;
		Assert.assertFalse(serviceUnderTest.getAllTickets().isEmpty());
		Assert.assertTrue(serviceUnderTest.getAllTickets().stream().anyMatch(fifty));
		Assert.assertTrue(serviceUnderTest.getAllTickets().stream().anyMatch(one));
		Assert.assertTrue((long) serviceUnderTest.getAllTickets().size() == 50);
	}
}
