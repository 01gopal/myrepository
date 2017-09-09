package com.suncorp.cashman.runner;

import org.springframework.beans.factory.annotation.Autowired;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.currency.Currency;
import com.suncorp.cashman.currency.CurrencyFactory;
import com.suncorp.cashman.currency.Denomination;
import com.suncorp.cashman.service.IAtmLockerService;
import com.suncorp.cashman.util.CashmanUtils;
import com.suncorp.cashman.util.CustomerInput;

@CashmanCommand(name=ManualInitializationCommandRunner.COMMAND)
public class ManualInitializationCommandRunner extends InitializationCommandRunner{
	public static final String COMMAND = "MANUAL_INITIALIZATION";
	@Autowired
	CustomerInput input;
	@Autowired
	IAtmLockerService atmLockerService;
	
	@Override
	public CommandRunnerResponse execute() {
		System.out.println(":::: Suncorp INITIALIZATION, Manual ::::");
		try {
			String currencyCode = input.takeCustomerInput("Enter currency code (eg. AUD or USD): ");
			CurrencyFactory factory = new CurrencyFactory();
			Currency currency = factory.getCurrency(currencyCode);
			atmLockerService.setAcceptedCurrency(CashmanUtils.findCurrencySymbol(currencyCode));
			for (Denomination d : currency.getDenominations()) {
				String inputCount = input.takeCustomerInput("Enter total number of " + d.getFaceName() + " : " + d.getCurrencySymbol());
				int count = Integer.parseInt(inputCount);
				if (count > 0) {
					atmLockerService.addDenominationCount(d, count);
				}
			}
			System.out.println(":::: Suncorp INITIALIZATION, COMPLETED ::::");
		} catch (Exception e) {
			e.printStackTrace();
			return CommandRunnerResponse.FAILED;
		}
		return CommandRunnerResponse.SUCCESS;
	}

}
