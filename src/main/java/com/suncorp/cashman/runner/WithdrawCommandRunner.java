package com.suncorp.cashman.runner;

import org.springframework.beans.factory.annotation.Autowired;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.service.IAtmLockerService;
import com.suncorp.cashman.service.IWithdrawService;
import com.suncorp.cashman.util.CustomerInput;

@CashmanCommand(name=WithdrawCommandRunner.COMMAND)
public class WithdrawCommandRunner implements CashmanCommandRunner {
	public static final String COMMAND = "WITHDRAW";
	
	@Autowired
	CustomerInput input;
	
	@Autowired
	IAtmLockerService atmLockerService;
	
	@Autowired
	IWithdrawService withdrawService;
	
	
	public CommandRunnerResponse execute() {
		String ccySymbol = atmLockerService.getAcceptedCurrency();
		String inputAmount = null;
		try {
			inputAmount = input.takeCustomerInput("How much? : " + ccySymbol);
			double amount = Double.valueOf(inputAmount);
			if (amount > 0) {
				System.out.println("Start transaction for amount: " + ccySymbol + amount);
				withdrawService.withdrawAmount(amount);
				System.out.println("End transaction");
			} else {
				System.out.println("Enterd value = " + ccySymbol + inputAmount + " not a valid amount, try again!!");
				return CommandRunnerResponse.FAILED;
			}
		} catch (NumberFormatException e) {
			System.out.println("Enterd value = " + ccySymbol + inputAmount + " not a valid amount, try again!!");
			return CommandRunnerResponse.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to withdraw amount = " + ccySymbol + inputAmount + ", try again!!");
			return CommandRunnerResponse.FAILED;
		}
		return CommandRunnerResponse.SUCCESS;
	}

}
