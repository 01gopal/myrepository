package com.suncorp.cashman.runner;

import org.springframework.beans.factory.annotation.Autowired;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.service.IWithdrawService;
import com.suncorp.cashman.util.CustomerInput;

@CashmanCommand(name=WithdrawCommandRunner.COMMAND)
public class WithdrawCommandRunner implements CashmanCommandRunner {
	public static final String COMMAND = "WITHDRAW";
	
	@Autowired
	CustomerInput input;
	
	@Autowired
	IWithdrawService withdrawService;
	
	
	public CommandRunnerResponse execute() {
		System.out.println("Withdraw ammount...");
		String inputAmount = null;
		try {
			inputAmount = input.takeCustomerInput("Enter withdrawl amount: ");
			float amount = Float.valueOf(inputAmount);
			if (amount > 0) {
				System.out.println("Start transaction for amount: " + amount);
				withdrawService.withdrawAmount(amount);
				System.out.println("End transaction");
			} else {
				System.out.println("Enterd value = " + inputAmount + " not a valid amount, try again!!");
				return CommandRunnerResponse.FAILED;
			}
		} catch (NumberFormatException e) {
			System.out.println("Enterd value = " + inputAmount + " not a valid amount, try again!!");
			return CommandRunnerResponse.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to withdraw amount = " + inputAmount + ", try again!!");
			return CommandRunnerResponse.FAILED;
		}
		return CommandRunnerResponse.SUCCESS;
	}

}
