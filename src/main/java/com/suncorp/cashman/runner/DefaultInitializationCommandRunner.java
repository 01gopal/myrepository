package com.suncorp.cashman.runner;

import org.springframework.beans.factory.annotation.Autowired;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.currency.Denomination;
import com.suncorp.cashman.service.IAtmLockerService;
import com.suncorp.cashman.util.CashmanConstants.CurrencyCode;

@CashmanCommand(name=DefaultInitializationCommandRunner.COMMAND)
public class DefaultInitializationCommandRunner extends InitializationCommandRunner {
	public static final String COMMAND = "DEFAULT_INITIALIZATION";
	@Autowired
	IAtmLockerService atmLockerService;
	@Override
	public CommandRunnerResponse execute() {
		System.out.println(":::: Suncorp INITIALIZATION, Default ::::");
		String currencyCode = CurrencyCode.AUSTRALIA_CURRENCY_CODE;
		String currencySymbol = CurrencyCode.AUSTRALIA_CURRENCY_SYMBOL;
		Denomination d = new Denomination("TWENTY_AUD", 20, currencyCode, currencySymbol);
		atmLockerService.addDenominationCount(d, 50);
		d = new Denomination("HUNDRED_AUD", 100, currencyCode, currencySymbol);
		atmLockerService.addDenominationCount(d, 5);
		d = new Denomination("FIFTY_AUD", 50, currencyCode, currencySymbol);
		atmLockerService.addDenominationCount(d, 10);
		System.out.println(":::: Suncorp INITIALIZATION, COMPLETED ::::");
		return CommandRunnerResponse.SUCCESS;
	}

}
