package com.esignlive.lottery.exceptions;


public class OverBuyingException extends RuntimeException
{
	public OverBuyingException()
	{
		super();
	}

	public OverBuyingException(final String message, final Throwable exception)
	{
		super(message, exception);
	}

	public OverBuyingException(final String message)
	{
		super(message);
	}

	public OverBuyingException(final Throwable exception)
	{
		super(exception);
	}
}
