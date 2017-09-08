package com.suncorp.cashman.runner;

import org.springframework.beans.factory.annotation.Autowired;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.currency.Currency;
import com.suncorp.cashman.currency.CurrencyFactory;
import com.suncorp.cashman.currency.Denomination;
import com.suncorp.cashman.service.IAtmLockerService;
import com.suncorp.cashman.util.CustomerInput;

@CashmanCommand(name=InitializationCommandRunner.COMMAND)
public class InitializationCommandRunner implements CashmanCommandRunner {
	public static final String COMMAND = "INITIALIZATION";

	@Autowired
	CustomerInput input;
	@Autowired
	IAtmLockerService atmLockerService;

	public CommandRunnerResponse execute() {
		System.out.println(":::: INITIALIZATION STARTED ::::");
		try {
			String currencyCode = input.takeCustomerInput(">> Enter currency code : ");
			CurrencyFactory factory = new CurrencyFactory();
			Currency currency = factory.getCurrency(currencyCode);
			for (Denomination d : currency.getDenominations()) {
				String inputCount = input.takeCustomerInput(">> Enter total number of " + d.getFaceName() + " : " + d.getCurrencySymbol());
				int count = Integer.parseInt(inputCount);
				atmLockerService.addDenominationCount(d, count);
			}
			System.out.println(":::: INITIALIZATION COMPLETED ::::");
		} catch (Exception e) {
			e.printStackTrace();
			return CommandRunnerResponse.FAILED;
		}
		return CommandRunnerResponse.SUCCESS;
	}
}
