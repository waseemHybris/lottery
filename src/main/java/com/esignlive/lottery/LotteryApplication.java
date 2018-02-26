package com.esignlive.lottery;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.esignlive.lottery.domain.LotteryCommand;
import com.esignlive.lottery.services.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import static java.lang.System.in;
import static java.lang.System.out;


@SpringBootApplication
public class LotteryApplication implements CommandLineRunner
{
	@Autowired
	LotteryService lotteryService;

	public static void main(String[] args)
	{
		SpringApplication.run(LotteryApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception
	{
		try (Scanner scanner = new Scanner(in))
		{
			boolean running = true;
			while (running)
			{
				out.println("Enter command: [PURCHASE, DRAW, RESET, EXIT]");
				String inputCommand = scanner.nextLine();
				if (!StringUtils.isEmpty(inputCommand) && inputCommand.equalsIgnoreCase(LotteryCommand.EXIT.toString()))
				{
					break;
				}
				if (!StringUtils.isEmpty(inputCommand) && inputCommand.equalsIgnoreCase(LotteryCommand.PURCHASE.toString()))
				{
					out.println("Enter the name of the ticket's buyer: ");
					String buyerName = scanner.nextLine();
					lotteryService.purchaseTicket(buyerName);
				}
				if (!StringUtils.isEmpty(inputCommand) && inputCommand.equalsIgnoreCase(LotteryCommand.DRAW.toString()))
				{
					lotteryService.randomDraw();
				}
				if (!StringUtils.isEmpty(inputCommand) && inputCommand.equalsIgnoreCase(LotteryCommand.RESET.toString()))
				{
					lotteryService.resetProcess();
				}
				//TODO REMOVE
				if(!StringUtils.isEmpty(inputCommand) && inputCommand.equalsIgnoreCase("RANDOM")){
					ThreadLocalRandom.current().ints(1, 50).distinct().limit(3).forEach(System.out::println);
				}
			}
		}
		finally
		{
			in.close();
		}
	}

}
